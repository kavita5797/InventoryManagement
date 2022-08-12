import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductStockService {

  constructor(private http: HttpClient) { }

  baseurl : string = 'http://localhost:6363/';

  getAllProductRecievingStocks():Observable<any> {
    var url = this.baseurl + 'receivingStock';
    return this.http.get<any[]>(url);
  }

  addProductReceivingStock(productstock:any): Observable<any> {
    var url = this.baseurl + 'receivingStock';
    return this.http.post<any>(url, productstock);
  }
}
