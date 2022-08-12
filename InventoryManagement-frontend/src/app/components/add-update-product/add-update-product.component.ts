import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/service/ProductService.service';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-update-product',
  templateUrl: './add-update-product.component.html',
  styleUrls: ['./add-update-product.component.css']
})
export class AddUpdateProductComponent implements OnInit {
  productForm = new FormGroup({
    description: new FormControl<string | null>(''),
    name: new FormControl<string | null>(''),
    label: new FormControl<string | null>(''),
    quality: new FormControl<number | null>(0),
    category: new FormControl<string | null>(''),
    price: new FormControl<number | null>(0),
    

  });

  id:string = '';
  constructor(private _router: Router, private _activeRoute: ActivatedRoute,
    private productService: ProductService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.id = this._activeRoute.snapshot.params['id'];
    if (this.id != '') {
      console.log(this.id);
      this.getProductById(this.id);
    }
  }

  getProductById(id:string){

    this.productService.getProductById(id).subscribe(
      (res) => {
        console.log(res);
        if (res.status && res.statusCode == '200') {
          this.productForm.controls.name.setValue(res.data.name);
          this.productForm.controls.description.setValue(res.data.description);
          this.productForm.controls.label.setValue(res.data.label);
          this.productForm.controls.category.setValue(res.data.category);
          this.productForm.controls.price.setValue(res.data.price);
          this.productForm.controls.quality.setValue(res.data.quality);
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

  onSubmit(){
    if (this.id !== undefined) {
      this.updateProduct();
    } else {
      this.addProduct();
    }
  }

  updateProduct(){
    var product = {
      id:this.id,
      productcategory:this.productForm.value.category,
      productprice:this.productForm.value.price,
      productdescription:this.productForm.value.description,
      productlabel:this.productForm.value.label,
      productname:this.productForm.value.name,
      productquality:this.productForm.value.quality

    };
    console.log(product);
    this.productService.updateProduct(product).subscribe(
      (res)=>{
        console.log(res);
        if (res.status && res.statusCode == '200') {
          this._snackBar.open('Product updated successfully.', 'OK');
          //this.getAllProducts();
          this._router.navigateByUrl("/product-list");
          this.productForm.reset();
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
  
  addProduct(){
    var product = {
      productcategory:this.productForm.value.category,
      productprice:this.productForm.value.price,
      productdescription:this.productForm.value.description,
      productlabel:this.productForm.value.label,
      productname:this.productForm.value.name,
      productquality:this.productForm.value.quality

    };

    console.log(product);
    this.productService.addProduct(product).subscribe(
      (res)=>{
        console.log(res);
        if (res.status && res.statusCode == '200') {
          this._snackBar.open('Product Added successfully.', 'OK');
          //this.getAllProducts();
          this._router.navigateByUrl("/product");
          this.productForm.reset();
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
