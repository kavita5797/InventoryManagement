import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentService } from 'src/app/service/payment.service';

@Component({
  selector: 'app-add-payment',
  templateUrl: './add-payment.component.html',
  styleUrls: ['./add-payment.component.css'],
})
export class AddPaymentComponent implements OnInit {
  stockid: string = '';
  paymentForm = new FormGroup({
    billingtype: new FormControl<string>(''),
    paidamount: new FormControl<number>(0),
  });

  constructor(
    private paymentService: PaymentService,
    private _snackBar: MatSnackBar,
    private _router: Router,
    private _activeRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.stockid = this._activeRoute.snapshot.params['id'];
  }

  onSubmit() {
    this.paymentService
      .payBill(
        this.stockid,
        this.paymentForm.value.billingtype!,
        this.paymentForm.value.paidamount!
      )
      .subscribe(
        (res) => {
          console.log(res);
          if (res.status && res.statusCode == '200') {
            this._snackBar.open('Paymenet has been done.', 'OK');
            this._router.navigateByUrl('/payment');
          }
          if (!res.status && res.statusCode == '500') {
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
