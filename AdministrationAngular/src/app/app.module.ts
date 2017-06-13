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

@NgModule({
  declarations: [
    AppComponent,
    CarownerComponent,
    JodatimePipe,
    SearchPipe
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
