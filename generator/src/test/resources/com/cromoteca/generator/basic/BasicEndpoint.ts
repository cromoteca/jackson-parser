import client from '../../../../connect-client.default.js';
import type { EndpointRequestInit } from '@hilla/frontend';

async function fromArrayOfStringsToNothing(argument: string[], init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.generator.basic.BasicEndpoint', 'fromArrayOfStringsToNothing', { argument }, init);
}

async function fromNothingToListOfStrings(init?: EndpointRequestInit): Promise<string[]> {
    return client.call('com.cromoteca.generator.basic.BasicEndpoint', 'fromNothingToListOfStrings', {}, init);
}

async function fromPrimitiveToPrimitiveArray(argument: number, init?: EndpointRequestInit): Promise<number[]> {
    return client.call('com.cromoteca.generator.basic.BasicEndpoint', 'fromPrimitiveToPrimitiveArray', { argument }, init);
}

async function fromStringToString(argument: string, init?: EndpointRequestInit): Promise<string> {
    return client.call('com.cromoteca.generator.basic.BasicEndpoint', 'fromStringToString', { argument }, init);
}

async function fromTwoToMapOfStringsToList(init: string, end: string, _init?: EndpointRequestInit): Promise<Map<string, string[]>> {
    return client.call('com.cromoteca.generator.basic.BasicEndpoint', 'fromTwoToMapOfStringsToList', { init, end }, _init);
}

async function threeParameters(a: number, b: number, c: number, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.generator.basic.BasicEndpoint', 'threeParameters', { a, b, c }, init);
}

const BasicEndpoint = {
    fromArrayOfStringsToNothing,
    fromNothingToListOfStrings,
    fromPrimitiveToPrimitiveArray,
    fromStringToString,
    fromTwoToMapOfStringsToList,
    threeParameters,
};

export default BasicEndpoint;
