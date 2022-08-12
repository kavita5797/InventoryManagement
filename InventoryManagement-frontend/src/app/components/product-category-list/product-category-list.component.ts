import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ProductCategoryService } from 'src/app/service/ProductCategoryService.service';

@Component({
  selector: 'app-product-category-list',
  templateUrl: './product-category-list.component.html',
  styleUrls: ['./product-category-list.component.css'],
})
export class ProductCategoryListComponent implements OnInit {
  productCategoryForm = new FormGroup({
    category: new FormControl<string | null>(''),
  });

  data: any[] = [];
  displayedColumns: string[] = ['category', 'actions'];
  constructor(
    private productCategoryservice: ProductCategoryService,
    private _router: Router,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.getAllProductCategory();
  }

  getAllProductCategory() {
    this.productCategoryservice.getAllProductCategory().subscribe(
      (res) => {
        console.log(res);
        this.data = res.data;
      },
      (err) => {
        console.log(err);
        this._snackBar.open('Something went wrong. Please try again.', 'OK');
      }
    );
  }

  deleteProductCategory(id: string) {
    this.productCategoryservice.deleteProductCategory(id).subscribe(
      (res) => {
        if (res.status && res.statusCode == '200') {
          this._snackBar.open('Product Category deleted successfully.', 'OK');
          this.getAllProductCategory();
        }
        if (
          !res.status &&
          res.statusCode == '500' &&
          res.errorCode == 'E5005'
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
    var category = {
      category: this.productCategoryForm.value.category,
    };
    this.productCategoryservice.addProductCategory(category).subscribe(
      (res) => {
        console.log(res);
        if (res.status && res.statusCode == '200') {
          this._snackBar.open('Product Category Added successfully.', 'OK');
          this.getAllProductCategory();
          this.productCategoryForm.reset();
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
