import { Component, OnInit } from '@angular/core';
import { Route, Router, ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/service/ProductService.service';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {

  data: any[] = [];
  displayedColumns: string[] =
   [
  'productlabel', 
  'productname', 
  'productcategory',
  'productquality',
  'productprice',
  'productdescription',
  'actions'];

  constructor(private _router: Router, private activeRoute: ActivatedRoute,
    private productService: ProductService, private _snackBar: MatSnackBar) {}

  ngOnInit(): void {
    //this.id = this.activeRoute.snapshot.params['id'];
    this.getAllProducts();

  }

  getAllProducts() {
    this.productService.getAllProducts().subscribe(
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

  onAddUpdateButtonClick() {
    this._router.navigateByUrl("/add-update-product");
    //this.router.navigate(['add-update-product']);
  }

  deleteProduct(id:string){
    //deletetodo
    this.productService.deleteProduct(id).subscribe(
      (res) => {
        if (res.status && res.statusCode == '200') {
          this._snackBar.open('Admin Merchant deleted successfully.', "OK");
          this.getAllProducts();
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

  updateProduct(id:string){
    this._router.navigateByUrl('/add-update-product/' + id);
  }



  // onSubmit(){

  //   var product = {
  //     productcategory:this.productForm.value.category,
  //     productprice:this.productForm.value.price,
  //     productdescription:this.productForm.value.description,
  //     productlabel:this.productForm.value.label,
  //     productname:this.productForm.value.name,
  //     productquality:this.productForm.value.quality
  //   };
  //   console.log(product);
  //   this.productService.addProduct(product).subscribe(
  //     (res)=>{
  //       console.log(res);
  //       if (res.status && res.statusCode == '200') {
  //         this._snackBar.open('Product Added successfully.', 'OK');
  //         this.getAllProducts();
  //         this.productForm.reset();
  //       }
  //       if (
  //         res.status &&
  //         res.statusCode == '500' &&
  //         res.errorCode == 'E5005'
  //       ) {
  //         this._snackBar.open(res.message);
  //       }
  //     },
  //     (err) => {
  //       console.log(err);
  //       this._snackBar.open('Something went wrong. Please try again.', 'OK');
  //     } 
  //   )

  // }
}
