import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../services/userService";

@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.scss']
})
export class UserCardComponent implements OnInit {

  constructor(private userService: UserService) { }

  @Input() user!: User;
  @Output() onUnfollow: EventEmitter<number> = new EventEmitter<number>();


  ngOnInit(): void {
  }

  unfollowClicked(){
    this.onUnfollow.emit(this.user.id);
  }

}
