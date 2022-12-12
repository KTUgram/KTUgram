import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { AdminService } from '../../../services/adminService';
import {Post} from "../../../models/post";

@Component({
  selector: 'app-user-posts-page',
  templateUrl: './user-posts-page.component.html',
  styleUrls: ['./user-posts-page.component.scss']
})
export class UserPostsPageComponent implements OnInit {

  constructor(private activeRoute: ActivatedRoute, private adminService: AdminService){}

  id: string | null = null;
  userPosts!: Post[]

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(map => {
      this.id = map.get('id');
        this.adminService.getUserPosts(Number(this.id)).subscribe((data: Post[]) => {
          this.userPosts = data;
        });
    });
  }

  deletePost(id: number): void {
    this.adminService.deleteUserPost(id).subscribe(() => {
        this.adminService.getUserPosts(Number(this.id)).subscribe((data: Post[]) => {
          this.userPosts = data;
        });
      });    
  }

  displayedColumns: string[] = ["id", "title", "date", "time", "about", "content", "user", "delete"];
}
