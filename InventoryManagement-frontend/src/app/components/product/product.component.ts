import { Component, OnInit } from '@angular/core';
import { Route, Router, ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/service/ProductService.service';
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  data: any[] = [];
  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSource = this.data;
  id: number = 0;
  constructor(private router: Router, private activeRoute: ActivatedRoute,
    private productService: ProductService) {}

  ngOnInit(): void {
    //this.id = this.activeRoute.snapshot.params['id'];
  }

  getAllProducts() {
    this.productService.getAllProducts().subscribe((res) => {
      this.data = res;
    });
  }

  onAddUpdateButtonClick() {
    //this.router.navigateByUrl("/add-update-product");
    //this.router.navigate(['add-update-product']);
  }
}
