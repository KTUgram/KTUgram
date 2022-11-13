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
export class PostService {
  constructor(private http: HttpClient, private permissionService: NgxPermissionsService) {
  }

  getPosts(): Observable<Post[]> {
    return this.http.post<Post[]>(  'posts/get-posts', null).pipe();
  }
}
