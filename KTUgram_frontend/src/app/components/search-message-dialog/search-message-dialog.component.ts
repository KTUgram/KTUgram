import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {Message} from "../../models/message";
import {MatSnackBar} from "@angular/material/snack-bar";
import {FoundMessageDialogComponent} from "../found-message-dialog/found-message-dialog.component";

@Component({
  selector: 'app-search-message-dialog',
  templateUrl: './search-message-dialog.component.html',
  styleUrls: ['./search-message-dialog.component.scss']
})
export class SearchMessageDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialog: MatDialog, private dialogRef: MatDialogRef<SearchMessageDialogComponent>, private snackbar: MatSnackBar) { }
  messages!: Message[];

  matchingMessages: Message[] = [];

  ngOnInit(): void {
    this.messages = this.data.allMessages;
  }



  onClose(){
    this.dialogRef.close(undefined);
  }

  onSearch(searchField: any){
    let found = false;
    let content = searchField.value;
    if(content === ""){
      this.snackbar.open("Please fill out search message", "Dismiss", {duration: 3000, panelClass: ['mat-accent']});
      return;
    }
    else {
      if (this.messages != undefined) {
        this.messages.forEach((userMessage) => {
          if (userMessage.content.match(content)) {
            found = true;
            this.matchingMessages.push(userMessage);
          }
        })
      }
    }
    if(found) {
      let dialogRef = this.dialog.open(FoundMessageDialogComponent, {data: {matchingMessages: this.matchingMessages}});
      dialogRef.afterClosed().subscribe((responseId) => {
        if(responseId != undefined){
          this.dialogRef.close(responseId);
        }
        else{
          this.dialogRef.close(undefined);
        }
      })
    }
    else{
      this.snackbar.open("Could not find matching message", "Dismiss", {duration: 3000, panelClass: ['mat-accent']});
      return;
    }
    return found;
  }

}
