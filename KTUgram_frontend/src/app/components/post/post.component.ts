import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {Post} from "../../models/post";
import {Comment} from "../../models/comment";
import { DeletePostDialogComponent } from '../delete-post-dialog/delete-post-dialog.component';
import { PostService } from 'src/app/services/postService';
import { MatDialog } from '@angular/material/dialog';
import { userInfo } from 'os';
import { User } from 'src/app/models/user';
import {UserService} from "../../services/userService";
import {ReportDialogComponent} from "../report-dialog/report-dialog.component";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  constructor(private postService: PostService, private dialog: MatDialog, private userService: UserService) { }

  user?: User;

  @Input() post!: Post;
  @Input() liked: boolean = false;
  @Input() comments: Comment[] | undefined;
  @Output() likedId: EventEmitter<number> = new EventEmitter<number>();
  @Output() onComment: EventEmitter<number> = new EventEmitter<number>();
  @Output() onDelete: EventEmitter<number> = new EventEmitter<number>();

  ngOnInit(){
    this.userService.getUser().subscribe(response => {
      this.user = response;
    })
  }

  onLike(){
    this.likedId.emit(this.post.id);
  }

  onOpenComments(){
    this.onComment.emit(this.post.id);
  }
  onDeleteDialog()  {
    let dialogRef = this.dialog.open(DeletePostDialogComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if(result == true){
        this.onDelete.emit(this.post.id);
      }
    });
  }

  onReportDialog(){
    let dialogRef = this.dialog.open(ReportDialogComponent, {data: {id: this.post.id}});
    dialogRef.afterClosed().subscribe(result => {
      if(result != null && this.post.id){
        this.postService.reportPost(this.post.id, result.comment).subscribe();
      }
    });
  }
}
