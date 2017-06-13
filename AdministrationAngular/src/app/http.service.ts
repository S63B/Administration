import { Injectable } from '@angular/core';
import { Headers, Response, Http } from '@angular/http';

import { API_URL_ADMINISTRATION } from "app/constants";

@Injectable()
export class HttpService {

  constructor(private http: Http) {
  }

  public extractData(res: Response) {
    let body = res.text();
    return JSON.parse(body).entity || {};
  }

  public get(url) {
    console.log(url);
    return this.http.get(url, {
      headers: this.setHeaders()
    });
  }

  public post(url) {
    console.log(url);
    return this.http.post(url, {
      headers: this.setHeaders()
    });
  }

  public put(url) {
    console.log(url);
    return this.http.put(url, {
      headers: this.setHeaders()
    });
  }

  public delete(url) {
    const fullURL = `${API_URL_ADMINISTRATION}${url}`;
    return this.http.delete(fullURL, {
      headers: this.setHeaders()
    });
  }

  private setHeaders(): Headers {
    const headers: Headers = new Headers();
    //headers.append('Authorization', `Basic ${btoa('bramdb:steve')}`);
    return headers;
  }
}