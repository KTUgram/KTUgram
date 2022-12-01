import {Component, EventEmitter, Input, OnChanges, Output} from '@angular/core';
import {Post} from "../../models/post";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnChanges {

  constructor() { }

  @Input() post!: Post;
  @Input() liked: boolean = false;
  @Output() likedId: EventEmitter<number> = new EventEmitter<number>()

  ngOnChanges() {
    console.log(this.post);
  }

  onLike(){
    this.likedId.emit(this.post.id);
  }

}
