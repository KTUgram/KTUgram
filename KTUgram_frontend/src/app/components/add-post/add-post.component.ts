import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {PostService} from "../../services/postService";
import {Post} from "../../models/post";

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.scss']
})
export class AddPostComponent implements OnInit {

  constructor(private postService: PostService) { }

  uploadedFile: any = null
  uploadedUrl: any = null

  ngOnInit(): void {
  }

  onAddPost(title: any, about: any){
    let post: Post = {date: new Date(Date.now()), time: "10", about: about.value, content: "null", location: "aa", title: title.value, state: "0"};
    this.postService.uploadPost(post, this.uploadedFile).subscribe(() => {
      console.log("Posted");
    });
  }

  fileUploaded(file: any){
    this.uploadedFile = file;
  }

}
