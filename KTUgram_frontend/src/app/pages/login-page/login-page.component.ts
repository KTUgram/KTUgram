import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/userService";
import {Token} from "../../models/token";
import {HttpResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {NgxPermissionsService} from "ngx-permissions";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  constructor(private userService: UserService, private router: Router, private permissionsService: NgxPermissionsService) { }

  ngOnInit(): void {
    localStorage.setItem("perms", "GUEST");
  }

  loginSubmit(data: any){
    this.userService.login(data).subscribe((result: HttpResponse<Token>) => {
      console.log(result.body);
      localStorage.removeItem('username');
      localStorage.removeItem('loggedIn');
      localStorage.removeItem('perms');
      if(result.body != null && result.status == 200){
        localStorage.setItem('username', result.body.loggedInUserName);
        localStorage.setItem('loggedIn', "1");
        localStorage.setItem('perms', result.body.rights.toString());
        this.permissionsService.loadPermissions(result.body.rights);
        console.log(this.permissionsService.getPermissions());
        this.router.navigate(["/main"]);
      }
    })
  }

}
