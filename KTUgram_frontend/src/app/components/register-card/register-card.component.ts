import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Person} from "../../models/person";

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

  signupButtonClicked(username: any, email: any, pass: any, name: any, surname: any){
    let person: Person = {username: username, email: email, password: pass, name: name, surname: surname};
    this.submitEvent.emit(person);
  }

}
