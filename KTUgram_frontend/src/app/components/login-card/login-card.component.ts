import {Component, OnInit, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-login-card',
  templateUrl: './login-card.component.html',
  styleUrls: ['./login-card.component.scss']
})
export class LoginCardComponent implements OnInit {
  constructor() { }

  ngOnInit(): void {
  }

  @Output() submitEvent: EventEmitter<any> = new EventEmitter<any>();

  loginButtonClicked(username: any, pass: any){
    this.submitEvent.emit({username: username, password: pass});
  }

}
