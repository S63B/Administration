export class RateRegion {
    constructor(
        public id: number,
        public uuid: string,
        public rate: number,
        public lat: number,
        public lng: number,
        public radius: number,
        public startDate: string,
        public endDate: string
    ) {

    }
}
