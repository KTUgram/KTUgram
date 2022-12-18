import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/userService";
import {Router} from "@angular/router";
import {NgxPermissionsService} from "ngx-permissions";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {

  constructor(private userService: UserService, private router: Router, private permissionService: NgxPermissionsService, private snackbar: MatSnackBar) { }

  username!: string | null;

  ngOnInit(): void {
  }

  logout(){
    this.userService.logout().subscribe(result => {
      localStorage.removeItem("username");
      localStorage.removeItem("loggedIn");
      localStorage.removeItem("perms");
      this.permissionService.flushPermissions();
      this.permissionService.loadPermissions(['GUEST']);
      this.snackbar.open("Successful operation", "Dismiss", {duration: 3000});
      this.router.navigate(['/login']);
      console.log(this.permissionService.getPermissions());
    })
  }
}
