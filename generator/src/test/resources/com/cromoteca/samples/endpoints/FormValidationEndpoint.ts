import client from '../../../../connect-client.default.js';
import type RegistrationInfo from './FormValidationEndpoint/RegistrationInfo.js';
import { type EndpointRequestInit } from '@hilla/frontend';

async function validate(info: RegistrationInfo, init?: EndpointRequestInit): Promise<string> {
    return client.call('com.cromoteca.samples.endpoints.FormValidationEndpoint', 'validate', { info }, init);
}

const FormValidationEndpoint = {
    validate,
};

export default FormValidationEndpoint;
