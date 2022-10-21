import {HttpClient, HttpResponse} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {User} from "../models/user";
import {Observable} from "rxjs";
import {Token} from "../models/token";
import {Person} from "../models/person";

@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) {
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
}
