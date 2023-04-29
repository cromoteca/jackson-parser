import RegistrationInfo from "Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint/RegistrationInfo.js";
import ValidationEndpoint from "Frontend/generated/com/example/application/endpoints/ValidationEndpoint.js";
import { Resolver } from "react-hook-form";
import { EndpointValidationError } from "@hilla/frontend";

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

    try {
        await ValidationEndpoint.preValidate(values);
    } catch (error: any) {
        if (error instanceof EndpointValidationError) {
            // As in Lit, parse the error messages and set the errors on the corresponding fields
            error.validationErrorData.forEach((data) => {
                const res =
                    /Object of type '(.+)' has invalid property '(.+)' with value '(.*)', validation error: '(.+)'/.exec(
                        data.message,
                    );
                const [property, , message] = res ? res.splice(2) : [data.parameterName, undefined, data.message];
                //@ts-ignore
                if (property && !errors[property]) {
                    errors[property] = {
                        type: 'validate',
                        message,
                    }
                }
            });
        } else {
            throw error;
        }
    }


    return {
        values,
        errors,
    };
}
