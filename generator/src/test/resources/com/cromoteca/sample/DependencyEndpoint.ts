import client from '../../../connect-client.default.js';
import type Simple from './Simple.js';
import type { EndpointRequestInit } from '@hilla/frontend';

async function simple(arg0: Simple | undefined, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.DependencyEndpoint', 'simple', { arg0 }, init);
}

const DependencyEndpoint = {
    simple,
};

export default DependencyEndpoint;
