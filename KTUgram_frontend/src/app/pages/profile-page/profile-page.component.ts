import { Component, Inject, OnInit } from '@angular/core';
import {UserService} from "../../services/userService";
import {User} from "../../models/user";
import {ActivatedRoute, Router} from "@angular/router";
import {Post} from "../../models/post";
import {PostService} from "../../services/postService";
import {Comment} from "../../models/comment";
import {AddPostComponent} from "../../components/add-post/add-post.component";
import {CommentsDialogComponent} from "../../components/comments-dialog/comments-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import { UserReport } from 'src/app/models/userReport';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import { ReportUserDialogComponent } from 'src/app/components/report-user-dialog/report-dialog.component';
import { ReportDialogComponent } from 'src/app/components/report-dialog/report-dialog.component';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {

  constructor(private userService: UserService, private activeRoute: ActivatedRoute, private postService: PostService, private dialog: MatDialog, private router: Router) { }

  user!: User
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
    this.activeRoute.paramMap.subscribe(param => {
      if(param.get("id") != null){
        // @ts-ignore
        let id: number = +param.get("id");
        this.userService.getUserById(id).subscribe(response => {
          if(response.body != null && response.status == 200){
            this.user = response.body;
            this.getFollowingInfo();
            this.getPosts(id);
            return;
          }

          if(response.status == 204){
            this.router.navigate(["/main"]);
            return;
          }

          if(response.status == 202){
            this.router.navigate(["/main/user-profile"]);
            return;
          }

        });
      }
    })
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

  getPosts(id: number){
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

  onReportClick(){
    let dialogRef = this.dialog.open(ReportUserDialogComponent, {data: this.user});
    dialogRef.afterClosed().subscribe(result => {
      if(result == true){

      }
    });
  }

  getPostComments(id: number): Comment[] | undefined{
    let comments = this.postComments.get(id);
    if(comments){
      return comments;
    }
    return undefined;
  }

  onFollowClicked(){
    if(this.user.id){
      this.userService.updateFollow(this.user.id).subscribe(() => {
        this.getFollowingInfo();
      });
    }
  }

  onDeletePost(id: number){
    this.postService.deletePost(id).subscribe(() => {
      this.init();
    });
  }
}
