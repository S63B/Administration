import { Pol } from './pol';

export class Ride {
    constructor(
        public id: number,
        public pols: Pol[],
        public distance: number) {
    }
}
