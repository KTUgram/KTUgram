import { Component, OnInit } from '@angular/core';
import {User} from "../../../models/user";
import {AdminService} from "../../../services/adminService";

@Component({
  selector: 'app-users-table',
  templateUrl: './users-table.component.html',
  styleUrls: ['./users-table.component.scss']
})
export class UsersTableComponent implements OnInit {

  constructor(private adminService: AdminService) { }

  allUsers!: User[];

  ngOnInit(): void {
    this.adminService.getAllUsers().subscribe(data => {
      this.allUsers = data;
      console.log(this.allUsers);
    })
  }

  displayedColumns: string[] = ["id", "username", "name", "surname", "email", "comments", "posts", "block"];
}
