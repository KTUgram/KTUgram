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
  temp = new Array(10);
  ngOnInit(): void {
  }

}
