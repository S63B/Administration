import { SebmGoogleMapCircle } from "angular2-google-maps/core";

export class RoadPricingRegion {
    constructor(
        public rate: number,
        public lat: number,
        public lng: number,
        public radius: number,
        public circle: SebmGoogleMapCircle,
        public startDate: string,
        public endDate: string) {

        }
}
