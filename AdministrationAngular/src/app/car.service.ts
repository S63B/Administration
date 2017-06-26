import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";
import { Observable } from "rxjs/Rx";
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/map';

import { environment } from "../environments/environment.prod"


@Injectable()
export class CarService {
  private API_URL_ADMINISTRATION = environment.administrationUrl;

  constructor(private httpService: HttpService) {
  }

  getOwners(): Observable<any> {
    return this.httpService.get(`${this.API_URL_ADMINISTRATION}/owner`)
      .map(response => response.json());
  }


  getOwner(ownerId: number): Observable<any> {
    return this.httpService.get(`${this.API_URL_ADMINISTRATION}/owner/${ownerId}`)
      .map(response => response.json());
  }

  getOwnersCars(ownerId: number): Observable<any> {
    return this.httpService.get(`${this.API_URL_ADMINISTRATION}/owner/${ownerId}/cars`)
      .map(response => response.json());
  }

  addCarToOwner(ownerId: number, carId: number): Observable<any> {
    return this.httpService.post(`${this.API_URL_ADMINISTRATION}/car/${ownerId}/add/${carId}`)
      .map(response => response.json());
  }

  getOwnerInvoices(ownerId: number): Observable<any> {
    return this.httpService.get(`${this.API_URL_ADMINISTRATION}/invoices?owner=${ownerId}`)
      .map(this.httpService.extractData);
  }
}
