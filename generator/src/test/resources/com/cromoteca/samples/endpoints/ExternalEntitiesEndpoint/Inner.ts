import type Page from '../../../../org/springframework/data/domain/Page.js';
import type Flux from '../../../../reactor/core/publisher/Flux.js';

interface Inner<T> {
    page: Page<T>;
    flux: Flux<T>;
}

export default Inner;
