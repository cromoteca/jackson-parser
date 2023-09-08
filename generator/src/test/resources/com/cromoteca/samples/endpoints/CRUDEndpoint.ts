import client from '../../../../connect-client.default.js';
import type MyLibraryType from '../../../example/library/MyLibraryType.js';
import { type EndpointRequestInit } from '@hilla/frontend';

async function doSomethingWith(t: MyLibraryType, init?: EndpointRequestInit): Promise<MyLibraryType> {
    return client.call('com.cromoteca.samples.endpoints.CRUDEndpoint', 'doSomethingWith', { t }, init);
}

async function getAll(init?: EndpointRequestInit): Promise<Array<MyLibraryType>> {
    return client.call('com.cromoteca.samples.endpoints.CRUDEndpoint', 'getAll', {}, init);
}

async function remove(t: MyLibraryType, init?: EndpointRequestInit): Promise<MyLibraryType> {
    return client.call('com.cromoteca.samples.endpoints.CRUDEndpoint', 'remove', { t }, init);
}

async function saveOrUpdate(t: MyLibraryType, init?: EndpointRequestInit): Promise<MyLibraryType> {
    return client.call('com.cromoteca.samples.endpoints.CRUDEndpoint', 'saveOrUpdate', { t }, init);
}

const CRUDEndpoint = {
    doSomethingWith,
    getAll,
    remove,
    saveOrUpdate,
};

export default CRUDEndpoint;
