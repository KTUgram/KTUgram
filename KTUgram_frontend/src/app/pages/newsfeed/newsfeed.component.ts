import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/userService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-newsfeed',
  templateUrl: './newsfeed.component.html',
  styleUrls: ['./newsfeed.component.scss']
})
export class NewsfeedComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  username!: string | null;

  ngOnInit(): void {
    this.username = localStorage.getItem("username");
    if(this.username == null){
      this.router.navigate(["/login"]);
    }
  }

}
