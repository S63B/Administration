import { Injectable } from '@angular/core';

import { HttpService } from "app/http.service";
import { RateRegion } from "app/rate-region";

import { environment } from "environments/environment";

@Injectable()
export class RateRegionService {
  private API_URL_ADMINISTRATION: string = environment.administrationUrl;

  constructor(
    private httpService: HttpService
  ) {

  }

  /**
   * Gets all existing rate regions.
   * 
   * @memberof RateRegionService
   */
  getAll() {
    return this.httpService.get(`${this.API_URL_ADMINISTRATION}/rate`);
  }

  /**
   * Creates a new rate region.
   * 
   * @param {RateRegion} rateRegion The rate region that should be created.
   */
  create(newRateRegion: RateRegion) {
    const rr = newRateRegion;
    return this.httpService.post(`${this.API_URL_ADMINISTRATION}/rate/create?centerLat=${rr.lat}&centerLon=${rr.lng}&radius=${rr.radius}&pricing=${rr.rate}&startDate=${rr.startDate}&endDate=${rr.endDate}`);
  }

  /**
   * Updates an existing rate region.
   * 
   * @param {RateRegion} updatedRateRegion The rate region that needs to be updated.
   */
  update(updatedRateRegion: RateRegion) {
    const rr = updatedRateRegion;
    return this.httpService.put(`${this.API_URL_ADMINISTRATION}/rate/update?rateId=${rr.id}&centerLat=${rr.lat}&centerLon=${rr.lng}&radius=${rr.radius}&pricing=${rr.rate}&startDate=${rr.startDate}&endDate=${rr.endDate}`);
  }

  /**
   * Deletes a rate region with the given id.
   * 
   * @param rateRegionId 
   */
  delete(rateRegionId: number) {
    return this.httpService.delete(`${this.API_URL_ADMINISTRATION}/rate/delete?rateRegionId=${rateRegionId}`);
  }

}
