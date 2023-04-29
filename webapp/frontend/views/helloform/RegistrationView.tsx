import { Button } from "@hilla/react-components/Button.js";
import { Notification } from "@hilla/react-components/Notification.js";
import { Select } from "@hilla/react-components/Select.js";
import { TextField } from "@hilla/react-components/TextField.js";
import { VerticalLayout } from "@hilla/react-components/VerticalLayout.js";
import FormValidationEndpoint from "Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint.js";
import { FakeResolver } from "./FakeResolver.js";
import useForm from "./HillaFormHook.js";
import ValidatedCheckbox from "./ValidatedCheckbox.js";

export default function RegistrationView() {
    const countries = [
        { value: "FI", label: "Finland" },
        { value: "DE", label: "Germany" },
        { value: "US", label: "United States" },
    ];

    const { field, handleSubmit, formState: { isValid } } = useForm({
        resolver: FakeResolver,
        submitHandler: FormValidationEndpoint.handleRegistration,
        successHandler: (result) => Notification.show(result, { theme: "success" }),
        errorHandler: (error) => Notification.show(error.message, { theme: "error" }),
        preValidationHandler: FormValidationEndpoint.preValidate,
    });

    return (
        <VerticalLayout className='p-m'>
            <TextField label="Name" {...field("name")} />
            <TextField label="Email" {...field("email")} />
            <TextField label="Phone" {...field("phone")} />
            <Select label="Country" items={countries} {...field("country")} />
            <ValidatedCheckbox label="I agree to the terms and conditions" {...field("terms")} />
            <Button theme="primary" onClick={handleSubmit} disabled={!isValid}>Register</Button>
        </VerticalLayout>
    );
}
