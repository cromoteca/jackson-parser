import client from '../../../../connect-client.default.js';
import type Page from '../../../../org/springframework/data/domain/Page.js';
import type Flux from '../../../../reactor/core/publisher/Flux.js';
import type Inner from './ExternalEntitiesEndpoint/Inner.js';
import type { EndpointRequestInit } from '@hilla/frontend';

async function flux(argument: Flux<string[]>, init?: EndpointRequestInit): Promise<void> {
    return client.call('dev.hilla.generator.basic.ExternalEntitiesEndpoint', 'flux', { argument }, init);
}

async function inner(argument: Inner<string>, init?: EndpointRequestInit): Promise<void> {
    return client.call('dev.hilla.generator.basic.ExternalEntitiesEndpoint', 'inner', { argument }, init);
}

async function page(argument: Page<string[]>, init?: EndpointRequestInit): Promise<void> {
    return client.call('dev.hilla.generator.basic.ExternalEntitiesEndpoint', 'page', { argument }, init);
}

const ExternalEntitiesEndpoint = {
    flux,
    inner,
    page,
};

export default ExternalEntitiesEndpoint;
