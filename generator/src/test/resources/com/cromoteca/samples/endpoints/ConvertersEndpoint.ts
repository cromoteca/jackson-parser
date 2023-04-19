import client from '../../../../connect-client.default.js';
import type HasBeenConverted from './ConvertersEndpoint/HasBeenConverted.js';
import { type EndpointRequestInit } from '@hilla/frontend';

async function getConverted(init?: EndpointRequestInit): Promise<HasBeenConverted> {
    return client.call('com.cromoteca.samples.endpoints.ConvertersEndpoint', 'getConverted', {}, init);
}

const ConvertersEndpoint = {
    getConverted,
};

export default ConvertersEndpoint;
