import { Component, OnInit} from '@angular/core';
import {ActivatedRoute, Navigation, Route, Router} from "@angular/router";
import { AdminService } from '../../../../services/adminService';
import {CommentReport} from "../../../../models/commentReport";


@Component({
  selector: 'app-commentReports-page',
  templateUrl: './commentReports-page.component.html',
  styleUrls: ['./commentReports-page.component.scss']
})
export class CommentReportsPageComponent implements OnInit {

  constructor(private activeRoute: ActivatedRoute, private adminService: AdminService){}

  id: string | null = null;

  userCommentReports!: CommentReport[]

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(map => {
      this.id = map.get('id');     
        this.adminService.getCommentReportsByCommentId(Number(this.id)).subscribe((data: CommentReport[]) => {
          this.userCommentReports = data;
        });
      
    });
  }
  deleteReport(id: number) :void{
    this.adminService.deleteCommentReportById(id).subscribe(()=>{
      this.adminService.getCommentReportsByCommentId(Number(this.id)).subscribe((data: CommentReport[]) => {
        this.userCommentReports = data;
      });
    });
  }

  displayedColumns: string[] = ["id", "reason", "reasonComment", "date", "time", "comment", "user", "delete"];
}
