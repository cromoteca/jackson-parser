import { ConnectClient, type ConnectClientOptions } from '@vaadin/hilla-frontend';
import { type EndpointRequestInit, type Subscription } from "@vaadin/hilla-frontend/Connect.js";

// @ts-ignore
BigInt.prototype.toJSON = function () {
    return this.toString();
};

class MyConnectClient extends ConnectClient {
    public constructor(options: ConnectClientOptions = {}) {
        super(options);
    }

    async call<T>(endpoint: string, method: string, params?: Record<string, unknown>, __init?: EndpointRequestInit): Promise<T> {
        return super.call(endpoint.replace(/.*[.$]/, ''), method, params, __init);
    }

    subscribe<T>(endpoint: string, method: string, params?: Record<string, unknown>): Subscription<T> {
        return super.subscribe(endpoint.replace(/.*[.$]/, ''), method, params);
    }
}

const client = new MyConnectClient({ prefix: 'connect' });

export default client;
