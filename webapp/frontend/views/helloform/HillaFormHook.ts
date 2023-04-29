import { EndpointValidationError } from '@hilla/frontend';
import { FieldValues, Path, Resolver, UseFormRegister, useForm as parentUseForm } from 'react-hook-form';

/**
 * A hook that wraps react-hook-form and adds support for Yup validation and Vaadin Components.
 * @param schema The Yup schema to use for validation.
 * @param submitHandler The function to call when the form is submitted and passes validation.
 * @param successHandler The function to call when the submitHandler succeeds.
 * @param errorHandler The function to call when the submitHandler fails with an unexpected error.
 */
function useForm<T extends FieldValues, R, C = any>(options: {
    resolver: Resolver<T, C>,
    submitHandler: (data: T) => Promise<R>,
    successHandler?: (result: R) => any,
    errorHandler?: (error: { message: string }) => any,
    preValidationHandler?: (data: T) => any,
}): Omit<ReturnType<typeof parentUseForm<T, C>>, 'handleSubmit'> & {
    field: UseFormRegister<T>,
    handleSubmit: (event: any) => Promise<void>;
} {
    const setServerValidationErrors = async <U>(
        values: T,
        endpointCall: (values: T) => Promise<U>,
        setError1: typeof setError, // (name: keyof T, error: { type: string, message: string }) => void,
        errorHandler?: (error: any) => any,
    ) => {
        try {
            return await endpointCall(values);
        } catch (error: any) {
            if (error instanceof EndpointValidationError) {
                // As in Lit, parse the error messages and set the errors on the corresponding fields
                error.validationErrorData.forEach((data) => {
                    const res =
                        /Object of type '(.+)' has invalid property '(.+)' with value '(.*)', validation error: '(.+)'/.exec(
                            data.message,
                        );
                    const [property, , message] = res ? res.splice(2) : [data.parameterName, undefined, data.message];

                    if (property) {
                        const path = property as Path<T>;

                        if (getFieldState(path).isDirty && !errors[property]) {
                            setError1(path, { type: 'validate', message });
                        }
                    }
                });
            } else if (errorHandler) {
                errorHandler(error);
            } else {
                throw error;
            }

            return undefined;
        }
    }

    const wrapResolver = (resolver: Resolver<T, C>): Resolver<T, C> => {
        return (values, c, o) => {
            const clientValidation = resolver(values, c, o);

            if (isDirty && options.preValidationHandler) {
                setServerValidationErrors(values, options.preValidationHandler, parentForm.setError);
            }

            return clientValidation;
        };
    };

    const field: typeof register = (name, options?) => {
        return ({
            ...register(name, options),
            // Vaadin-specific properties
            invalid: !!errors[name],
            errorMessage: `${errors[name]?.message}`,
        });
    };

    const parentForm = parentUseForm({
        mode: 'all', // Validates on all events
        resolver: wrapResolver(options.resolver),
    });

    const { register: parentRegister, handleSubmit: parentHandleSubmit, formState: { errors, isDirty }, setError, getFieldState } = parentForm;

    const register: typeof parentRegister = (name, options?) => {
        return ({
            ...parentRegister(name, options),
            // Vaadin-specific properties
            invalid: !!errors[name],
            errorMessage: `${errors[name]?.message}`,
        });
    };

    const onValid = async (data: any) => {
        const response = await setServerValidationErrors(data, options.submitHandler, setError, options.errorHandler);
        response && options.successHandler && options.successHandler(response);
    };

    // Wrap the parent handleSubmit to call onValid
    const handleSubmit = function (event: any) {
        return parentHandleSubmit(onValid)(event);
    };

    return {
        ...parentForm,
        field,
        handleSubmit,
    };
};

export default useForm;
