import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-register-confirm-page',
  templateUrl: './register-confirm-page.component.html',
  styleUrls: ['./register-confirm-page.component.scss']
})
export class RegisterConfirmPageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  confirmed(){
    this.router.navigate(['/login']);
  }

}
