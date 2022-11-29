import {Component, Inject, OnInit} from '@angular/core';
import {UserService} from "../../services/userService";
import {Router} from "@angular/router";
import {Post} from "../../models/post";
import {PostService} from "../../services/postService";
import {AddPostComponent} from "../../components/add-post/add-post.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-newsfeed',
  templateUrl: './newsfeed.component.html',
  styleUrls: ['./newsfeed.component.scss']
})
export class NewsfeedComponent implements OnInit {
  private userService: UserService = Inject(UserService);
  //private postService: PostService = Inject(PostService);
  constructor(private postService: PostService, private dialog: MatDialog) { }

  posts!: Post[];

  ngOnInit(): void {
    this.postService.getPosts().subscribe(data => {
      this.posts = data;
      console.log(this.posts)
    });
  }

  onAddPostClicked(){
    this.dialog.open(AddPostComponent);
  }
}
