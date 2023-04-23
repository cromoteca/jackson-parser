import { Checkbox } from '@hilla/react-components/Checkbox.js';
import { ComponentProps, Ref, forwardRef } from 'react';
import { FieldError, FieldErrorsImpl, Merge } from 'react-hook-form';

type CheckboxFieldProps = Omit<ComponentProps<typeof Checkbox>, 'onCheckedChanged'> & {
    onChange?: (e: CustomEvent<any>) => void;
    error?: FieldError | Merge<FieldError, FieldErrorsImpl<any>> | undefined;
};

const CheckboxField = forwardRef((
    { error, onChange, ...checkboxProps }: CheckboxFieldProps,
    //@ts-ignore
    ref: Ref<Checkbox> | undefined
) => (
    <div>
        <Checkbox {...checkboxProps} ref={ref} onCheckedChanged={onChange} />
        {error && <span>{`${error.message}`}</span>}
    </div>
));

export default CheckboxField;
