import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { AdminService } from '../../../services/adminService';
import {UserReport} from "../../../models/userReport";

@Component({
  selector: 'app-user-reports-page',
  templateUrl: './user-reports-page.component.html',
  styleUrls: ['./user-reports-page.component.scss']
})
export class UserReportsPageComponent implements OnInit {

  constructor(private activeRoute: ActivatedRoute, private adminService: AdminService){}

  id: string | null = null;
  userReports!: UserReport[];

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(map => {
      this.id = map.get('id');
        this.adminService.getUserReportsById(Number(this.id)).subscribe((data: UserReport[]) => {
          this.userReports = data;
        });
    });
  }

  deleteUserReport(id: number): void {
    this.adminService.deleteUserReport(id).subscribe(() => {
        this.adminService.getUserReportsById(Number(this.id)).subscribe((data: UserReport[]) => {
          this.userReports = data;
        });
      });    
  }


  displayedColumns: string[] = ["id", "reportedUser", "date", "time", "reasonComment", "reason", "reporter",  "delete"];
}
