import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { HttpService } from "app/http.service";
import { CarService } from "app/car.service";

import { AppComponent } from './app.component';
import { CarownerComponent } from './carowner/carowner.component';
import { RateComponent } from './rate/rate.component';
import { routing } from "app/app.routing";

@NgModule({
  declarations: [
    AppComponent,
    CarownerComponent,
    RateComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [
    HttpModule,
    HttpService,
    CarService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
