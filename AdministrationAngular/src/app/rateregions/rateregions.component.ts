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
  private defaultID: number = -1;
  private defaultRate: number = 1.35;
  private defaultRadius: number = 500;
  private defaultStartDate: string = '2017-06-09';
  private defaultEndDate: string = '2017-12-31';

  private rateRegions: RateRegion[];

  constructor(
    private uuidService: UUIDService,
    private rateRegionService: RateRegionService
  ) {
    this.rateRegions = [];
    this.rateRegions.push(new RateRegion(-1, "asdfkajsdflka;sdf", 10, 51.436596, 5.478001, 500, this.defaultStartDate, this.defaultEndDate));
    this.getRateRegions();
  }

  ngOnInit() {
  }

  /**
   * Gets all the existing rate regions.
   * 
   * @memberof RateRegionsComponent
   */
  getRateRegions() {
    this.rateRegionService.getAll().subscribe(rateRegions => {
      console.log(rateRegions);
    });
  }

  /**
   * Creates a new rate region in the database through the server.
   * 
   * @param {RateRegion} rateRegion The rate region that should be created.
   * 
   * @memberof RateRegionsComponent
   */
  createNewRateRegion(rateRegion: RateRegion) {
    this.rateRegionService.create(rateRegion).subscribe(createdRateRegion => {
      // update object by using rateRegion.uuid
    });
  }

  /**
   * Updates an existing rate region in the database through the server.
   * 
   * @param {RateRegion} rateRegion The rate region that needs to be updated.
   * 
   * @memberof RateRegionsComponent
   */
  updateRateRegion(rateRegion: RateRegion) {
    this.rateRegionService.update(rateRegion).subscribe(updatedRateRegion => {

    });
    // this.rateRegionService.update(rateRegion)
  }

  /**
   * Event handler for when a right click occurs on the map.
   * 
   * @param {any} mapClickedEvent An event object containing the coordinates of where the map was right clicked.
   * 
   * @memberof RateRegionsComponent
   */
  mapRightClicked(mapClickedEvent: any) {
    const coordinates = mapClickedEvent.coords;
    const newUUID = this.generateUUID();
    const newRegion 
      = new RateRegion(this.defaultID, newUUID, this.defaultRate, coordinates.lat, coordinates.lng, this.defaultRadius, this.defaultStartDate, this.defaultEndDate);
    this.rateRegions.push(newRegion);
  }

  /**
   * Event handler for when a rate regions radius has been changed.
   * 
   * @param {number} newRadius The new radius in meters.
   * @param {RateRegion} changedRateRegion The rate region of which the radius has been changed.
   * 
   * @memberof RateRegionsComponent
   */
  radiusChanged(newRadius: number, changedRateRegion: RateRegion) {
    const index = this.rateRegions.findIndex(region => region.uuid === changedRateRegion.uuid);
    if (index === -1) {
      throw new Error(`Rate region couldn't be found in rate regions array`);
    }
    const foundRateRegion = this.rateRegions[index];
    if (foundRateRegion.radius !== newRadius) {
      foundRateRegion.radius = newRadius;
      this.rateRegions[index] = foundRateRegion;
    }
  }

  /**
   * Event handler for when the centerpoint of a rate region has been changed.
   * 
   * @param {any} newCenterCoordinates An object containing the new latitude and longitude of a rate regino.
   * @param {RateRegion} changedRateRegion The rate region of which the centerpoint has been changed.
   * 
   * @memberof RateRegionsComponent
   */
  centerpointChanged(newCenterCoordinates: any, changedRateRegion: RateRegion) {
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

  /**
   * Generates a random UUID which is used to identify rate regions which do not have a database id assigned to them.
   * Theres a slight change that generated values might not be unique.
   * 
   * @returns {string} A random UUID.
   * 
   * @memberof RateRegionsComponent
   */
  generateUUID(): string {
    let uuid = this.uuidService.generate();
    while (this.doesUUIDExist(uuid)) {
      uuid = this.uuidService.generate();
    }
    return uuid;
  }

  /**
   * Checks if the generate UUID is already being used by one of the rgions.
   * 
   * @param {string} uuid 
   * @returns {boolean} Whether the given UUID is already in used by another region.
   * 
   * @memberof RateRegionsComponent
   */
  doesUUIDExist(uuid: string) {
    return typeof this.rateRegions.find(region => region.uuid === uuid) !== 'undefined';
  }
}
