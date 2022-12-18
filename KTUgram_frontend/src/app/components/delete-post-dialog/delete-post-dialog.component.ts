import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Comment} from "../../models/comment";

@Component({
  selector: 'app-delete-post-dialog',
  templateUrl: './delete-post-dialog.component.html',
  styleUrls: ['./delete-post-dialog.component.scss']
})
export class DeletePostDialogComponent implements OnInit {
  

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialogRef: MatDialogRef<DeletePostDialogComponent>) { }

  comment!: string;

  ngOnInit(): void {
    this.comment = this.data.comment;
  }

  onDelete(){
    this.dialogRef.close(true);
  }

  onCancel(){
    this.dialogRef.close(false);
  }

}
