import {ConnectClient, ConnectClientOptions} from '@hilla/frontend';
import {EndpointRequestInit} from "@hilla/frontend/Connect.js";

class MyConnectClient extends ConnectClient {
    public constructor(options: ConnectClientOptions = {}) {
        super(options);
    }

    async call(endpoint: string, method: string, params?: any, __init?: EndpointRequestInit) {
        return super.call(endpoint.replace(/.*[.$]/, ''), method, params, __init);
    }

    subscribe(endpoint: string, method: string, params?: any) {
        return super.subscribe(endpoint.replace(/.*[.$]/, ''), method, params);
    }
}

const client = new MyConnectClient({prefix: 'connect'});

export default client;
