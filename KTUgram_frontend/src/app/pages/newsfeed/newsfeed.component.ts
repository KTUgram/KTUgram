import {Component, Inject, OnInit} from '@angular/core';
import {UserService} from "../../services/userService";
import {Router} from "@angular/router";
import {Post} from "../../models/post";
import {PostService} from "../../services/postService";
import {AddPostComponent} from "../../components/add-post/add-post.component";
import {MatDialog} from "@angular/material/dialog";
import {CommentsDialogComponent} from "../../components/comments-dialog/comments-dialog.component";

@Component({
  selector: 'app-newsfeed',
  templateUrl: './newsfeed.component.html',
  styleUrls: ['./newsfeed.component.scss']
})
export class NewsfeedComponent implements OnInit {
  constructor(private postService: PostService, private dialog: MatDialog) { }

  posts!: Post[];
  likedPostsId: number[] = [];

  ngOnInit(): void {
    this.getPosts();
    this.getLikedPosts();
  }

  onAddPostClicked(){
    let dialogRef = this.dialog.open(AddPostComponent);
    dialogRef.afterClosed().subscribe((result: any) => {
      if(result == true){
        this.getPosts();
      }
    })
  }

  getPosts(){
    this.postService.getPosts().subscribe(data => {
      this.posts = data;
      this.dialog.closeAll();
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
    console.log(id);
    let dialogRef = this.dialog.open(CommentsDialogComponent, {data: {
      id: id
      }})
  }
}
