import { Button } from '@hilla/react-components/Button.js';
import { HorizontalLayout } from '@hilla/react-components/HorizontalLayout.js';
import { Notification } from '@hilla/react-components/Notification.js';
import { Select } from '@hilla/react-components/Select.js';
import { TextField } from '@hilla/react-components/TextField.js';
import { VerticalLayout } from '@hilla/react-components/VerticalLayout.js';
import FormValidationEndpoint from 'Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint.js';
import useForm from './HillaFormHook.js';
import { RegistrationInfoSchema, country, email, name, phone, terms } from './RegistrationInfoSchema.js';
import ValidatedCheckbox from './ValidatedCheckbox.js';

export default function HelloFormView() {
  const countries = [
    { value: "FI", label: "Finland" },
    { value: "DE", label: "Germany" },
    { value: "US", label: "United States" },
  ];

  const { register, handleSubmit, formState: { isValid } } = useForm(
    RegistrationInfoSchema,
    FormValidationEndpoint.handleRegistration,
    (message) => Notification.show(message, { theme: 'success' }),
    (error) => Notification.show(error.message || 'Server error', { theme: 'error' }),
  );

  return (
    <VerticalLayout className='p-m'>
      <HorizontalLayout theme="spacing padding">
        <TextField label="Name" required {...register(name)} />
        <TextField label="Email" required {...register(email)} />
      </HorizontalLayout>

      <HorizontalLayout theme="spacing padding">
        <TextField label="Phone" {...register(phone)} />
        <Select label="Country" items={countries} required {...register(country)} />
      </HorizontalLayout>

      <HorizontalLayout theme="spacing padding">
        <ValidatedCheckbox label="I agree to the terms and conditions" {...register(terms)} />
      </HorizontalLayout>

      <HorizontalLayout theme="spacing padding">
        <Button theme="primary" onClick={handleSubmit} disabled={!isValid}>Register</Button>
      </HorizontalLayout>
    </VerticalLayout>
  );
}
