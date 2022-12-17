import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import { symlinkSync } from 'fs';
import {Comment} from "../../models/comment";
import {CommentReport} from "../../models/commentReport";
import {PostService} from "../../services/postService";

@Component({
  selector: 'app-report-dialog',
  templateUrl: './report-dialog.component.html',
  styleUrls: ['./report-dialog.component.scss']
})
export class ReportDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialogRef: MatDialogRef<ReportDialogComponent>, private postService: PostService) { }

  comment!: Comment;

  ngOnInit(): void {
    this.comment = this.data.comment;
  }

  onReport(content: string){
    
    let report: CommentReport = {reason: 1, comment: this.comment, reasonComment: content};

    this.postService.reportComment(report).subscribe(() =>
    {
      this.dialogRef.close(content); 
    });       
  }

  onCancel(){
    this.dialogRef.close(false);
  }

}
