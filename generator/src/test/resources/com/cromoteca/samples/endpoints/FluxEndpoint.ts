import client from '../../../../connect-client.default.js';
import { type Subscription } from '@hilla/frontend';

function flux(arg: Array<string | undefined>): Subscription<Map<string, Array<string | undefined>>> {
    return client.subscribe('com.cromoteca.samples.endpoints.FluxEndpoint', 'flux', { arg });
}

function subscription(): Subscription<string> {
    return client.subscribe('com.cromoteca.samples.endpoints.FluxEndpoint', 'subscription', {});
}

const FluxEndpoint = {
    flux,
    subscription,
};

export default FluxEndpoint;
