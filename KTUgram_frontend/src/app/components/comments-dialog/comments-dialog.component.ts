import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {PostService} from "../../services/postService";
import {Comment} from "../../models/comment";
import {Post} from "../../models/post";

@Component({
  selector: 'app-comments-dialog',
  templateUrl: './comments-dialog.component.html',
  styleUrls: ['./comments-dialog.component.scss']
})
export class CommentsDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private postService: PostService) {}

  comments!: Comment[];

  ngOnInit(): void {
    this.getComments();
  }

  getComments(){
    this.postService.getComments(this.data.id).subscribe((data: Comment[]) => {
      this.comments = data;
    })
  }

  onAddCommentClick(content: string){
    let post: Post;
    console.log(content)
    this.postService.getPost(this.data.id).subscribe(data => {
      post = data;
      let comment: Comment = {content: content, post: post};
      this.postService.addComment(comment).subscribe(data => {
        this.getComments();
      });
    })
  }
}
