import { Directive, Input, HostListener } from '@angular/core';
import { GoogleMapsAPIWrapper, CircleManager, SebmGoogleMapCircle } from "angular2-google-maps/core";
import { RoadPricingRegion } from "app/road-pricing-region";

declare var google: any;

@Directive({
  selector: 'sebm-google-map'
})
export class GooglemapsratesDirective {
  @Input() defaultRate: number;
  @Input() defaultRadius: number;
  @Input() startDate: string;
  @Input() endDate: string;
  @Input() regions: RoadPricingRegion[];

  constructor(
    private gmapsApi: GoogleMapsAPIWrapper,
    private circleManager: CircleManager) {

  }

  ngOnInit() {
    this.gmapsApi.getNativeMap().then(map => {
      if (typeof this.regions !== 'undefined') {
        this.regions.forEach(region => {
          const circle = this.createCircle(region.lat, region.lng, region.radius);
          this.addCircleToMap(circle);
        });
      }
    });
  }

  @HostListener('mapClick', ['$event']) 
  mapClicked(mapClickEvent) {
    const coordinates = mapClickEvent.coords;
    const circle = this.createCircle(coordinates.lat, coordinates.lng, 500);
    this.createRegionRate(circle);
  }

  createRegionRate(circle: SebmGoogleMapCircle) {
    this.addCircleToMap(circle);

    if (typeof this.regions === 'undefined') {
      this.regions = [];
    }

    const newRegion: RoadPricingRegion 
      = new RoadPricingRegion(this.defaultRate, circle.latitude, circle.longitude, circle.radius, circle, this.startDate, this.endDate);

    this.regions.push(newRegion);
  }

  createCircle(lat: number, lng: number, radius: number): SebmGoogleMapCircle {
    let circle: SebmGoogleMapCircle = new SebmGoogleMapCircle(this.circleManager);
    circle.latitude = lat;
    circle.longitude = lng;
    circle.radius = radius;
    circle.editable = true;
    return circle
  }

  addCircleToMap(newCircle: SebmGoogleMapCircle) {
    this.circleManager.addCircle(newCircle);
  }
}
