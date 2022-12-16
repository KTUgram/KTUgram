import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/userService";
import {Router} from "@angular/router";
import {User} from "../../models/user";

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.scss']
})
export class FollowingComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  following!: User[];

  ngOnInit(): void {
    this.init();
  }

  init(){
    this.userService.getUserFollowing().subscribe(response => {
      this.following = response;
    });
  }

  onUnfollow(id: number){
    this.userService.updateFollow(id).subscribe(() => {
      this.init();
    });
  }

}
