import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserService } from 'src/app/service/UserService.service';

import { MatSnackBar } from '@angular/material/snack-bar';
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
    password: new FormControl<string | null>(''),
  });

  constructor(private userservice: UserService,
    private _snackBar: MatSnackBar,) {}

  ngOnInit(): void {}

  onSubmit() {
    var adminUser = {
      firstName: this.userForm.value.firstName,
      lastName: this.userForm.value.lastName,
      email: this.userForm.value.email,
      city: this.userForm.value.city,
      country: this.userForm.value.country,
      password: this.userForm.value.password,
    };
    this.userservice.addAdmin(adminUser).subscribe(
      (res) => {
        console.log(res);
        if(res.status && res.statusCode == "200"){
          this._snackBar.open('Admin User added successfully.');
        }
        if(!res.status && res.statusCode == "500" && res.errorCode == "E5006"){
          this._snackBar.open(res.message);
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
