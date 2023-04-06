import client from '../../../../connect-client.default.js';
import type { EndpointRequestInit } from '@hilla/frontend';

async function box(n: number, init?: EndpointRequestInit): Promise<number | undefined> {
    return client.call('com.cromoteca.generator.nullability.BasicEndpoint', 'box', { n }, init);
}

async function nonNullListOfNonNullStrings(argument: string[], init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.generator.nullability.BasicEndpoint', 'nonNullListOfNonNullStrings', { argument }, init);
}

async function notNull(init?: EndpointRequestInit): Promise<string> {
    return client.call('com.cromoteca.generator.nullability.BasicEndpoint', 'notNull', {}, init);
}

const BasicEndpoint = {
    box,
    nonNullListOfNonNullStrings,
    notNull,
};

export default BasicEndpoint;
