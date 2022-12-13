import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {NgxPermissionsService} from "ngx-permissions";
import {Post} from "../models/post";
import {Comment} from "../models/comment";
import {User} from "../models/user";
import {Message} from "../models/message";

@Injectable({ providedIn: 'root' })
export class MessageService {
  constructor(private http: HttpClient, private permissionService: NgxPermissionsService) {
  }

  getAllUsers(){
    return this.http.get<User[]>('messages/get-users').pipe();
  }
  getUserMessages(id: number): Observable<Message[]>{
    return this.http.get<Message[]>('messages/get-messages/' + id).pipe();
  }
  getMessageByUser(id: number){
    return this.http.get<Message[]>('messages/messages-by-user/' + id).pipe();
  }

  getMessageExceptByUser(id: number){
    return this.http.get<Message[]>('messages/messages-except-user/' + id).pipe();
  }
  getUser(id: number): Observable<User>{
    return this.http.get<User>('messages/get-user/' + id).pipe();
  }
  getLoggedUser(){
    return this.http.get<User>('messages/get-logged-user').pipe();
  }

  addMessage(message: Message){
    return this.http.post('messages/add-message', message).pipe();
  }

}