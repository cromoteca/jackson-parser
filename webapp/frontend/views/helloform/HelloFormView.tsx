import { EndpointValidationError } from '@hilla/frontend';
import { Button } from '@hilla/react-components/Button.js';
import { HorizontalLayout } from '@hilla/react-components/HorizontalLayout.js';
import { Notification } from '@hilla/react-components/Notification.js';
import { Select } from '@hilla/react-components/Select.js';
import { TextField } from '@hilla/react-components/TextField.js';
import { VerticalLayout } from '@hilla/react-components/VerticalLayout.js';
import { yupResolver } from '@hookform/resolvers/yup';
import FormValidationEndpoint from 'Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint.js';
import RegistrationInfo from 'Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint/RegistrationInfo.js';
import { ObjectSchema, boolean, object, string } from 'yup';
import { useForm } from './HillaFormHook.js';
import ValidatedCheckbox from './ValidatedCheckbox.js';

export default function HelloFormView() {
  const countries = [
    { value: "FI", label: "Finland" },
    { value: "DE", label: "Germany" },
    { value: "US", label: "United States" },
  ];

  const yupSchema: ObjectSchema<RegistrationInfo> = object({
    name: string().required(),
    email: string().email().required(),
    phone: string().matches(/^[0-9]*$/).optional(),
    country: string().min(2).max(3).required(),
    terms: boolean().oneOf([true]).required(),
  });

  const { register, handleSubmit, setError, formState: { isValid } } = useForm({
    mode: 'all',
    resolver: yupResolver(yupSchema),
  });

  const onSubmit = async (data: any) => {
    try {
      const response = await FormValidationEndpoint.validate(data);
      Notification.show(response, { theme: 'success' });
    } catch (error: any) {
      if (error instanceof EndpointValidationError) {
        error.validationErrorData.forEach((data) => {
          const res =
            /Object of type '(.+)' has invalid property '(.+)' with value '(.+)', validation error: '(.+)'/.exec(
              data.message,
            );
          const [property, , message] = res ? res.splice(2) : [data.parameterName, undefined, data.message];
          setError(property!, { type: 'server', message });
        });
      } else {
        Notification.show(error.message || 'Server error', { theme: 'error' });
      }
    }
  };

  return (
    <VerticalLayout className='p-m'>
      <HorizontalLayout theme="spacing padding">
        <TextField label="Name" required {...register("name")} />
        <TextField label="Email" required {...register("email")} />
      </HorizontalLayout>

      <HorizontalLayout theme="spacing padding">
        <TextField label="Phone" {...register("phone")} />
        <Select label="Country" required {...register("country")} items={countries} />
      </HorizontalLayout>

      <HorizontalLayout theme="spacing padding">
        <ValidatedCheckbox label="I agree to the terms and conditions" {...register("terms")} />
      </HorizontalLayout>

      <HorizontalLayout theme="spacing padding">
        <Button theme="primary" onClick={handleSubmit(onSubmit)} disabled={!isValid}>Register</Button>
      </HorizontalLayout>
    </VerticalLayout>
  );
}
