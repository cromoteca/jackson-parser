import client from '../../../../connect-client.default.js';
import type GenericClass from './GenericsEndpoint/GenericClass.js';
import { type EndpointRequestInit } from '@vaadin/hilla-frontend';

async function opt(init?: EndpointRequestInit): Promise<string | undefined> {
    return client.call('com.cromoteca.samples.endpoints.GenericsEndpoint', 'opt', {}, init);
}

async function optList(init?: EndpointRequestInit): Promise<Array<string | undefined>> {
    return client.call('com.cromoteca.samples.endpoints.GenericsEndpoint', 'optList', {}, init);
}

async function optListT<T>(argument: Array<T | undefined>, init?: EndpointRequestInit): Promise<Array<T>> {
    return client.call('com.cromoteca.samples.endpoints.GenericsEndpoint', 'optListT', { argument }, init);
}

async function toList<T>(value: Array<T>, init?: EndpointRequestInit): Promise<Array<T>> {
    return client.call('com.cromoteca.samples.endpoints.GenericsEndpoint', 'toList', { value }, init);
}

async function toListOfMyType(value: GenericClass<string>, init?: EndpointRequestInit): Promise<Array<GenericClass<string>>> {
    return client.call('com.cromoteca.samples.endpoints.GenericsEndpoint', 'toListOfMyType', { value }, init);
}

async function toMap<T, U>(key: T, value: GenericClass<U>, init?: EndpointRequestInit): Promise<Map<T, GenericClass<U>>> {
    return client.call('com.cromoteca.samples.endpoints.GenericsEndpoint', 'toMap', { key, value }, init);
}

async function toNestedGenericClass<T>(value: GenericClass<T>, init?: EndpointRequestInit): Promise<GenericClass<GenericClass<T>>> {
    return client.call('com.cromoteca.samples.endpoints.GenericsEndpoint', 'toNestedGenericClass', { value }, init);
}

async function tooComplex<T, U, V>(p1: GenericClass<Array<unknown>>, p2: Map<U, GenericClass<GenericClass<GenericClass<unknown>>>>, init?: EndpointRequestInit): Promise<Map<GenericClass<Array<T>>, Map<unknown, GenericClass<GenericClass<GenericClass<V>> | undefined>>>> {
    return client.call('com.cromoteca.samples.endpoints.GenericsEndpoint', 'tooComplex', { p1, p2 }, init);
}

const GenericsEndpoint = {
    opt,
    optList,
    optListT,
    toList,
    toListOfMyType,
    toMap,
    toNestedGenericClass,
    tooComplex,
};

export default GenericsEndpoint;
