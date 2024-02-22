import { Checkbox } from '@vaadin/react-components';
import { ComponentProps, Ref, forwardRef } from 'react';

type CheckboxFieldProps = Omit<ComponentProps<typeof Checkbox>, 'onCheckedChanged'> & {
    // These properties are set by `useForm`
    onChange?: (e: CustomEvent<any>) => void;
    invalid?: boolean | undefined;
    errorMessage?: string | undefined;
};

/**
 * A checkbox that can be used in a form. It is a wrapper around the
 * Vaadin Checkbox component that adds support for validation.
 */
const ValidatedCheckbox = forwardRef((
    { invalid, errorMessage, onChange, ...checkboxProps }: CheckboxFieldProps,
    //@ts-ignore
    ref: Ref<Checkbox> | undefined
) => (
    <div>
        <Checkbox {...checkboxProps} ref={ref} onCheckedChanged={onChange} />
        {invalid && <div>{errorMessage}</div>}
    </div>
));

export default ValidatedCheckbox;
