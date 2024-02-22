import client from '../../../../connect-client.default.js';
import { type EndpointRequestInit } from '@vaadin/hilla-frontend';

async function hello(init?: EndpointRequestInit): Promise<string> {
    return client.call('com.cromoteca.samples.spring.SpringEndpoint', 'hello', {}, init);
}

const SpringEndpoint = {
    hello,
};

export default SpringEndpoint;
