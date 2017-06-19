import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { HttpService } from "app/http.service";
import { CarService } from "app/car.service";

import { JodatimePipe } from './jodatime.pipe';

import { AppComponent } from './app.component';
import { CarownerComponent } from './carowner/carowner.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SearchPipe } from './search.pipe';
import { RegistrationComponent } from './registration/registration.component';
import {AccountService} from "./account.service";
import {routing} from "./app.routing";

@NgModule({
  declarations: [
    AppComponent,
    CarownerComponent,
    JodatimePipe,
    SearchPipe,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    routing
  ],
  providers: [
    HttpModule,
    HttpService,
    CarService,
    AccountService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
