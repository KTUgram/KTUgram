import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {BlockedUser} from "../../models/blockedUser";

@Component({
  selector: 'app-blocked-users-dialog',
  templateUrl: './blocked-users-dialog.component.html',
  styleUrls: ['./blocked-users-dialog.component.scss']
})
export class BlockedUsersDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialogRef: MatDialogRef<BlockedUsersDialogComponent>) { }

  blockedUsers!: BlockedUser[];
  ngOnInit(): void {
    this.blockedUsers = this.data.blockedUsers;
  }
  onClose(){
    this.dialogRef.close(undefined);
  }
  onUnblockClick(id: number){
    this.dialogRef.close(id);
  }

}
