import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Message} from "../../models/message";

@Component({
  selector: 'app-found-message-dialog',
  templateUrl: './found-message-dialog.component.html',
  styleUrls: ['./found-message-dialog.component.scss']
})
export class FoundMessageDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialogRef: MatDialogRef<FoundMessageDialogComponent>) { }

  matchingMessages !: Message[]
  ngOnInit(): void {
    this.matchingMessages = this.data.matchingMessages;
  }

  onJumpToMessage(message: Message){
    this.dialogRef.close(message.id);
  }
  onClose(){
    this.dialogRef.close(undefined);
  }

}
