import client3 from '../../../../connect-client.default.js';
import type EndpointRequestInit from './NameClashesEndpoint/EndpointRequestInit.js';
import type MyOtherType1 from './NameClashesEndpoint/MyOtherType.js';
import type MyOtherType from './entities/MyOtherType.js';
import type { EndpointRequestInit as EndpointRequestInit1 } from '@hilla/frontend';

async function clashWithEndpointRequestInitAndClientName(client: EndpointRequestInit, init?: EndpointRequestInit1): Promise<void> {
    return client3.call('com.cromoteca.samples.endpoints.NameClashesEndpoint', 'clashWithEndpointRequestInitAndClientName', { client }, init);
}

async function doubleClient(client1: string, client2: string, init?: EndpointRequestInit1): Promise<string> {
    return client3.call('com.cromoteca.samples.endpoints.NameClashesEndpoint', 'doubleClient', { client1, client2 }, init);
}

async function fromTypeToTypeWithSameName(argument: MyOtherType, init?: EndpointRequestInit1): Promise<Array<MyOtherType1>> {
    return client3.call('com.cromoteca.samples.endpoints.NameClashesEndpoint', 'fromTypeToTypeWithSameName', { argument }, init);
}

async function initParamNameUsed(init: string, init1: string, _init: string, __init?: EndpointRequestInit1): Promise<void> {
    return client3.call('com.cromoteca.samples.endpoints.NameClashesEndpoint', 'initParamNameUsed', { init, init1, _init }, __init);
}

const NameClashesEndpoint = {
    clashWithEndpointRequestInitAndClientName,
    doubleClient,
    fromTypeToTypeWithSameName,
    initParamNameUsed,
};

export default NameClashesEndpoint;
