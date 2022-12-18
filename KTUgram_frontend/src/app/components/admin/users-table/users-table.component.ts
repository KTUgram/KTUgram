import { Component, OnInit } from '@angular/core';
import {User} from "../../../models/user";
import {UserTuple} from "../../../models/userTuple";
import {AdminService} from "../../../services/adminService";
import {MatDialog} from "@angular/material/dialog";
import {WarnUserDialogComponent} from "../warn-user-dialog/warn-user-dialog.component";

@Component({
  selector: 'app-users-table',
  templateUrl: './users-table.component.html',
  styleUrls: ['./users-table.component.scss']
})
export class UsersTableComponent implements OnInit {

  constructor(private adminService: AdminService, private dialog: MatDialog) { }

  allUsers!: UserTuple[];
  wasSorted: boolean = false;

  ngOnInit(): void {
    this.adminService.getAllUsers().subscribe(data => {
      this.allUsers = data;
    })
  }

  blockUser(id: any): void{
    this.adminService.blockUser(id).subscribe(()=>{
      this.OnUpdate();
    });
  }

  warnUser(id: number){
    let dialogRef = this.dialog.open(WarnUserDialogComponent, {data:{
      id: id
      }});

    dialogRef.afterClosed().subscribe((result: string) => {
      console.log(result)
      if(result == null){
        return;
      }
      this.adminService.warnUser(id, result).subscribe();
    })
  }

  unblockUser(id: any):void{
    this.adminService.unblockUser(id).subscribe(()=>{
      this.OnUpdate();
    });
  }

  sortUsersByReports():void{
    this.wasSorted = !this.wasSorted;
    this.OnUpdate();
  }

  OnUpdate():void
  {
    if(this.wasSorted){
      this.adminService.getAllUsersSortedByReports().subscribe(data => {
        this.allUsers = data;
      });
    }
    else{
      this.adminService.getAllUsers().subscribe(data => {
        this.allUsers = data;
      });
    }
  }

  displayedColumns: string[] = ["id", "username", "name", "surname", "email", "comments", "posts", "reports", "reportCount", "block", "warn"];
}
