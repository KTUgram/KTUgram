import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Message} from "../../models/message";

@Component({
  selector: 'app-delete-message-dialog',
  templateUrl: './delete-message-dialog.component.html',
  styleUrls: ['./delete-message-dialog.component.scss']
})
export class DeleteMessageDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialogRef: MatDialogRef<DeleteMessageDialogComponent>) { }

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
