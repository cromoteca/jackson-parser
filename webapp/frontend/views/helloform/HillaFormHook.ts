import { useForm as parentUseForm } from 'react-hook-form';

const useForm: typeof parentUseForm = (props?) => {
    const parentForm = parentUseForm(props);

    const { register: parentRegister, formState: { errors } } = parentForm;
    const register: typeof parentRegister = (name, options?) => {
        return ({
            ...parentRegister(name, options),
            invalid: !!errors[name],
            errorMessage: `${errors[name]?.message}`
        });
    };

    return {
        ...parentForm,
        register,
    };
};

export { useForm };
