import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { MerchantService } from 'src/app/service/MerchantService.service';
import { ProductStockService } from 'src/app/service/product-stock.service';
import { ProductService } from 'src/app/service/ProductService.service';
@Component({
  selector: 'app-add-update-product-receiving-stock',
  templateUrl: './add-update-product-receiving-stock.component.html',
  styleUrls: ['./add-update-product-receiving-stock.component.css'],
})
export class AddUpdateProductReceivingStockComponent implements OnInit {
  productsData: any[] = [];
  merchantData: any[] = [];

  ProductStockForm = new FormGroup({
    productid: new FormControl<string | null>(''),
    merchantid: new FormControl<string | null>(''),
    quantity: new FormControl<number >(0),
    unitprice: new FormControl<number >(0),
    purchaseddate: new FormControl<Date | null>(new Date()),
  });

  constructor(
    private _router: Router,
    private _activeRoute: ActivatedRoute,
    private _snackBar: MatSnackBar,
    private productstockservice: ProductStockService,
    private merchantService: MerchantService,
    private productService: ProductService
  ) {}

  ngOnInit(): void {
    this.getAllMerchants();
    this.getAllProducts();
  }

  getAllMerchants() {
    this.merchantService.getAll().subscribe(
      (res) => {
        console.log(res);
        this.merchantData = res.data;
        console.log(this.merchantData);
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

  getAllProducts() {
    this.productService.getAllProducts().subscribe(
      (res) => {
        console.log(res);
        this.productsData = res.data;
        console.log(this.productsData);
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

  onMerchantChange(event: any) {
    //this.ProductStockForm.controls.merchantid.setValue(event.target.value);
  }

  onProductChange(event: any) {
    //this.ProductStockForm.controls.merchantid.setValue(event.target.value);
  }

  onSubmit() {
    var productreceivingstock = {
      merchantId: this.ProductStockForm.value.merchantid,
      productId: this.ProductStockForm.value.productid,
      purchasedDate: this.ProductStockForm.value.purchaseddate,
      quantity: this.ProductStockForm.value.quantity,
      totalPrice: this.ProductStockForm.value.quantity! * this.ProductStockForm.value.unitprice!,
      unitPrice: this.ProductStockForm.value.unitprice,
    };
    console.log(productreceivingstock);
    this.productstockservice
      .addProductReceivingStock(productreceivingstock)
      .subscribe(
        (res) => {
          console.log(res);
          if (res.status && res.statusCode == '200') {
            this._snackBar.open(
              'Product Receiving Stock has been Added.',
              'OK'
            );
            this._router.navigateByUrl('/product-stock');
            this.ProductStockForm.reset();
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
