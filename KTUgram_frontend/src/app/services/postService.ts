import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {NgxPermissionsService} from "ngx-permissions";
import {Post} from "../models/post";
import {Comment} from "../models/comment";
import {User} from "../models/user";
import { CommentReport } from '../models/commentReport';
import { UserReport } from '../models/userReport';
import { PostReport } from '../models/postReport';

@Injectable({ providedIn: 'root' })
export class PostService {
  constructor(private http: HttpClient, private permissionService: NgxPermissionsService) {
  }

  getPosts(): Observable<Post[]> {
    return this.http.post<Post[]>(  'posts/get-posts', null).pipe();
  }

  uploadPost(post: Post, image: any): Observable<void>{
    const fd = new FormData();
    fd.append('image', image)
    fd.append('title', post.title)
    fd.append('about', post.about)
    return this.http.post<void>('posts/upload', fd).pipe();
  }

  getLikedPosts(): Observable<any>{
    return this.http.get('posts/liked/').pipe();
  }

  likePost(id: number){
    return this.http.get('posts/like/' + id).pipe();
  }

  getComments(id: number): Observable<Comment[]>{
    return this.http.get<Comment[]>('posts/comments/' + id).pipe();
  }

  getCommentsByUser(id: number){
    return this.http.get<Comment[]>('posts/comments-by-user/' + id).pipe();
  }

  getCommentsExceptByUser(id: number){
    return this.http.get<Comment[]>('posts/comments-except-user/' + id).pipe();
  }

  addComment(comment: Comment){
    return this.http.post('posts/add-comment', comment).pipe();
  }

  editComment(comment: Comment){
    return this.http.post('posts/edit-comment', comment).pipe();
  }

  deleteComment(comment: Comment){
    return this.http.post('posts/delete-comment', comment).pipe();
  }

  deletePost(id: number){
    return this.http.get('posts/delete-post/' + id).pipe();
  }

  reportComment(reportComment: CommentReport){
    return this.http.post('posts/report-comment', reportComment).pipe();
  }

  reportUser(reportUser: UserReport){
    return this.http.post('posts/report-user', reportUser).pipe();
  }

  reportPost(reportPost: PostReport){
    return this.http.post('posts/report-post', reportPost).pipe();
  }

  removeProfile(){
    return this.http.post('posts/remove-user', null).pipe();
  }

  getPost(id: number): Observable<Post>{
    return this.http.get<Post>('posts/get-post/' + id).pipe();
  }

  getPostsByUser(id: number): Observable<Post[]>{
    return this.http.get<Post[]>('posts/get-posts-by-user/' + id).pipe();
  }

}
