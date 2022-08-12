import { Component, OnInit } from '@angular/core';
import { Route, Router, ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PaymentService } from 'src/app/service/payment.service';
@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  
  data: any[] = [];
  displayedColumns: string[] =
   [
  'stockId', 
  'paidAmount', 
  'totalAmount',
  'paymentDate',
  'paymentType',];

  constructor(private _router: Router, private activeRoute: ActivatedRoute,
    private paymentService: PaymentService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.paymentService.getAllPayments().subscribe(
      (res) => {
        console.log(res.data);
      this.data = res.data;

    },
    (err) => {
      console.log(err);
      this._snackBar.open('Something went wrong. Please try again.', 'OK');
    }
    );
  }

}
