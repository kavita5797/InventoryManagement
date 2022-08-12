import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductStockService } from 'src/app/service/product-stock.service';
@Component({
  selector: 'app-add-update-product-receiving-stock',
  templateUrl: './add-update-product-receiving-stock.component.html',
  styleUrls: ['./add-update-product-receiving-stock.component.css']
})
export class AddUpdateProductReceivingStockComponent implements OnInit {

  ProductStockForm = new FormGroup({
    billingtype:new FormControl<string | null>(''),
    lastpaiddate:new FormControl<Date|null>(new Date()),
    paidamount:new FormControl<number|null>(0),
    productid: new FormControl<string | null>(''),
    productname: new FormControl<string | null>(''),
    merchantid: new FormControl<string | null>(''),
    merchantname: new FormControl<string | null>(''),
    quantity: new FormControl<number | null>(0),
    unitprice: new FormControl<number | null>(0),
    totalprice: new FormControl<number | null>(0),
    totalpaidamount: new FormControl<number | null>(0),
    purchaseddate: new FormControl<Date | null>(new Date()),
    pendingpayment: new FormControl<number | null>(0),

})
  
  constructor(
    private _router: Router, 
    private _activeRoute: ActivatedRoute,
    private _snackBar: MatSnackBar,
    private productstockservice:ProductStockService
  ) { }

  ngOnInit(): void {

  }

  onSubmit(){

    var productreceivingstock = {

      // billing_type:this.ProductStockForm.value.billingtype, 
      // last_paid_date:this.ProductStockForm.value.lastpaiddate,
      // merchant_id:this.ProductStockForm.value.merchantid, 
      // paid_amount:this.ProductStockForm.value.paidamount, 
      // product_id:this.ProductStockForm.value.productid,
      // purchased_date:this.ProductStockForm.value.purchaseddate,
      // quantity:this.ProductStockForm.value.quantity,
      // total_price:this.ProductStockForm.value.totalprice,
      // unit_price:this.ProductStockForm.value.unitprice,
      // merchant_name:this.ProductStockForm.value.merchantname, 
      // pending_payment:this.ProductStockForm.value.pendingpayment,
      // product_name:this.ProductStockForm.value.productname,
      // total_paid_amount:this.ProductStockForm.value.totalpaidamount


      //billing_type:this.ProductStockForm.value.billingtype, 
      //last_paid_date:this.ProductStockForm.value.lastpaiddate,
      merchantId:this.ProductStockForm.value.merchantid, 
      //paid_amount:this.ProductStockForm.value.paidamount, 
      productId:this.ProductStockForm.value.productid,
      purchasedDate:this.ProductStockForm.value.purchaseddate,
      quantity:this.ProductStockForm.value.quantity,
      totalPrice:this.ProductStockForm.value.totalprice,
      unitPrice:this.ProductStockForm.value.unitprice,
      merchantName:this.ProductStockForm.value.merchantname, 
      pendingPayment:this.ProductStockForm.value.pendingpayment,
      productName:this.ProductStockForm.value.productname,
      totalPaidAmount:this.ProductStockForm.value.totalpaidamount

    }
    console.log(productreceivingstock);
    this.productstockservice.addProductReceivingStock(productreceivingstock).subscribe(
      (res)=>{
        console.log(res);
        if (res.status && res.statusCode == '200') {
          this._snackBar.open('Product Receiving Stock has been Added.', 'OK');
          //this.getAllProducts();
          this._router.navigateByUrl("/product-stock");
          this.ProductStockForm.reset();
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
        this._snackBar.open('Something went wrong. Please try again.', 'OK');
      } 
    )

  }

}
