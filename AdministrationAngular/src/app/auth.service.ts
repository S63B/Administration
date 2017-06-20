import { Injectable } from '@angular/core';
import { HttpService } from "app/http.service";
import { Observable } from "rxjs/Rx";
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/map';
import {API_URL_ADMINISTRATION} from "./constants";
import {Router} from "@angular/router";

@Injectable()
export class AuthService {

  constructor(private httpService: HttpService, private router: Router) {
  }

  isAuthorized(): boolean {
    let token = localStorage.getItem("auth");
    return token && token.length > 0;
  }

  login(username: string, password: string): Observable<any> {
    localStorage.setItem("auth", `${btoa(`${username}:${password}`)}`);

    let url = `${API_URL_ADMINISTRATION}/account/loggedin`;
    return this.httpService.get(url).map(res => res.status);
  }

  logout() {
    localStorage.removeItem("auth");
    this.router.navigateByUrl('login');
  }
}
