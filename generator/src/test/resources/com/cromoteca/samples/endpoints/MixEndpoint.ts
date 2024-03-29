import client from '../../../../connect-client.default.js';
import type MyType from './MixEndpoint/MyType.js';
import { type EndpointRequestInit, type Subscription } from '@vaadin/hilla-frontend';

async function expose(arg: MyType | undefined, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.MixEndpoint', 'expose', { arg }, init);
}

function flux(count: number): Subscription<MyType> {
    return client.subscribe('com.cromoteca.samples.endpoints.MixEndpoint', 'flux', { count });
}

const MixEndpoint = {
    expose,
    flux,
};

export default MixEndpoint;
