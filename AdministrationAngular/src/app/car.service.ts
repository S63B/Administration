import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";
import { Observable } from "rxjs/Rx";
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/map';


@Injectable()
export class CarService {
  
  constructor(private httpService: HttpService) {
  }

  getOwners(): Observable<any> {
    return this.httpService.get(`http://localhost:8080/owner`)
      .map(response => response.json());
  }


  getOwner(ownerId: number): Observable<any> {
    return this.httpService.get(`http://localhost:8080/owner/${ownerId}`)
      .map(response => response.json());
  }

  getOwnersCars(ownerId: number): Observable<any> {
    return this.httpService.get(`http://localhost:8080/owner/${ownerId}/cars`)
      .map(response => response.json());
  }

  addCarToOwner(ownerId: number, carId: number): Observable<any> {
    return this.httpService.post(`http://localhost:8080/car/${ownerId}/add/${carId}`)
      .map(response => response.json());
  }

  getOwnerInvoices(ownerId: number): Observable<any> {
    return this.httpService.get(`http://localhost:8080/invoices?user=${ownerId}`)
      .map(this.httpService.extractData);
  }
}
