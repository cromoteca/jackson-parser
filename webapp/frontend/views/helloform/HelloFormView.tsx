import { EndpointValidationError } from '@hilla/frontend';
import { Button } from '@hilla/react-components/Button.js';
import { Notification } from '@hilla/react-components/Notification.js';
import { Select } from '@hilla/react-components/Select.js';
import { TextField } from '@hilla/react-components/TextField.js';
import { VerticalLayout } from '@hilla/react-components/VerticalLayout.js';
import HelloFormEndpoint from 'Frontend/generated/com/example/application/endpoints/helloreact/HelloFormEndpoint.js';
import RegistrationInfo from 'Frontend/generated/com/example/application/endpoints/helloreact/HelloFormEndpoint/RegistrationInfo.js';
import { Field, Form, Formik } from 'formik';
import { ObjectSchema, object, string } from 'yup';

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
    };

    const yupSchema: ObjectSchema<RegistrationInfo> = object({
        name: string().required(),
        email: string().email().required(),
        phone: string().matches(/^[0-9]+$/).required(),
        country: string().min(2).max(2).required(),
    });

    return (
        <Formik
            initialValues={{ ...initialValues }}
            validationSchema={yupSchema}
            onSubmit={async values => {
                try {
                    const response = await HelloFormEndpoint.validate(values);

                    if (response === 'OK') {
                        Notification.show('Registration accepted', { theme: 'success' });
                    } else {
                        Notification.show(`Registration rejected: ${response}`, { theme: 'error' });
                    }
                } catch (error) {
                    if (error instanceof EndpointValidationError) {
                        error.validationErrorData.forEach((error) => {
                            Notification.show(error.message, { theme: 'error' });
                        });
                    }
                }
            }}
        >
            {({ errors, touched, submitForm, isValid, dirty }) => (
                <Form>
                    <VerticalLayout theme="spacing padding">
                        <Field name="name">
                            {({ field, meta }: any) => (
                                <TextField {...field} placeholder="Name" invalid={meta.error && meta.touched} errorMessage={meta.error} />
                            )}
                        </Field>
                        <Field name="email">
                            {({ field, meta }: any) => (
                                <TextField {...field} placeholder="Email" invalid={meta.error && meta.touched} errorMessage={meta.error} />
                            )}
                        </Field>
                        <Field name="phone">
                            {({ field, meta }: any) => (
                                <TextField {...field} placeholder="Phone" invalid={meta.error && meta.touched} errorMessage={meta.error} />
                            )}
                        </Field>
                        <Field name="country" as={Select} placeholder="Country" items={countries} />
                        {errors.country && touched.country ? <div>{errors.country}</div> : null}

                        <Button theme="primary" onClick={submitForm} disabled={!dirty || !isValid}>Submit</Button>
                    </VerticalLayout>
                </Form>
            )}
        </Formik>
    );
}
