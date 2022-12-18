import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Message} from "../../models/message";

@Component({
  selector: 'app-remove-profile-dialog',
  templateUrl: './remove-profile-dialog.component.html',
  styleUrls: ['./remove-profile-dialog.component.scss']
})
export class RemoveProfileDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialogRef: MatDialogRef<RemoveProfileDialogComponent>) { }

  message!: Message;

  ngOnInit(): void {
    this.message = this.data.message;
  }

  onDelete(){
    this.dialogRef.close(true);
  }

  onCancel(){
    this.dialogRef.close(false);
  }

}
