import { EndpointValidationError } from '@hilla/frontend';
import { yupResolver } from '@hookform/resolvers/yup';
import { useForm as parentUseForm } from 'react-hook-form';
import { AnyObject, Maybe, ObjectSchema } from 'yup';

/**
 * A hook that wraps react-hook-form and adds support for Yup validation and Vaadin Components.
 * @param schema The Yup schema to use for validation.
 * @param submitHandler The function to call when the form is submitted and passes validation.
 * @param successHandler The function to call when the submitHandler succeeds.
 * @param errorHandler The function to call when the submitHandler fails with an unexpected error.
 */
function useForm<T extends Maybe<AnyObject>, U>(
    schema: ObjectSchema<T>,
    submitHandler: (data: T) => Promise<U>,
    successHandler?: (result: U) => any,
    errorHandler?: (error: any) => any,
): Omit<ReturnType<typeof parentUseForm>, 'handleSubmit'> & {
    handleSubmit: (event: any) => Promise<void>;
} {
    const parentForm = parentUseForm({
        mode: 'all', // Validates on all events
        //@ts-ignore
        resolver: yupResolver(schema),
    });

    const { register: parentRegister, handleSubmit: parentHandleSubmit, formState: { errors }, setError } = parentForm;

    const register: typeof parentRegister = (name, options?) => {
        return ({
            ...parentRegister(name, options),
            // Vaadin-specific properties
            invalid: !!errors[name],
            errorMessage: `${errors[name]?.message}`,
        });
    };

    const onValid = async (data: any) => {
        try {
            const response = await submitHandler(data);
            successHandler && successHandler(response);
        } catch (error: any) {
            if (error instanceof EndpointValidationError) {
                // As in Lit, parse the error messages and set the errors on the corresponding fields
                error.validationErrorData.forEach((data) => {
                    const res =
                        /Object of type '(.+)' has invalid property '(.+)' with value '(.+)', validation error: '(.+)'/.exec(
                            data.message,
                        );
                    const [property, , message] = res ? res.splice(2) : [data.parameterName, undefined, data.message];
                    setError(property!, { type: 'server', message });
                });
            } else {
                // Other error, use passed error handler
                errorHandler && errorHandler(error);
            }
        }
    };

    // Wrap the parent handleSubmit to call onValid
    const handleSubmit = function(event: any) {
        return parentHandleSubmit(onValid)(event);
    };

    return {
        ...parentForm,
        register,
        handleSubmit,
    };
};

export default useForm;
