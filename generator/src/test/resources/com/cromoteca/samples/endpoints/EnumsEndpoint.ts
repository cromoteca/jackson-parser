import client from '../../../../connect-client.default.js';
import type Color from './EnumsEndpoint/Color.js';
import type { EndpointRequestInit } from '@hilla/frontend';

async function getRed(init?: EndpointRequestInit): Promise<Color> {
    return client.call('com.cromoteca.samples.endpoints.EnumsEndpoint', 'getRed', {}, init);
}

const EnumsEndpoint = {
    getRed,
};

export default EnumsEndpoint;
