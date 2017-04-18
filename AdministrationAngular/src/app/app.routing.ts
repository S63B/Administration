import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { HomeComponent } from './home/home.component';
import { InvoiceComponent } from "app/invoice/invoice.component";

const appRoutes: Routes = [
  {
    component: HomeComponent,
    path: '',
  },
  {
    path: 'invoice',
    component: InvoiceComponent
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
