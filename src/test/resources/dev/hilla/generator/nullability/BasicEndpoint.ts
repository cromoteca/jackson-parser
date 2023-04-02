import client from '../../../../connect-client.default.js';
import type { EndpointRequestInit } from '@hilla/frontend';

const box: { (n: number, init?: EndpointRequestInit): Promise<number | undefined> } = async (n, init) => {
    return client.call('dev.hilla.generator.nullability.BasicEndpoint', 'box', { n }, init);
};

const BasicEndpoint = {
    box,
};

export default BasicEndpoint;
