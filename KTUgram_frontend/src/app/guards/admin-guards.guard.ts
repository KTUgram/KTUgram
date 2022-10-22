import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Route, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {NgxPermissionsService} from "ngx-permissions";

@Injectable({
  providedIn: 'root'
})
export class AdminGuardsGuard implements CanActivate {
  constructor(private permissionService: NgxPermissionsService, private router: Router) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let permission = this.permissionService.getPermission("ADMIN")

    if(permission != undefined){
      this.router.navigate(["main/users"])
      return true;
    }
    this.router.navigate(["main/newsfeed"])
    return true;
  }

}
