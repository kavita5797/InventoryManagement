import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class ProductCategoryService {
  constructor(private http: HttpClient) {}

  baseurl: string = 'http://localhost:6363/';

  getAllProductCategory(): Observable<any> {
    var url = this.baseurl + 'category';
    return this.http.get<any[]>(url);
  }

  addProductCategory(productCategory: any): Observable<any> {
    var url = this.baseurl + 'category/createCategory';
    return this.http.post<any>(url, productCategory);
  }

  deleteProductCategory(id: string): Observable<any> {
    var url =
      this.baseurl +
      'category/deleteCategory?productCategoryId=' +
      id;
    return this.http.delete<any>(url);
  }
}
