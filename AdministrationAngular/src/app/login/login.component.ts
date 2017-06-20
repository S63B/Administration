import { Component, OnInit } from '@angular/core';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private _username: string = "";
  private _password: string = "";
  private _succes: boolean = true;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.authService.logout();
  }

  login() {
    this.authService.login(this._username, this._password).subscribe(res => {
      this._succes = (res == 200);
      console.log(`Succes: ${this._succes}`);

      if (this._succes) {
        console.log('Log in succesfull');
        this.router.navigateByUrl('');
      }
      else {
        localStorage.removeItem("auth");
      }
    })
  }


  public get succes():boolean {
    return this._succes;
  }

  public set succes(succes:boolean){
    this._succes = succes;
  }

  public set username(username:string){
    this._username = username;
  }

  public get password():string {
    return this._password;
  }

  public set password(password:string){
    this._password = password;
  }

}
