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
    })
  }
  blockUser(id: any): void{
    console.log(id)
    this.adminService.blockUser(id).subscribe(()=>{
      this.adminService.getAllUsers().subscribe(data => {
        this.allUsers = data;
      })
    });    
  }
  unblockUser(id: any):void{
    this.adminService.unblockUser(id).subscribe(()=>{
      this.adminService.getAllUsers().subscribe(data => {
        this.allUsers = data;
      })
    });
  }

  displayedColumns: string[] = ["id", "username", "name", "surname", "email", "comments", "posts", "block"];
}
