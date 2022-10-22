import {HttpClient, HttpResponse} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {User} from "../models/user";
import {Observable} from "rxjs";
import {Token} from "../models/token";
import {Person} from "../models/person";
import {log} from "util";
import {NgxPermissionsService} from "ngx-permissions";

@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient, private permissionService: NgxPermissionsService) {
  }

  register(person: Person): Observable<void> {
    return this.http.post<void>(  'user/register', person).pipe();
  }

  login(user: any): Observable<HttpResponse<Token>>{
    return this.http.post<Token>('user/login', user, { observe: 'response' }).pipe();
  }

  reLogin(){
    return this.http.post('user/re-login', null).pipe();
  }

  logout(){
    return this.http.post('user/logout', {}).pipe();
  }

  tst(){
    return this.http.get('user/tst').pipe();
  }

  isLogged(): boolean{
    return localStorage.getItem("logged") === "1";
  }

  getPermissions(): string[] | null{
    let perms = localStorage.getItem("perms");
    if(perms != null){
      return perms.split(',')
    }
    return null
  }
}
