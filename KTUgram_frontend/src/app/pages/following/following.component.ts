import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/userService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.scss']
})
export class FollowingComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  username!: string | null;

  ngOnInit(): void {
    this.username = localStorage.getItem("username");
    if(this.username == null){
      this.router.navigate(["/login"]);
    }
  }

}
