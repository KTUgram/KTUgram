import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-upload-img',
  templateUrl: './upload-img.component.html',
  styleUrls: ['./upload-img.component.scss']
})
export class UploadImgComponent implements OnInit {

  constructor() { }


  ngOnInit(): void {
  }

  uploadedUrl: any = null

  @Output() fileDropped: EventEmitter<any> = new EventEmitter<any>();

  onDrop(file: any){
    this.fileDropped.emit(file)
    var reader = new FileReader();
    reader.readAsDataURL(file);

    reader.onload = (event) => {
      this.uploadedUrl = reader.result;
    }
  }

  onUploadClick(element: any){
    element.click();
  }

  onUpload(event: any){
    let file = event.target.files[0];
    this.fileDropped.emit(file);
    var reader = new FileReader();
    reader.readAsDataURL(file);

    reader.onload = (event) => {
      this.uploadedUrl = reader.result;
    }
  }

}
