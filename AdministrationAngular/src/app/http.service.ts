import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

@Injectable()
export class HttpService {

  constructor(private http: Http) {
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

  private setHeaders(): Headers {
    const headers: Headers = new Headers();
    //headers.append('Authorization', `Basic ${btoa('bramdb:steve')}`);
    return headers;
  }
}