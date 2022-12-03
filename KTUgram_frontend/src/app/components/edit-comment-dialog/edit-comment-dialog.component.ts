import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Comment} from "../../models/comment";

@Component({
  selector: 'app-edit-comment-dialog',
  templateUrl: './edit-comment-dialog.component.html',
  styleUrls: ['./edit-comment-dialog.component.scss']
})
export class EditCommentDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialogRef: MatDialogRef<EditCommentDialogComponent>) { }

  comment!: Comment;

  ngOnInit(): void {
    this.comment = this.data.comment;
  }

  onDone(content: string){
    this.comment.content = content;
    this.dialogRef.close(this.comment)
  }

  onClose(){
    this.dialogRef.close(undefined);
  }

}
