import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MessageService} from "../../services/messageService";
import {Router} from "@angular/router";
import {User} from "../../models/user";
import {Comment} from "../../models/comment";
import {Message} from "../../models/message";
import {CommentsDialogComponent} from "../../components/comments-dialog/comments-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {MessageDialogComponent} from "../../components/message-dialog/message-dialog.component";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatPageComponent implements OnInit {

  constructor(private messageService: MessageService, private dialog: MatDialog) { }


  //usersId: number[] = [];
  //userMessages: Map<number, Message[]> = new Map<number, Message[]>();
  //userMessages !: Message[];

  users !: User[];
  userMessages: Map<number, Message[]> = new Map<number, Message[]>();

  ngOnInit(): void {
    this.getUsers();
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

}
