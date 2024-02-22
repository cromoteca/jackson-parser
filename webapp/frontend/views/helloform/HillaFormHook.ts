import { EndpointValidationError } from '@vaadin/hilla-frontend';
import { type UseFormProps, useForm as parentUseForm, type FieldValues, type Path, type Resolver, type UseFormRegister } from 'react-hook-form';

/**
 * A hook that wraps react-hook-form and adds support for Hilla validation and Vaadin Components.
 * @param serverResolver And optional endpoint method to call to use server-side validation.
 */
function useHillaForm<T extends FieldValues, C = any>(props: UseFormProps<T, C> & {
    serverResolver?: (data: T) => any | undefined,
}): ReturnType<typeof parentUseForm<T, C>> & {
    // A wrapper around endpoint submission calls that can parse validation errors
    validate: <R>(outcome: Promise<R>, setError?: typeof parentSetError | undefined) => Promise<R | undefined>,
    // A wrapper around register that adds Vaadin-specific properties
    field: UseFormRegister<T>;
} {
    // Awaits for the outcome of an endpoint call and parses validation errors
    const validate = async <R>(outcome: Promise<R>) => {
        try {
            return await outcome;
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

                        // Set the error, but only if the field is dirty or the form is submitting
                        // Note that `parentForm.formState.isSubmitting` must be called as such and not as `isSubmitting` which stays false
                        if (parentForm.formState.isSubmitting || (getFieldState(path).isDirty) && !errors[property]) {
                            parentSetError(path, { type: 'validate', message });
                        }
                    }
                });

                return undefined;
            } else {
                throw error;
            }
        }
    }

    // A wrapper that adds server-side validation to the resolver
    const resolverWrapper: Resolver<T, C> = async (values, c, o) => {
        const clientValidation = props.resolver ? props.resolver(values, c, o) : { values, errors: {} };

        if (isDirty && props.serverResolver) {
            validate(props.serverResolver(values));
        }

        return await clientValidation;
    };

    // A wrapper around register that adds Vaadin-specific properties
    const field: typeof register = (name, options?) => {
        return ({
            ...register(name, options),
            invalid: !!errors[name],
            errorMessage: errors[name] && `${errors[name]?.message}`,
        });
    };

    // Call the parent hook wrapping the resolver if needed
    const parentForm = parentUseForm({
        ...props,
        resolver: props.serverResolver ? resolverWrapper : props.resolver,
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
