import {RouterModule, Routes} from "@angular/router";
import {CarownerComponent} from "./carowner/carowner.component";
import {RegistrationComponent} from "./registration/registration.component";
import {ModuleWithProviders} from "@angular/core";
import { CarownerComponent } from "app/carowner/carowner.component";
import { RateRegionsComponent } from "app/rateregions/rateregions.component";

const appRoutes: Routes = [
  {
    path: '',
    component: CarownerComponent
  },
  {
    path: 'register',
    component: RegistrationComponent
  },
  {
    path: 'rateregions',
    component: RateRegionsComponent
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
