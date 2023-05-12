import { ConnectClient, type ConnectClientOptions } from '@hilla/frontend';
import { type EndpointRequestInit, type Subscription } from "@hilla/frontend/Connect.js";

// @ts-ignore
BigInt.prototype.toJSON = function () {
    return this.toString();
};

class MyConnectClient extends ConnectClient {
    public constructor(options: ConnectClientOptions = {}) {
        super(options);
    }

    async call<P, R>(endpoint: string, method: string, params?: P, __init?: EndpointRequestInit): Promise<R> {
        return super.call(endpoint.replace(/.*[.$]/, ''), method, params, __init);
    }

    subscribe<P, R>(endpoint: string, method: string, params?: P): Subscription<R> {
        return super.subscribe(endpoint.replace(/.*[.$]/, ''), method, params);
    }
}

const client = new MyConnectClient({ prefix: 'connect' });

export default client;
