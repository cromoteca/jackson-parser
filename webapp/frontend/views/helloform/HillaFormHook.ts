import { EndpointValidationError } from '@hilla/frontend';
import { type UseFormProps, useForm as parentUseForm, type FieldValues, type Path, type Resolver, type UseFormRegister } from 'react-hook-form';

/**
 * A hook that wraps react-hook-form and adds support for Hilla validation and Vaadin Components.
 * @param preValidationHandler The function to call to check for server-side validation errors.
 */
function useHillaForm<T extends FieldValues, C = any>(props: UseFormProps<T, C> & {
    preValidationHandler?: (data: T) => any | undefined,
}): ReturnType<typeof parentUseForm<T, C>> & {
    validate: <R>(outcome: Promise<R>, setError?: typeof parentSetError | undefined) => Promise<R | undefined>,
    field: UseFormRegister<T>;
} {
    const validate = async <R>(
        outcome: Promise<R>,
        setError?: typeof parentSetError | undefined,
    ) => {
        try {
            return await outcome;
        } catch (error: any) {
            if (setError && error instanceof EndpointValidationError) {
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
                            setError(path, { type: 'validate', message });
                        }
                    }
                });

                return undefined;
            } else {
                throw error;
            }
        }
    }

    const wrapResolver = (resolver: Resolver<T, C>): Resolver<T, C> => {
        return (values, c, o) => {
            const clientValidation = resolver(values, c, o);

            if (isDirty && props.preValidationHandler) {
                validate(props.preValidationHandler(values), parentSetError);
            }

            return clientValidation;
        };
    };

    const field: typeof register = (name, options?) => {
        return ({
            ...register(name, options),
            // Vaadin-specific properties
            invalid: !!errors[name],
            errorMessage: errors[name] && `${errors[name]?.message}`,
        });
    };

    const parentForm = parentUseForm({
        ...props,
        resolver: props.resolver && wrapResolver(props.resolver),
    });

    const {
        register,
        formState: { errors, isDirty },
        setError: parentSetError,
        getFieldState
    } = parentForm;

    return {
        ...parentForm,
        field,
        validate,
    };
};

export default useHillaForm;
