import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MessageService} from "../../services/messageService";
import {Message} from "../../models/message";
import {Post} from "../../models/post";
import {Comment} from "../../models/comment";
import {User} from "../../models/user";
import {Time} from "@angular/common";
import {EditCommentDialogComponent} from "../edit-comment-dialog/edit-comment-dialog.component";
import {EditMessageDialogComponent} from "../edit-message-dialog/edit-message-dialog.component";
import {DeleteDialogComponent} from "../delete-dialog/delete-dialog.component";
import {DeleteMessageDialogComponent} from "../delete-message-dialog/delete-message-dialog.component";

@Component({
  selector: 'app-message-dialog',
  templateUrl: './message-dialog.component.html',
  styleUrls: ['./message-dialog.component.scss']
})
export class MessageDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private messageService: MessageService, private dialog: MatDialog, private snackbar: MatSnackBar) {}

  otherUserMessages!: Message[];
  allMessages!: Message[];

  userMessages!: Message[];
  ngOnInit(): void {
    this.getMessages();
  }

  getMessages(){
    this.messageService.getUserMessages(this.data.id).subscribe((data: Message[]) => {
      this.allMessages = data;
    })
    this.messageService.getMessageExceptByUser(this.data.id).subscribe((data: Message[]) => {
      this.otherUserMessages = data;
    })
    this.messageService.getMessageByUser(this.data.id).subscribe((data: Message[]) => {
      this.userMessages = data;
    })
  }
  existsInUserMessages(message: Message){
    let value = false;
    this.userMessages.forEach( (userMessage) => {
      if(userMessage.id == message.id){
        value = true;
      }
    })
    return value;
  }
  existsInOtherUserMessages(message: Message){
    let value = false;
    this.otherUserMessages.forEach( (userMessage) => {
      if(userMessage.id == message.id){
        value = true;
      }
    })
    return value;
  }

  onReloadMessagesClick(){
    this.getMessages();
  }
  onAddMessageClick(messageField: any){
    let user: User;
    let content = messageField.value;
    if(content === ""){
      this.snackbar.open("Please fill out message", "Dismiss", {duration: 3000, panelClass: ['mat-accent']});
      return;
    }
    let message: Message = {content: content};
    this.messageService.addMessage(this.data.id, message).subscribe(value => {
       this.getMessages();
       messageField.value = "";
    })
  }

  onEditMessageClick(message: Message){
    let dialogRef = this.dialog.open(EditMessageDialogComponent, {data: {message: message}})
    dialogRef.afterClosed().subscribe(response => {
      if(response != undefined){
        this.messageService.editMessage(response).subscribe(() => {
          this.getMessages();
        })
      }
    })
  }
  onDeleteMessageClick(message: Message){
    let dialogRef = this.dialog.open(DeleteMessageDialogComponent, {data: {message: message }});
    dialogRef.afterClosed().subscribe(response => {
      if(response == true){
        this.messageService.deleteMessage(message).subscribe(() => {
          this.getMessages();
        });
      }
    });
  }
}
