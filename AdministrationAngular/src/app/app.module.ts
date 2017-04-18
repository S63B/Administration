import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';

import { routing } from './app.routing';
import { InvoiceComponent } from './invoice/invoice.component';
import { HomeComponent } from './home/home.component';
import { RidesComponent } from './rides/rides.component';
import { InvoicesComponent } from './invoices/invoices.component';
import { HttpService } from "app/http.service";
import { RideService } from "app/ride.service";
import { InvoiceService } from "app/invoice.service";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    InvoiceComponent,
    HomeComponent,
    RidesComponent,
    InvoicesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [
    HttpService,
    HttpModule,
    RideService,
    InvoiceService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
