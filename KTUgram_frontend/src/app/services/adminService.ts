import {HttpClient, HttpResponse} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {NgxPermissionsService} from "ngx-permissions";
import {Observable} from "rxjs";
import * as http from "http";
import {User} from "../models/user";
import {Token} from "../models/token";
import {Comment} from "../models/comment";
import {Post} from "../models/post";
import { CommentReport } from '../models/commentReport';

@Injectable({ providedIn: 'root' })
export class AdminService {
  constructor(private http: HttpClient, private permissionService: NgxPermissionsService) {
  }

  getAllUsers(){
    return this.http.post<User[]>("admin/all-users", null).pipe();
  }
  blockUser(id:number): Observable<HttpResponse<Token>> {
    return this.http.post<Token>("admin/blockUserById/", id, { observe: 'response' }).pipe();
  }
  unblockUser(id:number):Observable<HttpResponse<Token>>{
    return this.http.post<Token>("admin/unblockUserById/", id, { observe: 'response' }).pipe();
  }
  getCommentsByUser(id: number){
    return this.http.get<Comment[]>('admin/comments-by-user/' + id).pipe();
  }
  getCommentsByAllUsers(){
    return this.http.get<Comment[]>('admin/commentsByAllUsers').pipe();
  }
  deleteUserComment(id: number){
    return this.http.post<Token>("admin/deleteUserComment/", id, {observe: 'response'}).pipe();
  }
  getUserPosts(id: number){
    return this.http.get<Post[]>("admin/getUserPosts/" + id).pipe();
  }
  deleteUserPost(id: number){
    return this.http.post<Token>("admin/deleteUserPost/", id, {observe: 'response'}).pipe();
  }
  getCommentReportsByCommentId(id: number)
  {
    return this.http.get<CommentReport[]>("admin/reportsByCommentId/" + id).pipe();
  }
  deleteCommentReportById(id: number){
    return this.http.post<Token>("admin/deleteCommentReportById/", id, { observe: 'response'}).pipe();
  }

  deleteAllReportsByCommentId(id: number) //not used atm
  {
    return this.http.post<Token>("admin/deleteCommentReports", id, { observe: 'response'}).pipe();
  }
}
