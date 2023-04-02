import client from '../../../../connect-client.default.js';
import type { GenericClass } from './GenericsEndpoint/GenericClass.js';
import type { EndpointRequestInit } from '@hilla/frontend';

const toList: { <T>(value: T[], init?: EndpointRequestInit): Promise<T[]> } = async (value, init) => {
    return client.call('dev.hilla.generator.basic.GenericsEndpoint', 'toList', { value }, init);
};

const toListOfMyType: { (value: GenericClass<string>, init?: EndpointRequestInit): Promise<GenericClass<string>[]> } = async (value, init) => {
    return client.call('dev.hilla.generator.basic.GenericsEndpoint', 'toListOfMyType', { value }, init);
};

const toMap: { <T, U>(key: T, value: GenericClass<U>, init?: EndpointRequestInit): Promise<Map<T, GenericClass<U>>> } = async (key, value, init) => {
    return client.call('dev.hilla.generator.basic.GenericsEndpoint', 'toMap', { key, value }, init);
};

const toNestedGenericClass: { <T>(value: GenericClass<T>, init?: EndpointRequestInit): Promise<GenericClass<GenericClass<T>>> } = async (value, init) => {
    return client.call('dev.hilla.generator.basic.GenericsEndpoint', 'toNestedGenericClass', { value }, init);
};

const tooComplex: { <T, U, V>(p1: GenericClass<unknown[]>, p2: Map<U, GenericClass<GenericClass<GenericClass<unknown>>>>, init?: EndpointRequestInit): Promise<Map<GenericClass<T[]>, Map<unknown, GenericClass<GenericClass<GenericClass<V>>>>>> } = async (p1, p2, init) => {
    return client.call('dev.hilla.generator.basic.GenericsEndpoint', 'tooComplex', { p1, p2 }, init);
};

const GenericsEndpoint = {
    toList,
    toListOfMyType,
    toMap,
    toNestedGenericClass,
    tooComplex,
};

export default GenericsEndpoint;
