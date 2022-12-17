import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {PostService} from "../../services/postService";
import {Comment} from "../../models/comment";
import {Post} from "../../models/post";
import {EditCommentDialogComponent} from "../edit-comment-dialog/edit-comment-dialog.component";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DeleteDialogComponent} from "../delete-dialog/delete-dialog.component";
import {ReportDialogComponent} from "../report-dialog/report-dialog.component";

@Component({
  selector: 'app-comments-dialog',
  templateUrl: './comments-dialog.component.html',
  styleUrls: ['./comments-dialog.component.scss']
})
export class CommentsDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private postService: PostService, private dialog: MatDialog, private snackbar: MatSnackBar) {}

  comments!: Comment[];
  userComments!: Comment[];

  ngOnInit(): void {
    this.getComments();
  }

  getComments(){
    this.postService.getCommentsByUser(this.data.id).subscribe((data: Comment[]) => {
      this.userComments = data;
    })
    this.postService.getCommentsExceptByUser(this.data.id).subscribe((data: Comment[]) => {
      this.comments = data;
    })
  }

  onAddCommentClick(commentField: any){
    let post: Post;
    let content = commentField.value;
    if(content === ""){
      this.snackbar.open("Please fill out comment", "Dismiss", {duration: 3000, panelClass: ['mat-accent']});
      return;
    }
    this.postService.getPost(this.data.id).subscribe(data => {
      post = data;
      let comment: Comment = {content: content, post: post};
      this.postService.addComment(comment).subscribe(data => {
        this.getComments();
        commentField.value = "";
      });
    })
  }

  onEditCommentClick(comment: Comment){
    console.log(comment);
    let dialogRef = this.dialog.open(EditCommentDialogComponent, {data: {comment: comment}})
    dialogRef.afterClosed().subscribe(response => {
      if(response != undefined){
        this.postService.editComment(response).subscribe(() => {
          this.getComments();
        })
      }
    })
  }

  onDeleteCommentClick(comment: Comment){
    let dialogRef = this.dialog.open(DeleteDialogComponent, {data: {
      comment: comment.content
      }});
    dialogRef.afterClosed().subscribe(result => {
      if(result == true){
        this.postService.deleteComment(comment).subscribe(() => {
          this.getComments();
        });
      }
    });
  }

  onReportCommentClick(comment: Comment)
  {
    let dialogRef = this.dialog.open(ReportDialogComponent, {data: {comment}});
    dialogRef.afterClosed().subscribe(result => {
      if(result == true){
        this.postService.deleteComment(comment).subscribe(() => {
          this.getComments();
        });
      }
    });
  }
}
