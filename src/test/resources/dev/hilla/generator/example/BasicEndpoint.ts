declare type EndpointRequestInit = {};
declare function _call(endpoint: string, method: string, params?: any, __init?: EndpointRequestInit): Promise<any>;
declare const _client: { call: typeof _call };

const fromArrayOfStringsToNothing = async (argument: string[], init?: EndpointRequestInit): Promise<void> => {
    return _client.call('dev.hilla.generator.example.BasicEndpoint', 'fromArrayOfStringsToNothing', { argument }, init);
};

const fromNothingToListOfStrings = async (init?: EndpointRequestInit): Promise<string[]> => {
    return _client.call('dev.hilla.generator.example.BasicEndpoint', 'fromNothingToListOfStrings', {}, init);
};

const fromPrimitiveToPrimitiveArray = async (argument: number, init?: EndpointRequestInit): Promise<number[]> => {
    return _client.call('dev.hilla.generator.example.BasicEndpoint', 'fromPrimitiveToPrimitiveArray', { argument }, init);
};

const fromStringToString = async (argument: string, init?: EndpointRequestInit): Promise<string> => {
    return _client.call('dev.hilla.generator.example.BasicEndpoint', 'fromStringToString', { argument }, init);
};

const fromTwoToMapOfStringsToList = async (init: string, end: string, _init?: EndpointRequestInit): Promise<Map<string, string[]>> => {
    return _client.call('dev.hilla.generator.example.BasicEndpoint', 'fromTwoToMapOfStringsToList', { init, end }, _init);
};

const BasicEndpoint = {
    fromArrayOfStringsToNothing,
    fromNothingToListOfStrings,
    fromPrimitiveToPrimitiveArray,
    fromStringToString,
    fromTwoToMapOfStringsToList,
};

export default BasicEndpoint;
