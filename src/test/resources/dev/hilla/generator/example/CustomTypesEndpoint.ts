import { MyLibraryType } from '../../../../com/example/library/MyLibraryType.js';
import client from '../../../../connect-client.default.js';
import { MyCustomType } from './CustomTypesEndpoint/MyCustomType.js';
import { MyOtherType } from './entities/MyOtherType.js';
import { EndpointRequestInit } from '@hilla/frontend';

const externalLibraryType = async (argument: MyLibraryType, init?: EndpointRequestInit): Promise<MyLibraryType> => {
    return client.call('dev.hilla.generator.example.CustomTypesEndpoint', 'externalLibraryType', { argument }, init);
};

const fromArrayOfCustomsToCustom = async (argument: MyOtherType[], init?: EndpointRequestInit): Promise<MyOtherType> => {
    return client.call('dev.hilla.generator.example.CustomTypesEndpoint', 'fromArrayOfCustomsToCustom', { argument }, init);
};

const fromCustomToListOfCustoms = async (argument: MyCustomType, init?: EndpointRequestInit): Promise<MyCustomType[]> => {
    return client.call('dev.hilla.generator.example.CustomTypesEndpoint', 'fromCustomToListOfCustoms', { argument }, init);
};

const CustomTypesEndpoint = {
    externalLibraryType,
    fromArrayOfCustomsToCustom,
    fromCustomToListOfCustoms,
};

export default CustomTypesEndpoint;
