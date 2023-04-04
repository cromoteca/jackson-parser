import client from '../../../../connect-client.default.js';
import type GenericClass from './GenericsEndpoint/GenericClass.js';
import type { EndpointRequestInit } from '@hilla/frontend';

async function toList<T>(value: T[], init?: EndpointRequestInit): Promise<T[]> {
    return client.call('dev.hilla.generator.basic.GenericsEndpoint', 'toList', { value }, init);
}

async function toListOfMyType(value: GenericClass<string>, init?: EndpointRequestInit): Promise<GenericClass<string>[]> {
    return client.call('dev.hilla.generator.basic.GenericsEndpoint', 'toListOfMyType', { value }, init);
}

async function toMap<T, U>(key: T, value: GenericClass<U>, init?: EndpointRequestInit): Promise<Map<T, GenericClass<U>>> {
    return client.call('dev.hilla.generator.basic.GenericsEndpoint', 'toMap', { key, value }, init);
}

async function toNestedGenericClass<T>(value: GenericClass<T>, init?: EndpointRequestInit): Promise<GenericClass<GenericClass<T>>> {
    return client.call('dev.hilla.generator.basic.GenericsEndpoint', 'toNestedGenericClass', { value }, init);
}

async function toString(object: unknown, init?: EndpointRequestInit): Promise<string> {
    return client.call('dev.hilla.generator.basic.GenericsEndpoint', 'toString', { object }, init);
}

async function tooComplex<T, U, V>(p1: GenericClass<unknown[]>, p2: Map<U, GenericClass<GenericClass<GenericClass<unknown>>>>, init?: EndpointRequestInit): Promise<Map<GenericClass<T[]>, Map<unknown, GenericClass<GenericClass<GenericClass<V>>>>>> {
    return client.call('dev.hilla.generator.basic.GenericsEndpoint', 'tooComplex', { p1, p2 }, init);
}

const GenericsEndpoint = {
    toList,
    toListOfMyType,
    toMap,
    toNestedGenericClass,
    toString,
    tooComplex,
};

export default GenericsEndpoint;
