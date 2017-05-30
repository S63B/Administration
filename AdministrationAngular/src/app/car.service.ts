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

  getOwnersCars(ownerId: number): Observable<any> {
    return this.httpService.get(`http://localhost:8080/owner/${ownerId}/cars`)
      .map(response => response.json());
  }
}
