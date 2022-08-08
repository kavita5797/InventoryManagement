import { DOCUMENT } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
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
  ];
  constructor(
    private userservice: UserService,
    private _router: Router,
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
        }
      );
  }

  onAddUpdateButtonClick() {
    this._router.navigateByUrl('/add-update-user');
  }
}
