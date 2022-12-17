import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MessageService} from "../../services/messageService";
import {Router} from "@angular/router";
import {User} from "../../models/user";
import {Comment} from "../../models/comment";
import {Message} from "../../models/message";
import {CommentsDialogComponent} from "../../components/comments-dialog/comments-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {MessageDialogComponent} from "../../components/message-dialog/message-dialog.component";
import {BlockedUser} from "../../models/blockedUser";
import {BlockedUsersDialogComponent} from "../../components/blocked-users-dialog/blocked-users-dialog.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatPageComponent implements OnInit {

  constructor(private messageService: MessageService, private dialog: MatDialog, private snackbar: MatSnackBar) { }


  users !: User[];
  userMessages: Map<number, Message[]> = new Map<number, Message[]>();

  blockedUsers !: BlockedUser[];

  ngOnInit(): void {
    this.getUsers();
    this.getBlockedUsers();
  }



  getUsers(){
    this.messageService.getAllUsers().subscribe(data => {
      this.users = data;
      data.forEach(user => {
        if(user.id){
          console.log(user.id);
          this.messageService.getUserMessages(user.id).subscribe(messages =>{
            if(user.id){
              this.userMessages.set(user.id, messages);
            }
          }
          )
        }
      })
    });
  }
  getBlockedUsers(){
    this.messageService.getBlockedUsers().subscribe(data => {
      this.blockedUsers = data;
    })
  }

  getUserMessages(id: number): Message[] | undefined{
    let messages = this.userMessages.get(id);
    if(messages){
      return messages;
    }
    return undefined;
  }

  onOpenMessages(id: number){
    let dialogRef = this.dialog.open(MessageDialogComponent, {data: {
        id: id
      }})
    dialogRef.afterClosed().subscribe(() => {
      this.getUsers();
    })
  }
  onOpenBlockedUsers() {
    this.messageService.getBlockedUsers().subscribe(data => {
      this.blockedUsers = data;
      if (this.blockedUsers.length != 0) {
        let dialogRef = this.dialog.open(BlockedUsersDialogComponent, {
          data: {
            blockedUsers: this.blockedUsers
          }
        })
        dialogRef.afterClosed().subscribe((blockDataId) => {
          if (blockDataId != undefined) {
            this.messageService.removeBlockedUser(blockDataId).subscribe(() => {
              this.getUsers();
              this.getBlockedUsers();
            })
          }
        })
      } else {
        this.snackbar.open("Don't have blocked users", "Dismiss", {duration: 3000, panelClass: ['mat-accent']});
        return;
      }
    })
  }
}
