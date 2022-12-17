import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {NgxPermissionsService} from "ngx-permissions";
import {Post} from "../models/post";
import {Comment} from "../models/comment";
import {User} from "../models/user";
import {Message} from "../models/message";
import {BlockedUser} from "../models/blockedUser";

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

  addMessage(id: number, message: Message){
    return this.http.post('messages/add-message/' + id, message).pipe();
  }

  editMessage(message: Message){
    return this.http.post('messages/edit-message', message).pipe();
  }
  blockMessages(id: number){
    return this.http.get('messages/block-user-messages/' + id).pipe();
  }

  getBlockedUsers(){
    return this.http.get<BlockedUser[]>('messages/get-blocked-users').pipe();
  }

  removeBlockedUser(id: number){
    return this.http.get('messages/remove-blocked-user/' + id).pipe();
  }

  deleteMessage(message: Message){
    return this.http.post('messages/delete-message', message).pipe();
  }

}
