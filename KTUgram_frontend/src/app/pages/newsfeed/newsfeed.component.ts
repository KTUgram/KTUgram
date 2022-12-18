import {Component, Inject, OnInit} from '@angular/core';
import {UserService} from "../../services/userService";
import {Router} from "@angular/router";
import {Post} from "../../models/post";
import {PostService} from "../../services/postService";
import {AddPostComponent} from "../../components/add-post/add-post.component";
import {MatDialog} from "@angular/material/dialog";
import {CommentsDialogComponent} from "../../components/comments-dialog/comments-dialog.component";
import {Comment} from "../../models/comment";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-newsfeed',
  templateUrl: './newsfeed.component.html',
  styleUrls: ['./newsfeed.component.scss']
})
export class NewsfeedComponent implements OnInit {
  constructor(private postService: PostService, private dialog: MatDialog, private snackbar: MatSnackBar) { }

  posts!: Post[];
  likedPostsId: number[] = [];
  postComments: Map<number, Comment[]> = new Map<number, Comment[]>();

  ngOnInit(): void {
    this.getPosts();
    this.getLikedPosts();
  }

  onAddPostClicked(){
    let dialogRef = this.dialog.open(AddPostComponent);
    dialogRef.afterClosed().subscribe((result: any) => {
      if(result == true){
        this.snackbar.open("Successful operation", "Dismiss", {duration: 3000});
        this.getPosts();
      }
    })
  }

  getPosts(){
    this.postService.getPosts().subscribe(data => {
      this.posts = data;
      data.forEach(post => {
        if(post.id){
          this.postService.getComments(post.id).subscribe(comments => {
            if (post.id) {
              this.postComments.set(post.id, comments);
            }
          })
        }
      })
    });
  }

  getLikedPosts(){
    this.postService.getLikedPosts().subscribe(data => {
      this.likedPostsId = []
      data.forEach((item: any) => {
        this.likedPostsId.push(item.post.id);
      })
    });
  }

  onPostLiked(id: number){
    this.postService.likePost(id).subscribe(() => {
      this.getLikedPosts();
    })
  }

  isLiked(id: number): boolean{
    return this.likedPostsId.includes(id);
  }

  onOpenComments(id: number){
    let dialogRef = this.dialog.open(CommentsDialogComponent, {data: {
      id: id
      }})
    dialogRef.afterClosed().subscribe(() => {
      this.getPosts();
    })
  }

  getPostComments(id: number): Comment[] | undefined{
    let comments = this.postComments.get(id);
    if(comments){
      return comments;
    }
    return undefined;
  }

  onDeletePost(id: number){
    this.postService.deletePost(id).subscribe(() => {
      this.getPosts();
      this.getLikedPosts();
    });
  }
}
