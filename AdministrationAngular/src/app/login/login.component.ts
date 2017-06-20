import { Component, OnInit } from '@angular/core';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private username: string = "";
  private password: string = "";
  private succes: boolean = true;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.authService.logout();
  }

  login() {
    this.authService.login(this.username, this.password).subscribe(res => {
      this.succes = (res == 200);
      console.log(`Succes: ${this.succes}`);

      if (this.succes) {
        console.log('Log in succesfull');
        this.router.navigateByUrl('');
      }
    })

  }

}
