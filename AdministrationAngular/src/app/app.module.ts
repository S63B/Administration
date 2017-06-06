import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AgmCoreModule } from 'angular2-google-maps/core';

import { HttpService } from "app/http.service";
import { CarService } from "app/car.service";

import { AppComponent } from './app.component';
import { CarownerComponent } from './carowner/carowner.component';
import { RateComponent } from './rate/rate.component';
import { routing } from "app/app.routing";
import { GooglemapsratesDirective } from './googlemapsrates.directive';

@NgModule({
  declarations: [
    AppComponent,
    CarownerComponent,
    RateComponent,
    GooglemapsratesDirective
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDNtmOxKdE2VfxAHO6wTdiqRZMoGN_20cc'
    })
  ],
  providers: [
    HttpModule,
    HttpService,
    CarService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
