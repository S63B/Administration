import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";
import { RateRegion } from "app/rate-region";

@Injectable()
export class RateRegionService {

  constructor(
    private httpService: HttpService
  ) {

  }

  /**
   * Gets all existing rate regions from the database.
   * 
   * @memberof RateRegionService
   */
  getAll() {
    return this.httpService.get(`/rate`);
  }

  /**
   * Creates a new rate region in the database through the server.
   * 
   * @param {RateRegion} rateRegion The rate region that should be created.
   *
   * @memberof RateRegionService
   */
  create(newRateRegion: RateRegion) {
    const rr = newRateRegion;
    return this.httpService.put(`/rate/${rr.id}/${rr.lat}/${rr.lng}/${rr.rate}/${rr.startDate}/${rr.endDate}`);
  }

  /**
   * * Updates an existing rate region in the database through the server
   * 
   * @param {RateRegion} updatedRateRegion The rate region that needs to be updated.
   * 
   * @memberof RateRegionService
   */
  update(updatedRateRegion: RateRegion) {
    const rr = updatedRateRegion; 
    return this.httpService.put(`/rate/${rr.id}/${rr.lat}/${rr.lng}/${rr.rate}/${rr.startDate}/${rr.endDate}`);
  }

}
