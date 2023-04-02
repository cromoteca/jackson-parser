import client from '../../../../connect-client.default.js';
import type { EndpointRequestInit } from '@hilla/frontend';

const fromArrayOfStringsToNothing: { (argument: string[], init?: EndpointRequestInit): Promise<void> } = async (argument, init) => {
    return client.call('dev.hilla.generator.basic.BasicEndpoint', 'fromArrayOfStringsToNothing', { argument }, init);
};

const fromNothingToListOfStrings: { (init?: EndpointRequestInit): Promise<string[]> } = async (init) => {
    return client.call('dev.hilla.generator.basic.BasicEndpoint', 'fromNothingToListOfStrings', {}, init);
};

const fromPrimitiveToPrimitiveArray: { (argument: number, init?: EndpointRequestInit): Promise<number[]> } = async (argument, init) => {
    return client.call('dev.hilla.generator.basic.BasicEndpoint', 'fromPrimitiveToPrimitiveArray', { argument }, init);
};

const fromStringToString: { (argument: string, init?: EndpointRequestInit): Promise<string> } = async (argument, init) => {
    return client.call('dev.hilla.generator.basic.BasicEndpoint', 'fromStringToString', { argument }, init);
};

const fromTwoToMapOfStringsToList: { (init: string, end: string, _init?: EndpointRequestInit): Promise<Map<string, string[]>> } = async (init, end, _init) => {
    return client.call('dev.hilla.generator.basic.BasicEndpoint', 'fromTwoToMapOfStringsToList', { init, end }, _init);
};

const BasicEndpoint = {
    fromArrayOfStringsToNothing,
    fromNothingToListOfStrings,
    fromPrimitiveToPrimitiveArray,
    fromStringToString,
    fromTwoToMapOfStringsToList,
};

export default BasicEndpoint;
