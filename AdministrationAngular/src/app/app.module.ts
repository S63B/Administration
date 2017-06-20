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
import { ReactiveFormsModule } from '@angular/forms';
import { RateRegionsComponent } from './rateregions/rateregions.component';
import { UUIDService } from "app/uuid.service";
import { RateRegionService } from "app/rate-region.service";
import { JodatimePipe } from './jodatime.pipe';
import { HeaderComponent } from './header/header.component';
import { SearchPipe } from './search.pipe';
import { RegistrationComponent } from './registration/registration.component';
import {AccountService} from "./account.service";
import {routing} from "./app.routing";
import { LoginComponent } from './login/login.component';
import {AuthService} from "./auth.service";
import {CanActivateAuthGuard} from "./can-active.authguard";


@NgModule({
  declarations: [
    AppComponent,
    CarownerComponent,
    RateRegionsComponent,
    JodatimePipe,
    SearchPipe,
    RegistrationComponent,
    HeaderComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
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
    AccountService,
    UUIDService,
    RateRegionService,
    AuthService,
    CanActivateAuthGuard,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
