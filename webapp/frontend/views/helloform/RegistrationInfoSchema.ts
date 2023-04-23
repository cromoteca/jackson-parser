import RegistrationInfo from "Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint/RegistrationInfo.js";
import { ObjectSchema, boolean, object, string } from "yup";

const RegistrationInfoYupSchema: ObjectSchema<RegistrationInfo> = object({
    name: string().required(),
    email: string().email().required(),
    phone: string().matches(/^[0-9]*$/).optional(),
    country: string().min(2).max(3).required(),
    terms: boolean().oneOf([true]).required(),
});

export default RegistrationInfoYupSchema;
