import client from '../../../../connect-client.default.js';
import type RegistrationInfo from './FormValidationEndpoint/RegistrationInfo.js';
import { type EndpointRequestInit } from '@hilla/frontend';

async function handleRegistration(info: RegistrationInfo, init?: EndpointRequestInit): Promise<string> {
    return client.call('com.cromoteca.samples.endpoints.FormValidationEndpoint', 'handleRegistration', { info }, init);
}

async function preValidate(info: RegistrationInfo, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.FormValidationEndpoint', 'preValidate', { info }, init);
}

const FormValidationEndpoint = {
    handleRegistration,
    preValidate,
};

export default FormValidationEndpoint;
