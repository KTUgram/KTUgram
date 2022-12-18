import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../../services/userService";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-register-confirm-page',
  templateUrl: './register-confirm-page.component.html',
  styleUrls: ['./register-confirm-page.component.scss']
})
export class RegisterConfirmPageComponent implements OnInit {

  constructor(private router: Router, private userService: UserService, private snackbar: MatSnackBar) { }

  ngOnInit(): void {
  }

  confirmed(code: string){
    this.userService.confirmRegistration(code).subscribe(response => {
      if(response.status == 200){
        this.snackbar.open("Successful operation", "Dismiss", {duration: 3000});
        this.router.navigate(['/login']);
        return;
      }
      this.snackbar.open("Incorrect code", "dismiss",{duration: 3000})
    })
  }
}
