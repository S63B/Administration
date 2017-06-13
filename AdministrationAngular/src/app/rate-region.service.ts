import { Injectable } from '@angular/core';

import { HttpService } from "app/http.service";
import { RateRegion } from "app/rate-region";

import { API_URL_ADMINISTRATION } from "app/constants";

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
    return this.httpService.get(`${API_URL_ADMINISTRATION}/rate`);
  }

  /**
   * Creates a new rate region in the database through the server.
   * 
   * @param {RateRegion} rateRegion The rate region that should be created.
   */
  create(newRateRegion: RateRegion) {
    const rr = newRateRegion;
    return this.httpService.post(`${API_URL_ADMINISTRATION}/rate/create?centerLat=${rr.lat}&centerLon=${rr.lng}&radius=${rr.radius}&pricing=${rr.rate}&startDate=${rr.startDate}&endDate=${rr.endDate}`);
  }

  /**
   * Updates an existing rate region in the database through the server
   * 
   * @param {RateRegion} updatedRateRegion The rate region that needs to be updated.
   */
  update(updatedRateRegion: RateRegion) {
    const rr = updatedRateRegion; 
    return this.httpService.put(`${API_URL_ADMINISTRATION}/rate/update?rateId=${rr.id}&centerLat=${rr.lat}&centerLon=${rr.lng}&radius=${rr.radius}&pricing=${rr.rate}&startDate=${rr.startDate}&endDate=${rr.endDate}`);
  }

  delete(rateRegionId: number) {
    return this.httpService.delete(`/rate/delete?rateRegionId=${rateRegionId}`);
  }

}
