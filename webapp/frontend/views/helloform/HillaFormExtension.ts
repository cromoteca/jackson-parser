import { EndpointValidationError } from '@hilla/frontend';
import { FieldValues, UseFormRegister, useForm } from 'react-hook-form';

/**
 * A hook that wraps react-hook-form and adds support for validation and Vaadin Components.
 */
function useHillaExtension<T extends FieldValues, C = any>(
    parentForm: ReturnType<typeof useForm<T, C>>,
): ReturnType<typeof useForm<T, C>> & {
    field: UseFormRegister<T>,
    handleEndpoint: <R>(returnValue: Promise<R>) => Promise<R | undefined>
} {
    const { register, formState: { errors }, setError } = parentForm;

    const field: typeof register = (name, options?) => {
        return ({
            ...register(name, options),
            // Vaadin-specific properties
            invalid: !!errors[name],
            errorMessage: `${errors[name]?.message}`,
        });
    };

    const handleEndpoint = async <R>(returnValue: Promise<R>) => {
        try {
            return await returnValue;
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
                    setError(property!, { type: 'validate', message });
                });

                return undefined;
            } else {
                throw error;
            }
        }
    };

    return {
        ...parentForm,
        field,
        handleEndpoint,
    };
};

export default useHillaExtension;
