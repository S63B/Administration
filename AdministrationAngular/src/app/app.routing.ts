import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { RateComponent } from "app/rate/rate.component";
import { CarownerComponent } from "app/carowner/carowner.component";

const appRoutes: Routes = [
  {
    component: CarownerComponent,
    path: '',
  },
  {
    path: 'rate',
    component: RateComponent
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
