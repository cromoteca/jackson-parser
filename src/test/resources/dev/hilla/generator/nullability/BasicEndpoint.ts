import client from '../../../../connect-client.default.js';
import type { EndpointRequestInit } from '@hilla/frontend';

const box: { (n: number, init?: EndpointRequestInit): Promise<number | undefined> } = async (n, init) => {
    return client.call('dev.hilla.generator.nullability.BasicEndpoint', 'box', { n }, init);
};

const nonNullListOfNonNullStrings: { (argument: string[], init?: EndpointRequestInit): Promise<void> } = async (argument, init) => {
    return client.call('dev.hilla.generator.nullability.BasicEndpoint', 'nonNullListOfNonNullStrings', { argument }, init);
};

const notNull: { (init?: EndpointRequestInit): Promise<string> } = async (init) => {
    return client.call('dev.hilla.generator.nullability.BasicEndpoint', 'notNull', {}, init);
};

const BasicEndpoint = {
    box,
    nonNullListOfNonNullStrings,
    notNull,
};

export default BasicEndpoint;
