import { Component, OnInit} from '@angular/core';
import {ActivatedRoute, Navigation, Route, Router} from "@angular/router";
import { AdminService } from '../../../services/adminService';
import {Comment} from "../../../models/comment";
import { CommentReport } from '../../../models/commentReport';

@Component({
  selector: 'app-comments-page',
  templateUrl: './comments-page.component.html',
  styleUrls: ['./comments-page.component.scss']
})
export class CommentsPageComponent implements OnInit {

  constructor(private activeRoute: ActivatedRoute, private adminService: AdminService){}

  id: string | null = null;

  userComments!: Comment[]

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(map => {
      this.id = map.get('id');

      if(this.id == null){
       this.id = "All comments";
       this.adminService.getCommentsByAllUsers().subscribe((data: Comment[]) => {
        this.userComments = data;
       });
      }
      else{
        this.adminService.getCommentsByUser(Number(this.id)).subscribe((data: Comment[]) => {
          this.userComments = data;
        });
      } 
    });
  }

  deleteComment(id: number): void{
    this.adminService.deleteUserComment(id).subscribe(() => {
      
      if(this.id == "All comments"){
      this.adminService.getCommentsByAllUsers().subscribe((data: Comment[]) => {
        this.userComments = data;
        });
      }
      else {
        this.adminService.getCommentsByUser(Number(this.id)).subscribe((data: Comment[]) => {
          this.userComments = data;
        });
      }
      });   
  }

  
  displayedColumns: string[] = ["id", "content", "date", "time", "post", "user", "reports", "delete"];
}
