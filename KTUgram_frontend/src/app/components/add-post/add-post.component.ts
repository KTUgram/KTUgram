import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {PostService} from "../../services/postService";
import {Post} from "../../models/post";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.scss']
})
export class AddPostComponent implements OnInit {

  constructor(private postService: PostService, private dialogRef: MatDialogRef<AddPostComponent>) { }

  uploadedFile: any = null

  ngOnInit(): void {
  }

  onAddPost(title: any, about: any){
    let post: Post = {date: new Date(Date.now()), time: "10", about: about.value, content: "null", location: "aa", title: title.value, state: "0"};
    this.postService.uploadPost(post, this.uploadedFile).subscribe(() => {
      this.dialogRef.close(true);
    });
  }

  fileUploaded(file: any){
    this.uploadedFile = file;
  }

}
