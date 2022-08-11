import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/UserService.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  signupForm = new FormGroup({
    firstName: new FormControl<string | null>(''),
    lastName: new FormControl<string | null>(''),
    email: new FormControl<string | null>(''),
    city: new FormControl<string | null>(''),
    country: new FormControl<string | null>(''),
    password: new FormControl<string | null>(''),
  });
  constructor(
    private userservice: UserService,
    private _snackBar: MatSnackBar,
    private _router: Router
  ) {}

  ngOnInit(): void {}

  onSubmit() {
    var adminUser = {
      firstName: this.signupForm.value.firstName,
      lastName: this.signupForm.value.lastName,
      email: this.signupForm.value.email,
      city: this.signupForm.value.city,
      country: this.signupForm.value.country,
      password: this.signupForm.value.password,
    };
    this.userservice.signup(adminUser).subscribe(
      (res) => {
        console.log(res);
        if (res.status && res.statusCode == '200') {
          this._snackBar.open('Admin User added successfully.', "OK");
          this._router.navigateByUrl("/login");
        }
        if (
          !res.status &&
          res.statusCode == '500' &&
          res.errorCode == 'E5006'
        ) {
          this._snackBar.open(res.message, "OK");
        }
      },
      (err) => {
        console.log(err);
        this._snackBar.open('Something went wrong. Please try again.', "OK");
      }
    );
  }
}
