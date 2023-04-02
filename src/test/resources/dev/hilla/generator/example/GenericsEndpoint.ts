import client from '../../../../connect-client.default.js';
import type { GenericClass } from './GenericsEndpoint/GenericClass.js';
import type { EndpointRequestInit } from '@hilla/frontend';

const toList: { <T>(value: T[], init?: EndpointRequestInit): Promise<T[]> } = async (value, init) => {
    return client.call('dev.hilla.generator.example.GenericsEndpoint', 'toList', { value }, init);
};

const toListOfMyType: { (value: GenericClass<string>, init?: EndpointRequestInit): Promise<GenericClass<string>[]> } = async (value, init) => {
    return client.call('dev.hilla.generator.example.GenericsEndpoint', 'toListOfMyType', { value }, init);
};

const toMap: { <T, U>(key: T, value: GenericClass<U>, init?: EndpointRequestInit): Promise<Map<T, GenericClass<U>>> } = async (key, value, init) => {
    return client.call('dev.hilla.generator.example.GenericsEndpoint', 'toMap', { key, value }, init);
};

const toNestedGenericClass: { <T>(value: GenericClass<T>, init?: EndpointRequestInit): Promise<GenericClass<GenericClass<T>>> } = async (value, init) => {
    return client.call('dev.hilla.generator.example.GenericsEndpoint', 'toNestedGenericClass', { value }, init);
};

const GenericsEndpoint = {
    toList,
    toListOfMyType,
    toMap,
    toNestedGenericClass,
};

export default GenericsEndpoint;
