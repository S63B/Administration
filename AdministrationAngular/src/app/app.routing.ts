import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { RateRegionsComponent } from "app/rateregions/rateregions.component";
import { CarownerComponent } from "app/carowner/carowner.component";

const appRoutes: Routes = [
  {
    component: CarownerComponent,
    path: '',
  },
  {
    path: 'rateregions',
    component: RateRegionsComponent
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);