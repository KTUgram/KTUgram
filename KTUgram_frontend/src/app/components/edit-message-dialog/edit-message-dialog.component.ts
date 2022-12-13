import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Message} from "../../models/message";

@Component({
  selector: 'app-edit-message-dialog',
  templateUrl: './edit-message-dialog.component.html',
  styleUrls: ['./edit-message-dialog.component.scss']
})
export class EditMessageDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialogRef: MatDialogRef<EditMessageDialogComponent>) { }

  message!: Message;

  ngOnInit(): void {
    this.message = this.data.message;
  }

  onDone(content: string){
    this.message.content = content;
    this.dialogRef.close(this.message)
  }

  onClose(){
    this.dialogRef.close(undefined);
  }

}
