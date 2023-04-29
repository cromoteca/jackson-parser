import RegistrationInfo from "Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint/RegistrationInfo.js";
import { Resolver } from "react-hook-form";

function isValidEmail(email: string): boolean {
    const emailInput = document.createElement('input');
    emailInput.type = 'email';
    emailInput.value = email;

    return emailInput.validity.valid;
}

export const FakeResolver: Resolver<RegistrationInfo, any> = async (values, _context, options) => {
    const errors: any = {};
    if (!errors.name && !values.name) {
        errors.name = {
            type: 'required',
            message: 'name is required',
        }
    }
    if (!errors.email && !values.email) {
        errors.email = {
            type: 'required',
            message: 'email is required',
        }
    }
    if (!errors.email && !isValidEmail(values.email)) {
        errors.email = {
            type: 'invalid',
            message: 'invalid email',
        }
    }
    if (!errors.phone && values.phone && !/^\+?[0-9]*$/.test(values.phone)) {
        errors.phone = {
            type: 'invalid',
            message: 'invalid phone, must be a number',
        }
    }
    if (!errors.country && values.country && !/^[A-Z]{2,3}$/.test(values.country)) {
        errors.country = {
            type: 'invalid',
            message: 'invalid country, must be a 2 or 3 letter country code',
        }
    }
    if (!errors.terms && !values.terms) {
        errors.terms = {
            type: 'required',
            message: 'terms must be accepted',
        }
    }

    return {
        values,
        errors,
    };
}
