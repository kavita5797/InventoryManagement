import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MerchantService } from 'src/app/service/MerchantService.service';
import { ProductStockService } from 'src/app/service/product-stock.service';
import { ProductCategoryService } from 'src/app/service/ProductCategoryService.service';
import { ProductService } from 'src/app/service/ProductService.service';
import { UserService } from 'src/app/service/UserService.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  userCount: number = 0;
  merchantCount: number = 0;
  productCount: number = 0;
  productCategoryCount: number = 0;
  outOfStockProductCount: number = 0;
  pendingPaymentCount: number = 0;

  constructor(
    private userService: UserService,
    private merchantService: MerchantService,
    private productService: ProductService,
    private productCategoryService: ProductCategoryService,
    private receivingStockService : ProductStockService,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.getAllUserCount();
    this.getAllMerchantCount();
    this.getAllProductCount();
    this.getAllProductCategoryCount();
    this.getTotalPendingPayments();
    this.getOutOfStockProducts();
  }

  getAllUserCount() {
    this.userService.getUserCount().subscribe(
      (res) => {
        console.log(res);
        this.userCount = res.data;
      },
      (err) => {
        console.log(err);
        this._snackBar.open('Something went wrong. Please try again.', 'OK');
      }
    );
  }

  getAllMerchantCount() {
    this.merchantService.getMerchantCount().subscribe(
      (res) => {
        console.log(res);
        this.merchantCount = res.data;
      },
      (err) => {
        console.log(err);
        this._snackBar.open('Something went wrong. Please try again.', 'OK');
      }
    );
  }

  getAllProductCount() {
    this.productService.getProductCount().subscribe(
      (res) => {
        console.log(res);
        this.productCount = res.data;
      },
      (err) => {
        console.log(err);
        this._snackBar.open('Something went wrong. Please try again.', 'OK');
      }
    );
  }

  getAllProductCategoryCount() {
    this.productCategoryService.getProductCategoryCount().subscribe(
      (res) => {
        console.log(res);
        this.productCategoryCount = res.data;
      },
      (err) => {
        console.log(err);
        this._snackBar.open('Something went wrong. Please try again.', 'OK');
      }
    );
  }

  getOutOfStockProducts() {
    this.productService.getOutOfStockProductCount().subscribe(
      (res) => {
        console.log(res);
        this.outOfStockProductCount = res.data;
      },
      (err) => {
        console.log(err);
        this._snackBar.open('Something went wrong. Please try again.', 'OK');
      }
    );
  }

  getTotalPendingPayments() {
    this.receivingStockService.getPendingPaymentCount().subscribe(
      (res) => {
        console.log(res);
        this.pendingPaymentCount = res.data;
      },
      (err) => {
        console.log(err);
        this._snackBar.open('Something went wrong. Please try again.', 'OK');
      }
    );
  }
}
