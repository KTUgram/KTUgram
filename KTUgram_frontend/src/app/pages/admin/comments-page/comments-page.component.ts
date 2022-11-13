import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Navigation, Route, Router} from "@angular/router";

@Component({
  selector: 'app-comments-page',
  templateUrl: './comments-page.component.html',
  styleUrls: ['./comments-page.component.scss']
})
export class CommentsPageComponent implements OnInit {

  constructor(private activeRoute: ActivatedRoute){}

  id: string | null = null;

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(map => {
      this.id = map.get('id');

      if(this.id == null){
       this.id = "All comments";
      }
    });
  }
}
