import client from '../../../../connect-client.default.js';
import type MyLibraryType from '../../../example/library/MyLibraryType.js';
import type MyCustomType from './CustomTypesEndpoint/MyCustomType.js';
import type MyOtherType from './entities/MyOtherType.js';
import { type EndpointRequestInit } from '@hilla/frontend';

async function externalLibraryType(argument: MyLibraryType, init?: EndpointRequestInit): Promise<MyLibraryType> {
    return client.call('com.cromoteca.samples.endpoints.CustomTypesEndpoint', 'externalLibraryType', { argument }, init);
}

async function fromArrayOfCustomsToCustom(argument: Array<MyOtherType>, init?: EndpointRequestInit): Promise<MyOtherType> {
    return client.call('com.cromoteca.samples.endpoints.CustomTypesEndpoint', 'fromArrayOfCustomsToCustom', { argument }, init);
}

async function fromCustomToListOfCustoms(argument: MyCustomType, init?: EndpointRequestInit): Promise<Array<MyCustomType>> {
    return client.call('com.cromoteca.samples.endpoints.CustomTypesEndpoint', 'fromCustomToListOfCustoms', { argument }, init);
}

const CustomTypesEndpoint = {
    externalLibraryType,
    fromArrayOfCustomsToCustom,
    fromCustomToListOfCustoms,
};

export default CustomTypesEndpoint;
