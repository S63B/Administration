import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, Http } from '@angular/http';

import { AgmCoreModule } from 'angular2-google-maps/core';
import { TranslateModule, TranslateLoader, TranslateStaticLoader } from 'ng2-translate';

import { HttpService } from "app/http.service";
import { CarService } from "app/car.service";
import { AppComponent } from './app.component';
import { CarownerComponent } from './carowner/carowner.component';
import { RateRegionsComponent } from './rateregions/rateregions.component';
import { routing } from "app/app.routing";
import { UUIDService } from "app/uuid.service";
import { RateRegionService } from "app/rate-region.service";

@NgModule({
  declarations: [
    AppComponent,
    CarownerComponent,
    RateRegionsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDNtmOxKdE2VfxAHO6wTdiqRZMoGN_20cc'
    }),
    TranslateModule.forRoot({
      provide: TranslateLoader,
      useFactory: (http: Http) => new TranslateStaticLoader(http, '/assets/i18n', '.json'),
      deps: [Http]
    })
  ],
  providers: [
    HttpModule,
    HttpService,
    CarService,
    UUIDService,
    RateRegionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
