import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {NgxPermissionsService} from "ngx-permissions";
import {Post} from "../models/post";

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
}
