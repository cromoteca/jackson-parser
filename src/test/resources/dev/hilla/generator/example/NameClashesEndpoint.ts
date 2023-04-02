import client3 from '../../../../connect-client.default.js';
import type { EndpointRequestInit as EndpointRequestInit1 } from './NameClashesEndpoint/EndpointRequestInit.js';
import type { MyOtherType as MyOtherType1 } from './NameClashesEndpoint/MyOtherType.js';
import type { MyOtherType } from './entities/MyOtherType.js';
import type { EndpointRequestInit } from '@hilla/frontend';

const clashWithEndpointRequestInitAndClientName: { (client: EndpointRequestInit1, init?: EndpointRequestInit): Promise<void> } = async (client, init) => {
    return client3.call('dev.hilla.generator.example.NameClashesEndpoint', 'clashWithEndpointRequestInitAndClientName', { client }, init);
};

const doubleClient: { (client1: string, client2: string, init?: EndpointRequestInit): Promise<string> } = async (client1, client2, init) => {
    return client3.call('dev.hilla.generator.example.NameClashesEndpoint', 'doubleClient', { client1, client2 }, init);
};

const fromTypeToTypeWithSameName: { (argument: MyOtherType, init?: EndpointRequestInit): Promise<MyOtherType1[]> } = async (argument, init) => {
    return client3.call('dev.hilla.generator.example.NameClashesEndpoint', 'fromTypeToTypeWithSameName', { argument }, init);
};

const NameClashesEndpoint = {
    clashWithEndpointRequestInitAndClientName,
    doubleClient,
    fromTypeToTypeWithSameName,
};

export default NameClashesEndpoint;
