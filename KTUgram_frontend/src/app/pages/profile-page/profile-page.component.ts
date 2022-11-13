import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/userService";
import {User} from "../../models/user";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {

  constructor(private userService: UserService, private activeRoute: ActivatedRoute) { }

  user!: User

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(param => {
      if(param.get("id") != null){
        // @ts-ignore
        let id: number = +param.get("id");
        this.userService.getUserById(id).subscribe(user => {
          this.user = user;
        });
      }
    })
  }

}
