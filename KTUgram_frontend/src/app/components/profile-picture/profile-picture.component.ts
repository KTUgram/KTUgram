import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-profile-picture',
  templateUrl: './profile-picture.component.html',
  styleUrls: ['./profile-picture.component.scss']
})
export class ProfilePictureComponent implements OnInit {

  constructor() { }

  @Input() pictureLocation: string | undefined;
  @Input() height!: string;
  @Input() width!: string;

  ngOnInit(): void {
  }
}
