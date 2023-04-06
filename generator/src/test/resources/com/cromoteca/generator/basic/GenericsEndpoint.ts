import client from '../../../../connect-client.default.js';
import type GenericClass from './GenericsEndpoint/GenericClass.js';
import type { EndpointRequestInit } from '@hilla/frontend';

async function opt(init?: EndpointRequestInit): Promise<string> {
    return client.call('com.cromoteca.generator.basic.GenericsEndpoint', 'opt', {}, init);
}

async function toList<T>(value: Array<T>, init?: EndpointRequestInit): Promise<Array<T>> {
    return client.call('com.cromoteca.generator.basic.GenericsEndpoint', 'toList', { value }, init);
}

async function toListOfMyType(value: GenericClass<string>, init?: EndpointRequestInit): Promise<Array<GenericClass<string>>> {
    return client.call('com.cromoteca.generator.basic.GenericsEndpoint', 'toListOfMyType', { value }, init);
}

async function toMap<T, U>(key: T, value: GenericClass<U>, init?: EndpointRequestInit): Promise<Map<T, GenericClass<U>>> {
    return client.call('com.cromoteca.generator.basic.GenericsEndpoint', 'toMap', { key, value }, init);
}

async function toNestedGenericClass<T>(value: GenericClass<T>, init?: EndpointRequestInit): Promise<GenericClass<GenericClass<T>>> {
    return client.call('com.cromoteca.generator.basic.GenericsEndpoint', 'toNestedGenericClass', { value }, init);
}

async function tooComplex<T, U, V>(p1: GenericClass<Array<unknown>>, p2: Map<U, GenericClass<GenericClass<GenericClass<unknown>>>>, init?: EndpointRequestInit): Promise<Map<GenericClass<Array<T>>, Map<unknown, GenericClass<GenericClass<GenericClass<V>>>>>> {
    return client.call('com.cromoteca.generator.basic.GenericsEndpoint', 'tooComplex', { p1, p2 }, init);
}

const GenericsEndpoint = {
    opt,
    toList,
    toListOfMyType,
    toMap,
    toNestedGenericClass,
    tooComplex,
};

export default GenericsEndpoint;
