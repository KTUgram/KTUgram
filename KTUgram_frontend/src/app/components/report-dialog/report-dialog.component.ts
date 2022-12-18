import {Component, Inject, Injector, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-report-dialog',
  templateUrl: './report-dialog.component.html',
  styleUrls: ['./report-dialog.component.scss']
})
export class ReportDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialogRef: MatDialogRef<ReportDialogComponent>, private snackbar: MatSnackBar) { }

  ngOnInit(): void {
  }

  onClose(){
    this.dialogRef.close(null);
  }

  onDone(comment: string){
    this.snackbar.open("Successful operation", "Dismiss", {duration: 3000});
    this.dialogRef.close({id: this.data.id, comment: comment});
  }
}
