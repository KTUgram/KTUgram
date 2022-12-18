import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-warn-user-dialog',
  templateUrl: './warn-user-dialog.component.html',
  styleUrls: ['./warn-user-dialog.component.scss']
})
export class WarnUserDialogComponent implements OnInit {

  constructor(private dialogRed: MatDialogRef<WarnUserDialogComponent>, private snackbar: MatSnackBar) { }

  ngOnInit(): void {
  }

  onSend(message: string){
    if(message === ""){
      this.snackbar.open("Message cannot be empty", "dismiss", {duration: 3000})
      return;
    }

    this.dialogRed.close(message);
  }

  onClose(){
    this.dialogRed.close(null);
  }

}
