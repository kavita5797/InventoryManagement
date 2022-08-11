import { DOCUMENT } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Route, Router } from '@angular/router';
import { UserService } from 'src/app/service/UserService.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
})
export class UserListComponent implements OnInit {
  offset: number = 0;
  size: number = 9;
  sortField: string = 'email';
  sortOrder: number = 1;
  searchText: string = '';
  maxpage: number = 1;
  totalRecords: number = 0;

  data: any[] = [];
  displayedColumns: string[] = [
    'firstName',
    'lastName',
    'email',
    'city',
    'country',
    'actions',
  ];
  constructor(
    private userservice: UserService,
    private _router: Router,
    private _snackBar: MatSnackBar,
    @Inject(DOCUMENT) document: Document
  ) {}

  ngOnInit(): void {
    this.getAllUsers();
  }

  searchUser() {
    this.searchText = (<HTMLInputElement>(
      document.getElementById('searchText')
    )).value;
    this.getAllUsers();
  }

  nextPage() {
    this.offset = this.offset + 1;
    this.getAllUsers();
  }

  previousPage() {
    if (this.offset > 0) {
      this.offset = this.offset - 1;
    }
    this.getAllUsers();
  }

  getAllUsers() {
    this.userservice
      .getAllUsers(
        this.offset,
        this.size,
        this.sortField,
        this.sortOrder,
        this.searchText
      )
      .subscribe(
        (res) => {
          console.log(res);
          this.data = res.data.result;
          this.totalRecords = res.data.totalRecords;
          this.maxpage = Math.ceil(this.totalRecords / this.size);
        },
        (err) => {
          console.log(err);
          this._snackBar.open('Something went wrong. Please try again.', "OK");
        }
      );
  }

  onAddUpdateButtonClick() {
    this._router.navigateByUrl('/add-update-user');
  }

  deleteUser(id: string) {
    this.userservice.deleteUser(id).subscribe(
      (res) => {
        if (res.status && res.statusCode == '200') {
          this._snackBar.open('Admin User deleted successfully.', "OK");
        }
        if (
          !res.status &&
          res.statusCode == '500' &&
          res.errorCode == 'E5005'
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

  updateUser(id: string) {
    this._router.navigateByUrl('/add-update-user/' + id);
  }
}
