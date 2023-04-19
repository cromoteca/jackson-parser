import type Page from '../../../../../org/springframework/data/domain/Page.js';
import type Flux from '../../../../../reactor/core/publisher/Flux.js';

interface Inner<T> {
    flux: Flux;
    page: Page<T>;
}

export default Inner;
