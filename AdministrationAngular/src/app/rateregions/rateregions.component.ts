import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import { SebmGoogleMapCircle } from "angular2-google-maps/core";
import { RateRegion } from "app/rate-region";
import { UUIDService } from "app/uuid.service";
import { RateRegionService } from "app/rate-region.service";

@Component({
  selector: 'app-rateregions',
  templateUrl: './rateregions.component.html',
  styleUrls: ['./rateregions.component.css'],
  encapsulation: ViewEncapsulation.None
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
    this.getRateRegions();
  }

  ngOnInit() {

  }

  /**
   * Gets all the existing rate regions.
   */
  getRateRegions() {
    this.rateRegionService.getAll().subscribe(response => {
      const unparsedRateRegions: RateRegion[] = response.json();
      if (unparsedRateRegions !== null) {
        const allRateRegions: RateRegion[] = unparsedRateRegions.map(this.parseRateRegionDates).map(rateRegion => {
          rateRegion.uuid = this.generateUUID();
          return rateRegion;
        });
        this.rateRegions = allRateRegions;
      } else {
        this.rateRegions = [];
      }
    });
  }

  /**
   * Creates a new rate region in the database through the server.
   *
   * @param {RateRegion} rateRegion The rate region that should be created.
   */
  createNewRateRegion(rateRegion: RateRegion) {
    const index = this.getRateRegionIndex(rateRegion);

    if (!this.areDatesValid(rateRegion)) {
      return;
    }

    this.rateRegionService.create(rateRegion).subscribe(response => {
      const createdRateRegion = this.parseRateRegionDates(response.json());
      createdRateRegion.uuid = rateRegion.uuid;
      this.rateRegions[index] = createdRateRegion;
      this.setActiveRateRegion(createdRateRegion);
    });
  }

  /**
   * Updates an existing rate region in the database through the server.
   *
   * @param {RateRegion} rateRegion The rate region that needs to be updated.
   */
  updateRateRegion(rateRegion: RateRegion) {
    const index = this.getRateRegionIndex(rateRegion);

    if (!this.areDatesValid(rateRegion)) {
      return;
    }

    this.rateRegionService.update(rateRegion).subscribe(response => {
      const updatedRateRegion = this.parseRateRegionDates(response.json());
      updatedRateRegion.uuid = rateRegion.uuid;
      this.rateRegions[index] = updatedRateRegion;
      this.setActiveRateRegion(updatedRateRegion);
    });
  }

  /**
   * Checks if the start- and enddate of a rate region are valid.
   * @param {RateRegion} rateRegion The rate region of which the dates should be validated.
   * @returns {boolean} Whether or not the dates are valid.
   */
  areDatesValid(rateRegion: RateRegion) {
    return rateRegion.startDate !== "" && rateRegion.endDate !== "";
  }

  /**
   * Deletes an existing rate region.
   *
   * @param {RateRegion} rateRegion The rate region that should be deleted.
   */
  deleteRateRegion(rateRegion: RateRegion) {
    const removeRateRegionFromList = () => {
      const index = this.getRateRegionIndex(rateRegion);
      this.rateRegions.splice(index, 1);
    }

    if (rateRegion.id === -1) {
      removeRateRegionFromList();
    } else {
      this.rateRegionService.delete(rateRegion.id).subscribe(response => {
        const result = response.json();
        if (result) {
          removeRateRegionFromList();
        }
      });
    }
  }

  /**
   * Parses the region date to YYYY-MM-DD.
   *
   * @param {RateRegion} rateRegion The rate region of which the dates should be parsed.
   * @returns {RateRegion} The rate region with updated dates.
   */
  parseRateRegionDates(rateRegion: RateRegion) {
    const addZeroToDatePart = datePart => datePart < 10 ? `0${datePart}` : datePart;
    const parseDate = date => `${date.year}-${addZeroToDatePart(date.monthOfYear)}-${addZeroToDatePart(date.dayOfMonth)}`;
    rateRegion.startDate = parseDate(rateRegion.startDate);
    rateRegion.endDate = parseDate(rateRegion.endDate);
    return rateRegion;
  }

  /**
   * Event handler for when a right click occurs on the map.
   *
   * @param {any} mapClickedEvent An event object containing the coordinates of where the map was right clicked.
   */
  mapRightClicked(mapClickedEvent: any) {
    const coordinates = mapClickedEvent.coords;
    const newUUID = this.generateUUID();
    const newRegion
      = new RateRegion(this.defaultID, newUUID, this.defaultRate, coordinates.lat, coordinates.lng, this.defaultRadius, this.defaultStartDate, this.defaultEndDate);
    this.rateRegions.push(newRegion);
    this.setActiveRateRegion(newRegion);
  }

  /**
   * Event handler for when a rate regions radius has been changed.
   *
   * @param {number} newRadius The new radius in meters.
   * @param {RateRegion} changedRateRegion The rate region of which the radius has been changed.
   */
  radiusChanged(newRadius: number, changedRateRegion: RateRegion) {
    const index = this.getRateRegionIndex(changedRateRegion);
    let foundRateRegion = this.rateRegions[index];
    if (foundRateRegion.radius !== newRadius) {
      foundRateRegion.radius = newRadius;
      this.setActiveRateRegion(foundRateRegion);
      this.rateRegions[index] = foundRateRegion;
    }
  }

  /**
   * Event handler for when the centerpoint of a rate region has been changed.
   *
   * @param {any} newCenterCoordinates An object containing the new latitude and longitude of a rate regino.
   * @param {RateRegion} changedRateRegion The rate region of which the centerpoint has been changed.
   */
  centerpointChanged(newCenterCoordinates: any, changedRateRegion: RateRegion) {
    const index = this.getRateRegionIndex(changedRateRegion);
    let foundRateRegion = this.rateRegions[index];
    if (foundRateRegion.lat !== newCenterCoordinates.lat || foundRateRegion.lng !== newCenterCoordinates.lng) {
      foundRateRegion.lat = newCenterCoordinates.lat;
      foundRateRegion.lng = newCenterCoordinates.lng;
      this.setActiveRateRegion(foundRateRegion);
      this.rateRegions[index] = foundRateRegion;
    }
  }

  /**
   * Deactivates the currently active rate region (if there is one) and sets a new rate region as active.
   * @param {RateRegion} rateRegion The new rate region that should be set to active.
   */
  setActiveRateRegion(newActiveRegion: RateRegion) {
    const index = this.rateRegions.findIndex(rateRegion => {
      return rateRegion.active;
    });
    if (index !== -1) {
      let currentlyActiveRateRegion = this.rateRegions[index];
      currentlyActiveRateRegion.active = false;
      this.rateRegions[index] = currentlyActiveRateRegion;
    }
    
    const activeRegionIndex = this.getRateRegionIndex(newActiveRegion);
    let regionToUpdate = this.rateRegions[activeRegionIndex];
    regionToUpdate.active = true;
    this.rateRegions[activeRegionIndex] = regionToUpdate;
  }

  /**
   * Gets the index of the given rate region.
   *
   * @param {RateRegion} rateRegion
   * @returns {number} The index of the rate region or -1 if it couldn't be found.
   */
  getRateRegionIndex(rateRegion: RateRegion) {
    const index = this.rateRegions.findIndex(region => region.uuid === rateRegion.uuid);
    if (index === -1) {
      throw new Error(`Rate region couldn't be found in rate regions array.`);
    }
    return index;
  }

  /**
   * Generates a random UUID which is used to identify rate regions which do not have a database id assigned to them.
   * Checks if the UUID is already in use because there's a slight change that generated values might not be unique.
   *
   * @returns {string} A random UUID.
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
   */
  doesUUIDExist(uuid: string) {
    return typeof this.rateRegions.find(region => region.uuid === uuid) !== 'undefined';
  }
}
