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
}
