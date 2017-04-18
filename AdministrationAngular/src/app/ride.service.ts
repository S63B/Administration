import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { API_URL } from './constants';
import { Ride } from "app/ride";


@Injectable()
export class RideService {

  constructor(private httpService: HttpService) {
  }

  public getRidesBetween(id: String, startTime: number, endTime: number): Observable<Ride[]> {
    return this.httpService.post(`${API_URL}/rides?id=${id}&startdate=${startTime}&startdate=${endTime}`)
      .map(response => response.json());
  }
  
  public get(id: String): Observable<Ride> {
    return this.httpService.post(`${API_URL}/ride?id=${id}`)
      .map(response => response.json());
  }
}
