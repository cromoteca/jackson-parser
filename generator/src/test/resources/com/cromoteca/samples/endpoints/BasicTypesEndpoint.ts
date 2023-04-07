import client from '../../../../connect-client.default.js';
import type { EndpointRequestInit } from '@hilla/frontend';

async function booleanArrayType(arg: Array<boolean>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'booleanArrayType', { arg }, init);
}

async function booleanListType(arg: Array<boolean>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'booleanListType', { arg }, init);
}

async function booleanMapType(arg: Map<boolean, boolean>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'booleanMapType', { arg }, init);
}

async function booleanObject(arg: boolean, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'booleanObject', { arg }, init);
}

async function booleanSetType(arg: Array<boolean>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'booleanSetType', { arg }, init);
}

async function booleanType(arg: boolean, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'booleanType', { arg }, init);
}

async function byteArrayType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'byteArrayType', { arg }, init);
}

async function byteListType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'byteListType', { arg }, init);
}

async function byteMapType(arg: Map<number, number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'byteMapType', { arg }, init);
}

async function byteObject(arg: number, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'byteObject', { arg }, init);
}

async function byteSetType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'byteSetType', { arg }, init);
}

async function byteType(arg: number, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'byteType', { arg }, init);
}

async function charArrayType(arg: Array<string>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'charArrayType', { arg }, init);
}

async function charListType(arg: Array<string>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'charListType', { arg }, init);
}

async function charMapType(arg: Map<string, string>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'charMapType', { arg }, init);
}

async function charSetType(arg: Array<string>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'charSetType', { arg }, init);
}

async function charType(arg: string, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'charType', { arg }, init);
}

async function characterObject(arg: string, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'characterObject', { arg }, init);
}

async function doubleArrayType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'doubleArrayType', { arg }, init);
}

async function doubleListType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'doubleListType', { arg }, init);
}

async function doubleMapType(arg: Map<number, number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'doubleMapType', { arg }, init);
}

async function doubleObject(arg: number, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'doubleObject', { arg }, init);
}

async function doubleSetType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'doubleSetType', { arg }, init);
}

async function doubleType(arg: number, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'doubleType', { arg }, init);
}

async function floatArrayType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'floatArrayType', { arg }, init);
}

async function floatListType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'floatListType', { arg }, init);
}

async function floatMapType(arg: Map<number, number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'floatMapType', { arg }, init);
}

async function floatObject(arg: number, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'floatObject', { arg }, init);
}

async function floatSetType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'floatSetType', { arg }, init);
}

async function floatType(arg: number, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'floatType', { arg }, init);
}

async function intArrayType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'intArrayType', { arg }, init);
}

async function intListType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'intListType', { arg }, init);
}

async function intMapType(arg: Map<number, number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'intMapType', { arg }, init);
}

async function intSetType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'intSetType', { arg }, init);
}

async function intType(arg: number, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'intType', { arg }, init);
}

async function integerObject(arg: number, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'integerObject', { arg }, init);
}

async function longArrayType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'longArrayType', { arg }, init);
}

async function longListType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'longListType', { arg }, init);
}

async function longMapType(arg: Map<number, number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'longMapType', { arg }, init);
}

async function longObject(arg: number, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'longObject', { arg }, init);
}

async function longSetType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'longSetType', { arg }, init);
}

async function longType(arg: number, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'longType', { arg }, init);
}

async function shortArrayType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'shortArrayType', { arg }, init);
}

async function shortListType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'shortListType', { arg }, init);
}

async function shortMapType(arg: Map<number, number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'shortMapType', { arg }, init);
}

async function shortObject(arg: number, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'shortObject', { arg }, init);
}

async function shortSetType(arg: Array<number>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'shortSetType', { arg }, init);
}

async function shortType(arg: number, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'shortType', { arg }, init);
}

async function stringArrayType(arg: Array<string>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'stringArrayType', { arg }, init);
}

async function stringListType(arg: Array<string>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'stringListType', { arg }, init);
}

async function stringMapType(arg: Map<string, string>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'stringMapType', { arg }, init);
}

async function stringSetType(arg: Array<string>, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'stringSetType', { arg }, init);
}

async function stringType(arg: string, init?: EndpointRequestInit): Promise<void> {
    return client.call('com.cromoteca.samples.endpoints.BasicTypesEndpoint', 'stringType', { arg }, init);
}

const BasicTypesEndpoint = {
    booleanArrayType,
    booleanListType,
    booleanMapType,
    booleanObject,
    booleanSetType,
    booleanType,
    byteArrayType,
    byteListType,
    byteMapType,
    byteObject,
    byteSetType,
    byteType,
    charArrayType,
    charListType,
    charMapType,
    charSetType,
    charType,
    characterObject,
    doubleArrayType,
    doubleListType,
    doubleMapType,
    doubleObject,
    doubleSetType,
    doubleType,
    floatArrayType,
    floatListType,
    floatMapType,
    floatObject,
    floatSetType,
    floatType,
    intArrayType,
    intListType,
    intMapType,
    intSetType,
    intType,
    integerObject,
    longArrayType,
    longListType,
    longMapType,
    longObject,
    longSetType,
    longType,
    shortArrayType,
    shortListType,
    shortMapType,
    shortObject,
    shortSetType,
    shortType,
    stringArrayType,
    stringListType,
    stringMapType,
    stringSetType,
    stringType,
};

export default BasicTypesEndpoint;
