import {HttpClient, HttpResponse} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {NgxPermissionsService} from "ngx-permissions";
import {Observable} from "rxjs";
import * as http from "http";
import {User} from "../models/user";
import {UserTuple} from "../models/userTuple";
import {Token} from "../models/token";
import {Comment} from "../models/comment";
import {Post} from "../models/post";
import { CommentReport } from '../models/commentReport';
import { CommentTuple } from '../models/commentTuple';
import { UserReport } from '../models/userReport';

@Injectable({ providedIn: 'root' })
export class AdminService {
  constructor(private http: HttpClient, private permissionService: NgxPermissionsService) {
  }

  getAllUsers(){
    return this.http.post<UserTuple[]>("admin/all-users-withReports", null).pipe();
  }
  getAllUsersSortedByReports(){
    return this.http.post<UserTuple[]>("admin/all-users-withReportsSorted", null).pipe();
  }
  blockUser(id:number): Observable<HttpResponse<Token>> {
    return this.http.post<Token>("admin/blockUserById/", id, { observe: 'response' }).pipe();
  }
  unblockUser(id:number):Observable<HttpResponse<Token>>{
    return this.http.post<Token>("admin/unblockUserById/", id, { observe: 'response' }).pipe();
  }
  getCommentsByUser(id: number){
    return this.http.get<CommentTuple[]>('admin/comments-by-user/' + id).pipe();
  }
  getCommentsByAllUsers(){
    return this.http.get<CommentTuple[]>('admin/commentsByAllUsers').pipe();
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
  getCommentsByAllUsersSortedByReports(){
    return this.http.get<CommentTuple[]>("admin/commentsByAllUsersSortByReports").pipe();
  }
  getCommentsByUserSortedByReports(id: number){
    return this.http.get<CommentTuple[]>('admin/comments-by-user-sorted/' + id).pipe();
  }
  deleteUserReport(id: number){
    return this.http.post<Token>("admin/deleteUserReport", id, { observe: 'response'}).pipe();
  }
  getUserReportsById(id: number){
    return this.http.get<UserReport[]>("admin/getUserReportsById/" + id).pipe();
  }

  warnUser(id: number, message: string): Observable<void>{
    console.log(id, message);
    return this.http.post<void>('admin/warn-user/' + id, message).pipe();
  }
}
