

import {Injectable} from "@angular/core";
import {HttpService} from "./http.service";
import {RequestOptions} from "@angular/http";
import {Account} from "./account";

import { environment } from "../environments/environment.prod"


@Injectable()
export class AccountService {
  private API_URL_ADMINISTRATION: string = environment.administrationUrl;

  constructor(private httpService: HttpService) {
  }
  /**
   * Register an Account.
   * @param owner The newly created owner
   */
  register(account: Account) {
    //Send request to the administration backend. /owner/create with owner in the body.
    let url = `${this.API_URL_ADMINISTRATION}/account/register?username=${account.username}&password=${account.password}`;
    return this.httpService.post(url);
  }

}
