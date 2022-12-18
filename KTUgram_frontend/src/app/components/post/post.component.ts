import {Component, EventEmitter, Input, OnChanges, Output} from '@angular/core';
import {Post} from "../../models/post";
import {Comment} from "../../models/comment";
import { DeletePostDialogComponent } from '../delete-post-dialog/delete-post-dialog.component';
import { PostService } from 'src/app/services/postService';
import { MatDialog } from '@angular/material/dialog';
import { userInfo } from 'os';
import { User } from 'src/app/models/user';
import { ReportPostDialogComponent } from '../report-post-dialog/report-post-dialog.component';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnChanges {

  constructor(private postService: PostService, private dialog: MatDialog) { }

  user?: User;

  @Input() post!: Post;
  @Input() liked: boolean = false;
  @Input() comments: Comment[] | undefined;
  @Output() likedId: EventEmitter<number> = new EventEmitter<number>();
  @Output() onComment: EventEmitter<number> = new EventEmitter<number>();

  ngOnInit(){
    console.log("aaaaa");
  }

  ngOnChanges() {
    console.log(this.post);
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
        this.postService.deletePost(this.post).subscribe(() => {
          
        });
      }
    });
  }

  onReportDialog(){
    let dialogRef = this.dialog.open(ReportPostDialogComponent, {data: this.user});
    dialogRef.afterClosed().subscribe(result => {
      if(result == true){
        
      }
    });
  }
}
