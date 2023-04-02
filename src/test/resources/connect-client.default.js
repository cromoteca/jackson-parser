export class ConnectClient {
    async call(_endpoint, _method, params, _init) {
        return params; // to get the 'any' as Homer wanted
    }
}

export default client = new ConnectClient();
