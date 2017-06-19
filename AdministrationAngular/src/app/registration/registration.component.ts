import { Component, OnInit } from '@angular/core';
import {AccountService} from "../account.service";
import {Account} from "../account";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  private account: Account = new Account();

  constructor(private accountService: AccountService, private router: Router) { }

  ngOnInit() {
  }


  register() {
    this.accountService.register(this.account)
      .subscribe(res => this.router.navigateByUrl(''));
  }

}
