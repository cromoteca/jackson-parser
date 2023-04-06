import client3 from '../../../../connect-client.default.js';
import type EndpointRequestInit1 from './NameClashesEndpoint/EndpointRequestInit.js';
import type MyOtherType1 from './NameClashesEndpoint/MyOtherType.js';
import type MyOtherType from './entities/MyOtherType.js';
import type { EndpointRequestInit } from '@hilla/frontend';

async function clashWithEndpointRequestInitAndClientName(client: EndpointRequestInit1, init?: EndpointRequestInit): Promise<void> {
    return client3.call('com.cromoteca.generator.basic.NameClashesEndpoint', 'clashWithEndpointRequestInitAndClientName', { client }, init);
}

async function doubleClient(client1: string, client2: string, init?: EndpointRequestInit): Promise<string> {
    return client3.call('com.cromoteca.generator.basic.NameClashesEndpoint', 'doubleClient', { client1, client2 }, init);
}

async function fromTypeToTypeWithSameName(argument: MyOtherType, init?: EndpointRequestInit): Promise<Array<MyOtherType1>> {
    return client3.call('com.cromoteca.generator.basic.NameClashesEndpoint', 'fromTypeToTypeWithSameName', { argument }, init);
}

const NameClashesEndpoint = {
    clashWithEndpointRequestInitAndClientName,
    doubleClient,
    fromTypeToTypeWithSameName,
};

export default NameClashesEndpoint;