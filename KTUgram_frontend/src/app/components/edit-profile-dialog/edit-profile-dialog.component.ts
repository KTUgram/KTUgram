import {Component, Inject, Input, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, Validators} from "@angular/forms";
import {MatFormField, MatFormFieldControl} from "@angular/material/form-field";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-edit-profile-dialog',
  templateUrl: './edit-profile-dialog.component.html',
  styleUrls: ['./edit-profile-dialog.component.scss']
})
export class EditProfileDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private dialogRef: MatDialogRef<EditProfileDialogComponent>, private snackbar: MatSnackBar) { }

  uploadedFile: any = null;

  ngOnInit(): void {
  }

  onFileUpload(file: any){
    this.uploadedFile = file;
  }

  onDialogClose(){
    this.dialogRef.close();
  }

  onDialogDone(about: String){
    this.dialogRef.close({event: 'Done', data: {
        about: about,
        file: this.uploadedFile
      }})
  };

}
