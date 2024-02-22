import client from '../../../../connect-client.default.js';
import type Level2 from './ReviverEndpoint/Level2.js';
import { type EndpointRequestInit } from '@vaadin/hilla-frontend';

async function bigInteger(arg: BigInt, init?: EndpointRequestInit): Promise<BigInt> {
    return client.call('com.cromoteca.samples.endpoints.ReviverEndpoint', 'bigInteger', { arg }, init);
}

async function date(arg: Date, init?: EndpointRequestInit): Promise<Date> {
    return client.call('com.cromoteca.samples.endpoints.ReviverEndpoint', 'date', { arg }, init);
}

async function map(arg: Map<number, Array<Date>>, init?: EndpointRequestInit): Promise<Map<number, Array<Date>>> {
    return client.call('com.cromoteca.samples.endpoints.ReviverEndpoint', 'map', { arg }, init);
}

async function nullableDate(arg: Date | undefined, init?: EndpointRequestInit): Promise<Date | undefined> {
    return client.call('com.cromoteca.samples.endpoints.ReviverEndpoint', 'nullableDate', { arg }, init);
}

async function reviveProperties(arg: Level2, init?: EndpointRequestInit): Promise<Level2> {
    return client.call('com.cromoteca.samples.endpoints.ReviverEndpoint', 'reviveProperties', { arg }, init);
}

const ReviverEndpoint = {
    bigInteger,
    date,
    map,
    nullableDate,
    reviveProperties,
};

export default ReviverEndpoint;
