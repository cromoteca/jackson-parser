import client from '../../../connect-client.default.js';
import type Simple from './Simple.js';
import { type EndpointRequestInit } from '@vaadin/hilla-frontend';

async function simple(argument: Simple | undefined, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.DependencyEndpoint', 'simple', { argument }, init);
}

const DependencyEndpoint = {
    simple,
};

export default DependencyEndpoint;
