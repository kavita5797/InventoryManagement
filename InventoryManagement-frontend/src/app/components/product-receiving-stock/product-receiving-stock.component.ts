import { Component, OnInit } from '@angular/core';
import { Route, Router, ActivatedRoute } from '@angular/router';
import { ProductStockService } from 'src/app/service/product-stock.service';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-product-receiving-stock',
  templateUrl: './product-receiving-stock.component.html',
  styleUrls: ['./product-receiving-stock.component.css']
})


export class ProductReceivingStockComponent implements OnInit {
  data: any[] = [];
  displayedColumns: string[] =
   [
  'merchantId', 
  'merchantName',
  'quantity', 
  'productId',  
  'productName', 
  'purchasedDate',
  // 'billing_type',
  // 'paid_amount',
  // 'last_paid_date',
  'totalPrice',
  'unitPrice',
  'pendingPayment',
  'totalPaidAmount'];
  constructor(private _router: Router, private activeRoute: ActivatedRoute,
    private productStockService:ProductStockService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.productStockService.getAllProductRecievingStocks().subscribe(
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

  onAddButtonClick() {
    this._router.navigateByUrl("/add-update-product-receiving-stock");
  }






}
