import {HttpClient, HttpResponse} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {NgxPermissionsService} from "ngx-permissions";
import * as http from "http";
import {User} from "../models/user";

@Injectable({ providedIn: 'root' })
export class AdminService {
  constructor(private http: HttpClient, private permissionService: NgxPermissionsService) {
  }

  getAllUsers(){
    return this.http.post<User[]>("admin/all-users", null).pipe();
  }
}
