import { type ObjectSchema, boolean, object, string } from 'yup';
import type RegistrationInfo from './RegistrationInfo.js';

const RegistrationInfoYupSchema: ObjectSchema<RegistrationInfo> = object({
    conditions: boolean().oneOf([true]).required(),
    country: string().min(2).max(3).required(),
    email: string().email().required(),
    name: string().required(),
    phone: string().matches(/^[0-9]+$/).required(),
});

export default RegistrationInfoYupSchema;
