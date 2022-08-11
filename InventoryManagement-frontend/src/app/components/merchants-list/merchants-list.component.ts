import { DOCUMENT } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { MerchantService } from 'src/app/service/MerchantService.service';

@Component({
  selector: 'app-merchants-list',
  templateUrl: './merchants-list.component.html',
  styleUrls: ['./merchants-list.component.css']
})
export class MerchantsListComponent implements OnInit {

  offset: number = 0;
  size: number = 9;
  sortField: string = 'email';
  sortOrder: number = 1;
  searchText: string = '';
  maxpage: number = 1;
  totalRecords: number = 0;

  data: any[] = [];
  displayedColumns: string[] = [
    'name',
    'email',
    'phone',
    'address',
    'country',
    'actions',
  ];
  constructor(
    private merchantservice: MerchantService,
    private _router: Router,
    private _snackBar: MatSnackBar,
    @Inject(DOCUMENT) document: Document
  ) {}

  ngOnInit(): void {
    this.getAllMerchants();
  }

  searchMerchant() {
    this.searchText = (<HTMLInputElement>(
      document.getElementById('searchText')
    )).value;
    this.getAllMerchants();
  }

  nextPage() {
    this.offset = this.offset + 1;
    this.getAllMerchants();
  }

  previousPage() {
    if (this.offset > 0) {
      this.offset = this.offset - 1;
    }
    this.getAllMerchants();
  }

  getAllMerchants() {
    this.merchantservice
      .getAllMerchants(
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
    this._router.navigateByUrl('/add-update-merchant');
  }

  deleteMerchant(id: string) {
    this.merchantservice.deleteMerchant(id).subscribe(
      (res) => {
        if (res.status && res.statusCode == '200') {
          this._snackBar.open('Admin Merchant deleted successfully.', "OK");
          this.getAllMerchants();
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

  updateMerchant(id: string) {
    this._router.navigateByUrl('/add-update-merchant/' + id);
  }
}
