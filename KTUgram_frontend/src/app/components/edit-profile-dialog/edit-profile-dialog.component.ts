import {Component, Inject, Input, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef, MatDialog} from "@angular/material/dialog";
import {FormBuilder, Validators} from "@angular/forms";
import {MatFormField, MatFormFieldControl} from "@angular/material/form-field";
import {MatSnackBar} from "@angular/material/snack-bar";
import { PostService } from 'src/app/services/postService';
import { RemoveProfileDialogComponent } from '../remove-profile-dialog/remove-profile-dialog.component';
import { UserService } from 'src/app/services/userService';
import {NgxPermissionsService} from "ngx-permissions";
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-profile-dialog',
  templateUrl: './edit-profile-dialog.component.html',
  styleUrls: ['./edit-profile-dialog.component.scss']
})
export class EditProfileDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private dialogRef: MatDialogRef<EditProfileDialogComponent>, private snackbar: MatSnackBar,  private userService: UserService, private dialog: MatDialog, private permissionService: NgxPermissionsService, private router: Router) { }

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
  onRemoveProfile()
  {
    let dialogRef = this.dialog.open(RemoveProfileDialogComponent, {data: this.data});
    dialogRef.afterClosed().subscribe(result => {
      if(result == true){
        this.userService.removeProfile().subscribe(()=>
        {
          this.userService.logout().subscribe(() =>
          {
            localStorage.removeItem("username");
            localStorage.removeItem("loggedIn");
            localStorage.removeItem("perms");
            this.permissionService.flushPermissions();
            this.permissionService.loadPermissions(['GUEST']);
            this.router.navigate(['/login']);
            console.log(this.permissionService.getPermissions());
            this.dialog.closeAll();
          });
        });
      }
    });    
  }

}
