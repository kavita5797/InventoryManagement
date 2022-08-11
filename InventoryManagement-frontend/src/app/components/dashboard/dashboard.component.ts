import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  userCount : number= 0;
  productCount : number = 0;
  productCategoryCount : number = 0;
  outOfStockProductCount : number = 0;
  pendingPaymentCount : number = 0;

  constructor() { }

  ngOnInit(): void {
  }

  getAllUserCount(){

  }

  getAllProductCount(){

  }

  getOutOfStockProduct(){

  }

  getPendingPayments(){

  }

}
