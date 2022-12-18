import {HttpClient, HttpResponse} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {User} from "../models/user";
import {Observable} from "rxjs";
import {Token} from "../models/token";
import {Person} from "../models/person";
import {log} from "util";
import {NgxPermissionsService} from "ngx-permissions";
import {Post} from "../models/post";

@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient, private permissionService: NgxPermissionsService) {
  }

  register(user: User, profile_pic: any): Observable<void> {
    const fd = new FormData();
    fd.append('profile_pic', profile_pic)
    fd.append('register_data', JSON.stringify(user))
    return this.http.post<void>(  'user/register', fd).pipe();
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

  getUserById(id: number): Observable<HttpResponse<User>>{
    return this.http.get<User>('user/getUserById/' + id, {observe: 'response'}).pipe();
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

  userExists(username: string): Observable<boolean>{
    return this.http.get<boolean>('user/check-username?username=' + username).pipe();
  }

  getFollowers(id: number): Observable<User[]>{
    return this.http.get<User[]>('user/followers/' + id).pipe();
  }

  getFollowing(id: number): Observable<User[]>{
    return this.http.get<User[]>('user/following/' + id).pipe();
  }

  getUserFollowing(): Observable<User[]>{
    return this.http.get<User[]>('user/following').pipe();
  }

  updateFollow(id: number): Observable<void>{
    return this.http.get<void>('user/update-follow/' + id).pipe();
  }

  isFollowing(id: number): Observable<boolean>{
    return this.http.get<boolean>('user/is-following/' + id).pipe();
  }

  getUser(): Observable<User>{
    return this.http.get<User>('user/getUser').pipe();
  }

  updateProfile(about: string, profile_pic: any): Observable<void>{
    const fd = new FormData();
    fd.append('image', profile_pic)
    fd.append('about', about)
    return this.http.post<void>(  'user/update-profile', fd).pipe();
  }

  confirmRegistration(code: string): Observable<HttpResponse<void>>{
    return this.http.post<void>('user/confirm', code, {observe: "response"}).pipe();
  }

}
