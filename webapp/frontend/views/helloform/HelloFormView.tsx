import { Button } from '@hilla/react-components/Button.js';
import { Checkbox } from '@hilla/react-components/Checkbox.js';
import { Notification } from '@hilla/react-components/Notification.js';
import { Select } from '@hilla/react-components/Select.js';
import { TextField } from '@hilla/react-components/TextField.js';
import { VerticalLayout } from '@hilla/react-components/VerticalLayout.js';
import { yupResolver } from '@hookform/resolvers/yup';
import FormValidationEndpoint from 'Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint.js';
import RegistrationInfo from 'Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint/RegistrationInfo.js';
import { useForm } from 'react-hook-form';
import { ObjectSchema, boolean, object, string } from 'yup';

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

  const { register, handleSubmit, formState: { errors, isValid } } = useForm({
    mode: 'all',
    resolver: yupResolver(yupSchema),
  });

  const onSubmit = async (data: any) => {
    const response = await FormValidationEndpoint.validate(data);
    Notification.show(response, { theme: 'success' });
  };

  return (
    <VerticalLayout theme="spacing padding">
      <TextField label="Name" required {...register("name")} invalid={!!errors.name} errorMessage={`${errors.name?.message}`} />
      <TextField label="Email" required {...register("email")} invalid={!!errors.email} errorMessage={`${errors.email?.message}`} />
      <TextField label="Phone" {...register("phone")} invalid={!!errors.phone} errorMessage={`${errors.phone?.message}`} />
      <Select label="Country" required {...register("country")} items={countries} invalid={!!errors.country} errorMessage={`${errors.country?.message}`} />
      <Checkbox label="I accept terms and conditions" {...register("terms")} />
      {errors.terms?.message && <p>{`${errors.terms?.message}`}</p>}

      <Button theme="primary" onClick={handleSubmit(onSubmit)} disabled={!isValid}>Submit</Button>
    </VerticalLayout>
  );
}
