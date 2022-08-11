import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserService } from 'src/app/service/UserService.service';

import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-add-update-user',
  templateUrl: './add-update-user.component.html',
  styleUrls: ['./add-update-user.component.css'],
})
export class AddUpdateUserComponent implements OnInit {
  userForm = new FormGroup({
    firstName: new FormControl<string | null>(''),
    lastName: new FormControl<string | null>(''),
    email: new FormControl<string | null>(''),
    city: new FormControl<string | null>(''),
    country: new FormControl<string | null>(''),
  });

  id: string = "";

  constructor(
    private userservice: UserService,
    private _snackBar: MatSnackBar,
    private _activeRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = this._activeRoute.snapshot.params['id'];
    if (this.id != '') {
      this.getUserById(this.id);
    }
  }

  getUserById(id: string) {
    this.userservice.getUserById(id).subscribe(
      (res) => {
        console.log(res);
        if (res.status && res.statusCode == '200') {
          this.userForm.controls.firstName.setValue(res.data.firstName);
          this.userForm.controls.lastName.setValue(res.data.lastName);
          this.userForm.controls.email.setValue(res.data.email);
          this.userForm.controls.city.setValue(res.data.city);
          this.userForm.controls.country.setValue(res.data.country);
        }
        if (
          !res.status &&
          res.statusCode == '500' &&
          res.errorCode == 'E5006'
        ) {
          this._snackBar.open(res.message , "OK");
        }
      },
      (err) => {
        console.log(err);
        this._snackBar.open('Something went wrong. Please try again.' , "OK");
      }
    );
  }

  onSubmit() {
    var adminUser = {
      id: this.id,
      firstName: this.userForm.value.firstName,
      lastName: this.userForm.value.lastName,
      email: this.userForm.value.email,
      city: this.userForm.value.city,
      country: this.userForm.value.country,
    };
    this.userservice.updateUser(adminUser).subscribe(
      (res) => {
        console.log(res);
        if (res.status && res.statusCode == '200') {
          this._snackBar.open('Admin User updated successfully.' , "OK");
        }
        if (
          !res.status &&
          res.statusCode == '500' &&
          res.errorCode == 'E5005'
        ) {
          this._snackBar.open(res.message);
        }
      },
      (err) => {
        console.log(err);
        this._snackBar.open('Something went wrong. Please try again.' , "OK");
      }
    );
  }
}
