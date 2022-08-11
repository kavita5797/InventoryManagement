import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/UserService.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm = new FormGroup({
    email: new FormControl<string | null>(''),
    password: new FormControl<string | null>(''),
  });
  constructor(
    private userservice: UserService,
    private _snackBar: MatSnackBar,
    private _router: Router
  ) {}

  ngOnInit(): void {}

  onSubmit() {
    var loginObj = {
      email: this.loginForm.value.email,
      password: this.loginForm.value.password,
    };
    this.userservice.login(loginObj).subscribe(
      (res) => {
        console.log(res);
        if (res.status && res.statusCode == '200') {
          this.navigateToDashboard();
        }
        if (
          !res.status &&
          res.statusCode == '500' &&
          (res.errorCode == 'E5005' || res.errorCode == 'E5007')
        ) {
          this._snackBar.open(res.message, 'OK');
        }
      },
      (err) => {
        console.log(err);
        this._snackBar.open('Something went wrong. Please try again.', 'OK');
      }
    );
  }

  navigateToDashboard() {
    this._router.navigateByUrl('/dashboard');
  }

  navigateToSignUp() {
    this._router.navigateByUrl('/signup');
  }
}
