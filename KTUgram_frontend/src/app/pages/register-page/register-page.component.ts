import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/userService";
import {Router} from "@angular/router";
import {Person} from "../../models/person";

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  registerSubmit(person: Person){
    console.log(person);
    this.userService.register(person).subscribe(response => {
      this.router.navigate(["/confirm"]);
    })
  }

}
