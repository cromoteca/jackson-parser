import client from '../../../../connect-client.default.js';
import type User from './LoadersEndpoint/User.js';
import { type EndpointRequestInit } from '@vaadin/hilla-frontend';
import { type LoaderFunctionArgs } from 'react-router-dom';

async function getUser({ params }: LoaderFunctionArgs): Promise<User> {
    return client.call('com.cromoteca.samples.endpoints.LoadersEndpoint', 'getUser', { id: Number(params.id) });
}

async function getUsers(init?: EndpointRequestInit): Promise<Array<User>> {
    return client.call('com.cromoteca.samples.endpoints.LoadersEndpoint', 'getUsers', {}, init);
}

const LoadersEndpoint = {
    getUser,
    getUsers,
};

export default LoadersEndpoint;
