declare type EndpointRequestInit = {};
declare function _call(endpoint: string, method: string, params?: any, __init?: EndpointRequestInit): Promise<any>;
declare const _client: { call: typeof _call };

const simpleMethod = async (argument: string, init?: EndpointRequestInit): Promise<string> => {
    return _client.call('dev.hilla.generator.example.BasicEndpoint', 'simpleMethod', { argument }, init);
};

const BasicEndpoint = {
    simpleMethod,
};

export default BasicEndpoint;
