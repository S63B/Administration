import {RouterModule, Routes} from "@angular/router";
import {CarownerComponent} from "./carowner/carowner.component";
import {RegistrationComponent} from "./registration/registration.component";
import {ModuleWithProviders} from "@angular/core";
import { RateRegionsComponent } from "app/rateregions/rateregions.component";
import {LoginComponent} from "./login/login.component";
import {CanActivateAuthGuard} from "./can-active.authguard";

const appRoutes: Routes = [
  {
    path: '',
    component: CarownerComponent,
    canActivate: [CanActivateAuthGuard]
  },
  {
    path: 'register',
    component: RegistrationComponent
  },
  {
    path: 'rateregions',
    component: RateRegionsComponent,
    canActivate: [CanActivateAuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
