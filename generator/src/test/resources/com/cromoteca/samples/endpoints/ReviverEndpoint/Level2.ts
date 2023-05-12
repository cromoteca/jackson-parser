import type Level1 from './Level1.js';

interface Level2 {
    id: number;
    info?: Level1 | undefined;
}

export default Level2;
