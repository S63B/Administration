import { Component, OnInit } from '@angular/core';

import { SebmGoogleMapCircle } from "angular2-google-maps/core";
import { RateRegion } from "app/rate-region";
import { UUIDService } from "app/uuid.service";
import { RateRegionService } from "app/rate-region.service";

@Component({
  selector: 'app-rateregions',
  templateUrl: './rateregions.component.html',
  styleUrls: ['./rateregions.component.css']
})
export class RateRegionsComponent implements OnInit {
  private rateRegions: RateRegion[];
  private defaultID: number = -1;
  private defaultRate: number = 1.35;
  private defaultRadius: number = 500;
  private defaultStartDate: string = '09-06-2017';
  private defaultEndDate: string = '31-12-2017';

  constructor(
    private uuidService: UUIDService,
    private rateRegionService: RateRegionService
  ) {
    this.rateRegions = [];
    this.rateRegions.push(new RateRegion(-1, "asdfkajsdflka;sdf", 10, 51.436596, 5.478001, 500, 'nu', 'morgen'));
  }

  ngOnInit() {
    // get regions, create unique uuid per region
  }

  getRateRegions() {

  }

  saveRateRegion(rateRegion: RateRegion) {
    console.log(rateRegion);
  }

  mapClicked(mapClickedEvent: any) {
    const coordinates = mapClickedEvent.coords;
    const newUUID = this.generateUUID();
    const newRegion 
      = new RateRegion(this.defaultID, newUUID, this.defaultRate, coordinates.lat, coordinates.lng, this.defaultRadius, this.defaultStartDate, this.defaultEndDate);
    this.rateRegions.push(newRegion);
  }

  radiusChanged(newRadius: any, changedRateRegion: RateRegion) {
    const index = this.rateRegions.findIndex(region => region.uuid === changedRateRegion.uuid);
    if (index === -1) {
      throw new Error(`Rate region couldn't be found in rate regions array`);
    }
    const foundRateRegion = this.rateRegions[index];
    if (foundRateRegion.radius !== newRadius) {
      foundRateRegion.radius = newRadius;
      this.rateRegions[index] = foundRateRegion;
      console.log('radius changed');
    }
  }

  centerChanged(newCenterCoordinates: any, changedRateRegion: RateRegion) {
    const index = this.rateRegions.findIndex(region => region.uuid === changedRateRegion.uuid);
    if (index === -1) {
      throw new Error(`Rate region couldn't be found in rate regions array`);
    }
    const foundRateRegion = this.rateRegions[index];
    if (foundRateRegion.lat !== newCenterCoordinates.lat || foundRateRegion.lng !== newCenterCoordinates.lng) {
      foundRateRegion.lat = newCenterCoordinates.lat;
      foundRateRegion.lng = newCenterCoordinates.lng;
      this.rateRegions[index] = foundRateRegion;
    }
  }

  generateUUID(): string {
    let uuid = this.uuidService.generate();
    while (this.doesUUIDExist(uuid)) {
      uuid = this.uuidService.generate();
    }
    return uuid;
  }

  doesUUIDExist(uuid: string) {
    return typeof this.rateRegions.find(region => region.uuid === uuid) !== 'undefined';
  }

  save
}
