import client from '../../../../../connect-client.default.js';
import type Nulls from './NullableEndpoint/Nulls.js';
import { type EndpointRequestInit } from '@vaadin/hilla-frontend';

async function box(n: number, init?: EndpointRequestInit): Promise<number | undefined> {
    return client.call('com.cromoteca.samples.nullable.endpoints.NullableEndpoint', 'box', { n }, init);
}

async function nonNullArrayOfNonNullStrings(argument: Array<string>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.nullable.endpoints.NullableEndpoint', 'nonNullArrayOfNonNullStrings', { argument }, init);
}

async function nonNullArrayOfNullableStrings(argument: Array<string | undefined>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.nullable.endpoints.NullableEndpoint', 'nonNullArrayOfNullableStrings', { argument }, init);
}

async function nonNullListOfNonNullStrings(argument: Array<string>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.nullable.endpoints.NullableEndpoint', 'nonNullListOfNonNullStrings', { argument }, init);
}

async function notNull(init?: EndpointRequestInit): Promise<string> {
    return client.call('com.cromoteca.samples.nullable.endpoints.NullableEndpoint', 'notNull', {}, init);
}

async function nullableArrayOfNonNullStrings(argument: Array<string> | undefined, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.nullable.endpoints.NullableEndpoint', 'nullableArrayOfNonNullStrings', { argument }, init);
}

async function nullableArrayOfNullableStrings(argument: Array<string | undefined> | undefined, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.nullable.endpoints.NullableEndpoint', 'nullableArrayOfNullableStrings', { argument }, init);
}

async function nulls(argument: Nulls | undefined, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.nullable.endpoints.NullableEndpoint', 'nulls', { argument }, init);
}

const NullableEndpoint = {
    box,
    nonNullArrayOfNonNullStrings,
    nonNullArrayOfNullableStrings,
    nonNullListOfNonNullStrings,
    notNull,
    nullableArrayOfNonNullStrings,
    nullableArrayOfNullableStrings,
    nulls,
};

export default NullableEndpoint;
