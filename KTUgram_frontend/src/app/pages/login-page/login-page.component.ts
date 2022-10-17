import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/userService";
import {Token} from "../../models/token";
import {HttpResponse} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    if(localStorage.getItem('username') != null){
      this.router.navigate(["/main"]);
    }
  }

  loginSubmit(data: any){
    console.log(data);
    this.userService.login(data).subscribe((result: HttpResponse<Token>) => {
      console.log(result.body);
      localStorage.removeItem('username');
      if(result.body != null && result.status == 200){
        localStorage.setItem('username', result.body.loggedInUserName);
        this.router.navigate(["/main"]);
      }
    })
  }

}
