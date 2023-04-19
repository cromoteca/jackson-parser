import { Button } from '@hilla/react-components/Button.js';
import { Checkbox } from '@hilla/react-components/Checkbox.js';
import { Notification } from '@hilla/react-components/Notification.js';
import { Select } from '@hilla/react-components/Select.js';
import { TextField } from '@hilla/react-components/TextField.js';
import { VerticalLayout } from '@hilla/react-components/VerticalLayout.js';
import FormValidationEndpoint from 'Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint.js';
import RegistrationInfo from 'Frontend/generated/com/cromoteca/samples/endpoints/FormValidationEndpoint/RegistrationInfo.js';
import { Field, Form, Formik } from 'formik';
import { ObjectSchema, boolean, object, string } from 'yup';

export default function HelloFormView() {
    const countries = [
        { value: "FI", label: "Finland" },
        { value: "DE", label: "Germany" },
        { value: "US", label: "United States" },
    ];

    const initialValues: RegistrationInfo = {
        name: '',
        email: '',
        phone: '',
        country: '',
        conditions: false,
    };

    const yupSchema: ObjectSchema<RegistrationInfo> = object({
        name: string().required(),
        email: string().email().required(),
        phone: string().matches(/^[0-9]+$/).required(),
        country: string().min(2).max(2).required(),
        conditions: boolean().oneOf([true]).required(),
    });

    return (
        <Formik
            initialValues={{ ...initialValues }}
            validationSchema={yupSchema}
            onSubmit={async values => {
                const response = await FormValidationEndpoint.validate(values);
                Notification.show(response, { theme: 'success' });
            }}
        >
            {({ submitForm, isValid, dirty }) => (
                <Form>
                    <VerticalLayout theme="spacing padding">
                        <Field name="name">
                            {({ field, meta }: any) => (
                                <TextField {...field} placeholder="Name"
                                    invalid={meta.error && meta.touched} errorMessage={meta.error} />
                            )}
                        </Field>

                        <Field name="email">
                            {({ field, meta }: any) => (
                                <TextField {...field} placeholder="Email"
                                    invalid={meta.error && meta.touched} errorMessage={meta.error} />
                            )}
                        </Field>

                        <Field name="phone">
                            {({ field, meta }: any) => (
                                <TextField {...field} placeholder="Phone"
                                    invalid={meta.error && meta.touched} errorMessage={meta.error} />
                            )}
                        </Field>

                        <Field name="country">
                            {({ field, meta }: any) => (
                                <Select {...field} placeholder="Country" items={countries} name={field.name}
                                    invalid={meta.error && meta.touched} errorMessage={meta.error} />
                            )}
                        </Field>

                        <Field name="conditions">
                            {({ field, meta }: any) =>
                                <Checkbox {...field} label="I accept terms and conditions"
                                    invalid={meta.error && meta.touched} errorMessage={meta.error}
                                    onCheckedChanged={field.onChange} />
                            }
                        </Field>

                        <Button theme="primary" onClick={submitForm} disabled={!(dirty && isValid)}>Submit</Button>
                    </VerticalLayout>
                </Form>
            )}
        </Formik>
    );
}
