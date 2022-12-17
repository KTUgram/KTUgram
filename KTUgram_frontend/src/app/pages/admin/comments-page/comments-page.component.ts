import { Component, OnInit} from '@angular/core';
import {ActivatedRoute, Navigation, Route, Router} from "@angular/router";
import { AdminService } from '../../../services/adminService';
import {Comment} from "../../../models/comment";
import {CommentTuple} from "../../../models/commentTuple";
import { CommentReport } from '../../../models/commentReport';

@Component({
  selector: 'app-comments-page',
  templateUrl: './comments-page.component.html',
  styleUrls: ['./comments-page.component.scss']
})
export class CommentsPageComponent implements OnInit {

  constructor(private activeRoute: ActivatedRoute, private adminService: AdminService){}

  id: string | null = null;

  userComments!: CommentTuple[]
  wasSorted: boolean = false;

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(map => {
      this.id = map.get('id');

      if(this.id == null){
       this.id = "All comments";
       this.adminService.getCommentsByAllUsers().subscribe((data: CommentTuple[]) => {
        this.userComments = data;
       });
       
      }
      else{
        this.adminService.getCommentsByUser(Number(this.id)).subscribe((data: CommentTuple[]) => {
          this.userComments = data;
        });
      } 
    });
  }

  deleteComment(id: number): void{
    this.adminService.deleteUserComment(id).subscribe(() => {
      this.GetItemsOnReload();    
    });   
  }

  sortByReports():void{
    if(!this.wasSorted){
      this.wasSorted = true;
    }
    else{
      this.wasSorted = false;
    }
    this.GetItemsOnReload();
  }


  GetItemsOnReload() :void
  {
    if(this.wasSorted){
      if(this.id == "All comments"){
        this.adminService.getCommentsByAllUsersSortedByReports().subscribe((data: CommentTuple[]) => {
          this.userComments = data;
          });
      }
      else {
          this.adminService.getCommentsByUserSortedByReports(Number(this.id)).subscribe((data: CommentTuple[]) => {
            this.userComments = data;
          });
      }
    }
    else{
      if(this.id == "All comments"){
        this.adminService.getCommentsByAllUsers().subscribe((data: CommentTuple[]) => {
          this.userComments = data;
          });
      }
      else {
          this.adminService.getCommentsByUser(Number(this.id)).subscribe((data: CommentTuple[]) => {
            this.userComments = data;
          });
      }
    }
  }
  
  displayedColumns: string[] = ["id", "content", "date", "time", "post", "user", "reports", "delete", "reportCount"];
}
