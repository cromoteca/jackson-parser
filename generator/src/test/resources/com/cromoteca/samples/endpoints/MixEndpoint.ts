import client from '../../../../connect-client.default.js';
import type MyType from './MixEndpoint/MyType.js';
import type { EndpointRequestInit } from '@hilla/frontend';

async function expose(arg: MyType | undefined, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.MixEndpoint', 'expose', { arg }, init);
}

const MixEndpoint = {
    expose,
};

export default MixEndpoint;
