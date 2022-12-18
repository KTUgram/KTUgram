import {Component, EventEmitter, OnInit, Output, ViewEncapsulation} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {UserService} from "../../services/userService";
import {MatStepper} from "@angular/material/stepper";
import {Person} from "../../models/person";
import {User} from "../../models/user";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-register-card',
  templateUrl: './register-card.component.html',
  styleUrls: ['./register-card.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class RegisterCardComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private userService: UserService, private snackbar: MatSnackBar) { }

  uploadedFile: any = null;

  accountDetailsStepCompleted: boolean = false;
  personalDetailsStepCompleted: boolean = false;

  ngOnInit(): void {
  }

  accountDetailsGroup = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
    email: ['', Validators.required]
  });

  personalDetailsGroup = this.formBuilder.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    phone: ['', Validators.required],
    birthday: ['', Validators.required]
  })

  profileDetailsGroup = this.formBuilder.group({
    about: ['', Validators.required]
  })

  @Output() submitEvent: EventEmitter<any> = new EventEmitter<any>();

  signupButtonClicked(){
    let person: Person = {
      name: this.personalDetailsGroup.controls.firstName.value ? this.personalDetailsGroup.controls.firstName.value : "undefined",
      username: this.accountDetailsGroup.controls.username.value ? this.accountDetailsGroup.controls.username.value : "undefined",
      surname: this.personalDetailsGroup.controls.lastName.value ? this.personalDetailsGroup.controls.lastName.value : "undefined",
      password: this.accountDetailsGroup.controls.password.value ? this.accountDetailsGroup.controls.password.value : "undefined",
      email: this.accountDetailsGroup.controls.email.value ? this.accountDetailsGroup.controls.email.value : "undefined"
    };

    let user: User = {
      about: this.profileDetailsGroup.controls.about.value != null ? this.profileDetailsGroup.controls.about.value : undefined,
      status: 1,
      person: person
    };

    this.submitEvent.emit({user: user, profile_pic: this.uploadedFile});
  }

  fileUploaded(file: any){
    this.uploadedFile = file;
  }

  filledAccountDetails(stepper: MatStepper){
    this.accountDetailsGroup.markAllAsTouched();
    if(this.accountDetailsGroup.valid && this.accountDetailsGroup.controls.username.value){
      this.userService.userExists(this.accountDetailsGroup.controls.username.value).subscribe(exists => {
        if(exists){
          this.snackbar.open("User already exists!", "Dismiss", {duration: 3000});
          return;
        }
        this.accountDetailsStepCompleted = true;
        stepper.next();
      });
    }
  }

  stepperBack(stepper: MatStepper){
    stepper.previous();
  }

  filledPersonalDetails(stepper: MatStepper){
    this.personalDetailsGroup.markAllAsTouched();
    console.log(this.personalDetailsGroup.valid);
    if(this.personalDetailsGroup.valid){
      this.personalDetailsStepCompleted = true;
      stepper.next();
    }
  }
}
