import {RouterModule, Routes} from "@angular/router";
import {CarownerComponent} from "./carowner/carowner.component";
import {RegistrationComponent} from "./registration/registration.component";
import {ModuleWithProviders} from "@angular/core";
/**
 * Created by Nekkyou on 19-6-2017.
 */


const appRoutes: Routes = [
  {
    path: '',
    component: CarownerComponent
  },
  {
    path: 'register',
    component: RegistrationComponent
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
