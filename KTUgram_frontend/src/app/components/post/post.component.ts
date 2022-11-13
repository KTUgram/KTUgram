import {Component, Input, OnChanges} from '@angular/core';
import {Post} from "../../models/post";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnChanges {

  constructor() { }

  @Input() post!: Post; //{id:0, about:"", content:"", date:new Date(), location: "", state: "", time: "", title: "", user: };

  ngOnChanges() {
    console.log(this.post);
  }

}
