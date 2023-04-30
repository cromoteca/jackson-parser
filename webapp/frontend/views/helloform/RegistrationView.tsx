import { Button } from "@hilla/react-components/Button.js";
import { EmailField } from "@hilla/react-components/EmailField.js";
import { Notification } from "@hilla/react-components/Notification.js";
import { Select } from "@hilla/react-components/Select.js";
import { TextField } from "@hilla/react-components/TextField.js";
import { VerticalLayout } from "@hilla/react-components/VerticalLayout.js";
import FormValidationEndpoint from "Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint.js";
import RegistrationInfo from "Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint/RegistrationInfo.js";
import { GeneratedResolver } from "./GeneratedResolver.js";
import useHillaForm from "./HillaFormHook.js";
import ValidatedCheckbox from "./ValidatedCheckbox.js";

export default function RegistrationView() {
    const countries = [
        { value: "FI", label: "Finland" },
        { value: "DE", label: "Germany" },
        { value: "US", label: "United States" },
    ];

    const { field, handleSubmit, validate } = useHillaForm<RegistrationInfo>({
        mode: 'all',
    resolver: GeneratedResolver,
        serverResolver: FormValidationEndpoint.preValidate,
    });

    const onSubmit = async (data: RegistrationInfo) => {
        try {
            const result = await validate(FormValidationEndpoint.handleRegistration(data));
            result && Notification.show(result, { theme: "success" });
        } catch (error: any) {
            Notification.show(error.message, { theme: "error" });
        }
    };

    return (
        <VerticalLayout className='p-m'>
            <TextField label="Name" required {...field("name")} />
            <EmailField label="Email"  required {...field("email")} />
            <TextField label="Phone" {...field("phone")} />
            <Select label="Country" required items={countries} {...field("country")} />
            <ValidatedCheckbox label="I agree to the terms and conditions" {...field("terms")} />
            <Button theme="primary" onClick={handleSubmit(onSubmit)}>Register</Button>
        </VerticalLayout>
    );
}
