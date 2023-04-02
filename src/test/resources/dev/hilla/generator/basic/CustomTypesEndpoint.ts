import type { MyLibraryType } from '../../../../com/example/library/MyLibraryType.js';
import client from '../../../../connect-client.default.js';
import type { MyCustomType } from './CustomTypesEndpoint/MyCustomType.js';
import type { MyOtherType } from './entities/MyOtherType.js';
import type { EndpointRequestInit } from '@hilla/frontend';

const externalLibraryType: { (argument: MyLibraryType, init?: EndpointRequestInit): Promise<MyLibraryType> } = async (argument, init) => {
    return client.call('dev.hilla.generator.basic.CustomTypesEndpoint', 'externalLibraryType', { argument }, init);
};

const fromArrayOfCustomsToCustom: { (argument: MyOtherType[], init?: EndpointRequestInit): Promise<MyOtherType> } = async (argument, init) => {
    return client.call('dev.hilla.generator.basic.CustomTypesEndpoint', 'fromArrayOfCustomsToCustom', { argument }, init);
};

const fromCustomToListOfCustoms: { (argument: MyCustomType, init?: EndpointRequestInit): Promise<MyCustomType[]> } = async (argument, init) => {
    return client.call('dev.hilla.generator.basic.CustomTypesEndpoint', 'fromCustomToListOfCustoms', { argument }, init);
};

const CustomTypesEndpoint = {
    externalLibraryType,
    fromArrayOfCustomsToCustom,
    fromCustomToListOfCustoms,
};

export default CustomTypesEndpoint;
