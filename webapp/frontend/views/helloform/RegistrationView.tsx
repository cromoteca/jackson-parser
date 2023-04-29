import { Button } from "@hilla/react-components/Button.js";
import { Select } from "@hilla/react-components/Select.js";
import { TextField } from "@hilla/react-components/TextField.js";
import { VerticalLayout } from "@hilla/react-components/VerticalLayout.js";
import FormValidationEndpoint from "Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint.js";
import RegistrationInfo from "Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint/RegistrationInfo.js";
import { useForm } from "react-hook-form";
import { FakeResolver } from "./FakeResolver.js";
import useHillaExtension from "./HillaFormHook.js";
import ValidatedCheckbox from "./ValidatedCheckbox.js";
import { Notification } from "@hilla/react-components/Notification.js";

export default function RegistrationView() {
    const countries = [
        { value: "FI", label: "Finland" },
        { value: "DE", label: "Germany" },
        { value: "US", label: "United States" },
    ];

    const { field, handleSubmit, handleEndpoint, formState: { isValid } } = useHillaExtension(useForm<RegistrationInfo>({
        resolver: FakeResolver,
        mode: 'all',
    }));

    const onSubmit = async (data: RegistrationInfo) => {
        var outcome = await handleEndpoint(FormValidationEndpoint.handleRegistration(data));
        outcome && Notification.show(outcome, { theme: "success" });
    };

    return (
        <VerticalLayout className='p-m'>
            <TextField label="Name" {...field("name")} />
            <TextField label="Email" {...field("email")} />
            <TextField label="Phone" {...field("phone")} />
            <Select label="Country" items={countries} {...field("country")} />
            <ValidatedCheckbox label="I agree to the terms and conditions" {...field("terms")} />
            <Button theme="primary" onClick={handleSubmit(onSubmit)} disabled={!isValid}>Register</Button>
        </VerticalLayout>
    );
}
