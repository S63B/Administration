import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { HttpService } from "app/http.service";
import { CarService } from "app/car.service";

import { AppComponent } from './app.component';
import { CarownerComponent } from './carowner/carowner.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    CarownerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule
  ],
  providers: [
    HttpModule,
    HttpService,
    CarService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
