import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/userService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  username!: string | null;

  ngOnInit(): void {
    this.username = localStorage.getItem("username");
    if(this.username == null){
      this.router.navigate(["/login"]);
    }
  }

  logout(){
    this.userService.logout().subscribe(result => {
      localStorage.removeItem("username");
      this.router.navigate(['/login']);
    })
  }
}
