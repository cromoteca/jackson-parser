interface EndpointRequestInit {
    signal?: AbortSignal | null;
}

export default class ConnectClient {
    call(endpoint: string, method: string, params?: any, init?: EndpointRequestInit): Promise<any>;
}
