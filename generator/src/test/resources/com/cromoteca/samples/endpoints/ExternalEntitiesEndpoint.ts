import client from '../../../../connect-client.default.js';
import type Page from '../../../../org/springframework/data/domain/Page.js';
import type Flux from '../../../../reactor/core/publisher/Flux.js';
import type Simple from '../Simple.js';
import type Inner from './ExternalEntitiesEndpoint/Inner.js';
import { type EndpointRequestInit } from '@vaadin/hilla-frontend';

async function flux(argument: Flux, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.ExternalEntitiesEndpoint', 'flux', { argument }, init);
}

async function inner(argument: Inner<string>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.ExternalEntitiesEndpoint', 'inner', { argument }, init);
}

async function page(argument: Page<Array<string>>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.ExternalEntitiesEndpoint', 'page', { argument }, init);
}

async function simple(argument: Simple, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.ExternalEntitiesEndpoint', 'simple', { argument }, init);
}

const ExternalEntitiesEndpoint = {
    flux,
    inner,
    page,
    simple,
};

export default ExternalEntitiesEndpoint;
