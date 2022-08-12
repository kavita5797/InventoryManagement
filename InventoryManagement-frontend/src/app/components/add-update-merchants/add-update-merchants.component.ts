import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { MerchantService } from 'src/app/service/MerchantService.service';

@Component({
  selector: 'app-add-update-merchants',
  templateUrl: './add-update-merchants.component.html',
  styleUrls: ['./add-update-merchants.component.css'],
})
export class AddUpdateMerchantsComponent implements OnInit {
  merchantForm = new FormGroup({
    name: new FormControl<string | null>(''),
    phone: new FormControl<string | null>(''),
    email: new FormControl<string | null>(''),
    address: new FormControl<string | null>(''),
    country: new FormControl<string | null>(''),
  });

  id: string = '';

  constructor(
    private merchantservice: MerchantService,
    private _snackBar: MatSnackBar,
    private _activeRoute: ActivatedRoute,
    private _router : Router
  ) {}

  ngOnInit(): void {
    this.id = this._activeRoute.snapshot.params['id'];
    if (this.id != '') {
      this.getMerchantById(this.id);
    }
  }

  getMerchantById(id: string) {
    this.merchantservice.getMerchantById(id).subscribe(
      (res) => {
        console.log(res);
        if (res.status && res.statusCode == '200') {
          this.merchantForm.controls.name.setValue(res.data.name);
          this.merchantForm.controls.phone.setValue(res.data.phone);
          this.merchantForm.controls.email.setValue(res.data.email);
          this.merchantForm.controls.address.setValue(res.data.address);
          this.merchantForm.controls.country.setValue(res.data.country);
        }
        if (
          !res.status &&
          res.statusCode == '500' &&
          res.errorCode == 'E5006'
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

  onSubmit() {
    if (this.id !== undefined) {
      this.updateMerchant();
    } else {
      this.addMerchant();
    }
  }

  addMerchant() {
    var merchant = {
      name: this.merchantForm.value.name,
      phone: this.merchantForm.value.phone,
      email: this.merchantForm.value.email,
      address: this.merchantForm.value.address,
      country: this.merchantForm.value.country,
    };
    this.merchantservice.addMerchant(merchant).subscribe(
      (res) => {
        console.log(res);
        if (res.status && res.statusCode == '200') {
          this._snackBar.open('Admin Merchant added successfully.', 'OK');
          this._router.navigateByUrl("/merchant-list");
        }
        if (
          !res.status &&
          res.statusCode == '500' &&
          res.errorCode == 'E5005'
        ) {
        this._snackBar.open(res.message , 'OK');
        }
      },
      (err) => {
        console.log(err);
        this._snackBar.open('Something went wrong. Please try again.', 'OK');
      }
    );
  }

  updateMerchant() {
    var merchant = {
      id: this.id,
      name: this.merchantForm.value.name,
      phone: this.merchantForm.value.phone,
      email: this.merchantForm.value.email,
      address: this.merchantForm.value.address,
      country: this.merchantForm.value.country,
    };
    this.merchantservice.updateMerchant(merchant).subscribe(
      (res) => {
        console.log(res);
        if (res.status && res.statusCode == '200') {
          this._snackBar.open('Admin Merchant updated successfully.', 'OK');
          this._router.navigateByUrl("/merchant-list");
        }
        if (
          !res.status &&
          res.statusCode == '500' &&
          res.errorCode == 'E5005'
        ) {
        this._snackBar.open(res.message , 'OK');
        }
      },
      (err) => {
        console.log(err);
        this._snackBar.open('Something went wrong. Please try again.', 'OK');
      }
    );
  }
}
