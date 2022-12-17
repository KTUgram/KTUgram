import { Component, Inject, OnInit } from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-block-user-message-dialog',
  templateUrl: './block-user-message-dialog.component.html',
  styleUrls: ['./block-user-message-dialog.component.scss']
})
export class BlockUserMessageDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialogRef: MatDialogRef<BlockUserMessageDialogComponent>) { }

  user!: User;

  ngOnInit(): void {
    this.user = this.data.user;
  }

  onBlock(){
    this.dialogRef.close(true);
  }

  onCancel(){
    this.dialogRef.close(false);
  }

}
