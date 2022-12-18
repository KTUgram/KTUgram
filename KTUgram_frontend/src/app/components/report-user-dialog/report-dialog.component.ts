import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import { symlinkSync } from 'fs';
import { User } from 'src/app/models/user';
import { UserReport } from 'src/app/models/userReport';
import {Comment} from "../../models/comment";
import {CommentReport} from "../../models/commentReport";
import {PostService} from "../../services/postService";

@Component({
  selector: 'app-report-dialog',
  templateUrl: './report-dialog.component.html',
  styleUrls: ['./report-dialog.component.scss']
})
export class ReportUserDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialogRef: MatDialogRef<ReportUserDialogComponent>, private postService: PostService) { }

  user!: User;

  ngOnInit(): void {
    this.user = this.data;
  }

  onReport(content: string){
    
    let report: UserReport = {reasonComment: content, reportedUser: this.user, reason: 1};
    console.log(this.user);
    this.postService.reportUser(report).subscribe(() =>
    {
      this.dialogRef.close(content); 
    });      
  }

  onCancel(){
    this.dialogRef.close(false);
  }

}
