

import {Injectable} from "@angular/core";
import {HttpService} from "./http.service";
import {RequestOptions} from "@angular/http";
import {API_URL_ADMINISTRATION} from "./constants";
import {Account} from "./account";


@Injectable()
export class AccountService {

  constructor(private httpService: HttpService) {
  }
  /**
   * Register an Account.
   * @param owner The newly created owner
   */
  register(account: Account) {
    //Send request to the administration backend. /owner/create with owner in the body.
    let url = `${API_URL_ADMINISTRATION}/account/register?username=${account.username}&password=${account.password}`;
    return this.httpService.post(url);
  }

}
