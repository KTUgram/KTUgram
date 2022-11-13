import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-posts-page',
  templateUrl: './user-posts-page.component.html',
  styleUrls: ['./user-posts-page.component.scss']
})
export class UserPostsPageComponent implements OnInit {

  constructor(private activeRoute: ActivatedRoute){}

  id: string | null = null;

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(map => {
      this.id = map.get('id');
    });
  }

}
