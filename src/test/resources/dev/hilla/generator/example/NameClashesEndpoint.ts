import client3 from '../../../../connect-client.default.js';
import { EndpointRequestInit as EndpointRequestInit1 } from './NameClashesEndpoint/EndpointRequestInit.js';
import { MyOtherType as MyOtherType1 } from './NameClashesEndpoint/MyOtherType.js';
import { MyOtherType } from './entities/MyOtherType.js';
import { EndpointRequestInit } from '@hilla/frontend';

const clashWithEndpointRequestInitAndClientName = async (client: EndpointRequestInit1, init?: EndpointRequestInit): Promise<void> => {
    return client3.call('dev.hilla.generator.example.NameClashesEndpoint', 'clashWithEndpointRequestInitAndClientName', { client }, init);
};

const doubleClient = async (client1: string, client2: string, init?: EndpointRequestInit): Promise<string> => {
    return client3.call('dev.hilla.generator.example.NameClashesEndpoint', 'doubleClient', { client1, client2 }, init);
};

const fromTypeToTypeWithSameName = async (argument: MyOtherType, init?: EndpointRequestInit): Promise<MyOtherType1[]> => {
    return client3.call('dev.hilla.generator.example.NameClashesEndpoint', 'fromTypeToTypeWithSameName', { argument }, init);
};

const NameClashesEndpoint = {
    clashWithEndpointRequestInitAndClientName,
    doubleClient,
    fromTypeToTypeWithSameName,
};

export default NameClashesEndpoint;
