import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-register-card',
  templateUrl: './register-card.component.html',
  styleUrls: ['./register-card.component.scss']
})
export class RegisterCardComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  @Output() submitEvent: EventEmitter<any> = new EventEmitter<any>();

  signupButtonClicked(username: any, email: any, pass: any){
    this.submitEvent.emit({username: username, email: email, password: pass});
  }

}
