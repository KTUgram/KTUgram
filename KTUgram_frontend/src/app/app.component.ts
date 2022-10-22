import { Component } from '@angular/core';
import {OnInit} from "@angular/core";
import {NgxPermissionsService} from "ngx-permissions";
import {UserService} from "./services/userService";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'KTUgram_frontend';

  constructor(private permissionService: NgxPermissionsService, private userService: UserService, private router: Router) {
    this.permissionService.flushPermissions()
    if(this.userService.isLogged()){
      this.permissionService.loadPermissions(["GUEST"]);
    }
    else{
      let perms = this.userService.getPermissions();
      if(perms != null){
        this.permissionService.loadPermissions(perms);
      }
      else{
        this.router.navigate(["login"]);
      }
    }
  }

}
