import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../services/userService";
import {Post} from "../../models/post";
import {Comment} from "../../models/comment";
import {CommentsDialogComponent} from "../../components/comments-dialog/comments-dialog.component";
import {PostService} from "../../services/postService";
import {MatDialog} from "@angular/material/dialog";
import {EditProfileDialogComponent} from "../../components/edit-profile-dialog/edit-profile-dialog.component";

@Component({
  selector: 'app-user-profile-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.scss']
})
export class UserProfilePageComponent implements OnInit {

  constructor(private userService: UserService, private postService: PostService, private dialog: MatDialog) { }

  user!: User;
  userPosts!: Post[];
  following!: User[];
  followers!: User[];
  isFollowing: boolean = false;
  likedPostsId: number[] = [];
  postComments: Map<number, Comment[]> = new Map<number, Comment[]>();

  ngOnInit(): void {
    this.init();
  }

  init(){
    this.userService.getUser().subscribe(response => {
      this.user = response;
      this.getFollowingInfo();
      this.getPosts();
    });
  }

  getFollowingInfo(){
    if(this.user.id){
      let id = this.user.id;
      this.userService.getFollowers(id).subscribe(followers => {
        this.followers = followers;
      })
      this.userService.getFollowing(id).subscribe(following => {
        this.following = following;
      })
      this.userService.isFollowing(id).subscribe(response => {
        this.isFollowing = response
      })
    }
  }

  getPosts(){
    if(this.user.id){
      let id = this.user.id
      this.postService.getPostsByUser(id).subscribe(posts => {
        this.userPosts = posts;
        posts.forEach(post => {
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
      this.init();
    })
  }

  getPostComments(id: number): Comment[] | undefined{
    let comments = this.postComments.get(id);
    if(comments){
      return comments;
    }
    return undefined;
  }

  onEditClicked(){
    let dialogRef = this.dialog.open(EditProfileDialogComponent, {data: {
      about: this.user.about
      }});

    dialogRef.afterClosed().subscribe(result => {
      console.log(result)
      if(result.event == "Done"){
        this.userService.updateProfile(result.data.about, result.data.file).subscribe(() => {
          this.init();
        });
      }
    })
  }

}
