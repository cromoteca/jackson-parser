import client from '../../../../connect-client.default.js';
import type Entity from './JacksonEndpoint/Entity.js';
import type Product from './JacksonEndpoint/Product.js';
import type User from './JacksonEndpoint/User.js';
import { type EndpointRequestInit } from '@vaadin/hilla-frontend';

async function dummy(entity: Entity, user: User, product: Product, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.JacksonEndpoint', 'dummy', { entity, user, product }, init);
}

const JacksonEndpoint = {
    dummy,
};

export default JacksonEndpoint;
