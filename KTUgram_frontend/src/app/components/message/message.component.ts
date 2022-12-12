import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Post} from "../../models/post";
import {Comment} from "../../models/comment";
import {MessageService} from "../../services/messageService";
import {Router} from "@angular/router";
import {User} from "../../models/user";
import {Message} from "../../models/message";

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.scss']
})
export class MessageComponent implements OnChanges {
  constructor() { }

  @Input() user !: User;
  @Input() messages: Message[] | undefined;
  @Output() onMessage: EventEmitter<number> = new EventEmitter<number>();


  ngOnChanges() {
  }

  onOpenMessage() {
    this.onMessage.emit(this.user.id);
  }
}
