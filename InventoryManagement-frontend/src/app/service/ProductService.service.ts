import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class ProductService {
  constructor(private http: HttpClient) {}

  baseurl : string = 'http://localhost:6363/';

  getAllProducts(): Observable<any> {
    var url = this.baseurl + 'product';
    return this.http.get<any[]>(url);
  }

  getProductById(id:string): Observable<any> {
    var url = this.baseurl + 'product/getProductById/' + id;
    return this.http.get<any>(url);
  }


  addProduct(product:any): Observable<any> {
    var url = this.baseurl + 'product/createProduct';
    return this.http.post<any>(url, product);
  }

  updateProduct(product:any):Observable<any> {
    var url = this.baseurl + 'product/updateProduct';
    return this.http.put<any>(url, product);
  }

  deleteProduct(id:string): Observable<any> {
    var url =
      this.baseurl +
      'product/deleteProduct?productId=' +
      id;
    return this.http.delete<any>(url);
  }

  getProductCount(): Observable<any> {
    var url = this.baseurl + 'product/count';
    return this.http.get<any>(url);
  }

  getOutOfStockProductCount(): Observable<any> {
    var url = this.baseurl + 'product/outofstock';
    return this.http.get<any>(url);
  }
}
