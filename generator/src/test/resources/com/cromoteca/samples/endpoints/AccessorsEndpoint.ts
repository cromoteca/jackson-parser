import client from '../../../../connect-client.default.js';
import type Accessors from './AccessorsEndpoint/Accessors.js';
import { type EndpointRequestInit } from '@hilla/frontend';

async function accessors(arg: Accessors, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.AccessorsEndpoint', 'accessors', { arg }, init);
}

const AccessorsEndpoint = {
    accessors,
};

export default AccessorsEndpoint;
