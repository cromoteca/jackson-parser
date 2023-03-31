declare type EndpointRequestInit = {};
declare function _call(endpoint: string, method: string, params?: any, __init?: EndpointRequestInit): Promise<any>;
declare const _client: { call: typeof _call };

const fromStringToString = async (argument: string, init?: EndpointRequestInit): Promise<string> => {
    return _client.call('dev.hilla.generator.example.BasicEndpoint', 'fromStringToString', { argument }, init);
};

const fromNothingToListOfStrings = async (init?: EndpointRequestInit): Promise<string[]> => {
    return _client.call('dev.hilla.generator.example.BasicEndpoint', 'fromNothingToListOfStrings', {}, init);
};

const BasicEndpoint = {
    fromStringToString,
    fromNothingToListOfStrings,
};

export default BasicEndpoint;
