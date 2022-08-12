import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) { }

  
  baseurl : string = 'http://localhost:6363/';


  getAllPayments():Observable<any> {
    var url = this.baseurl + 'payment';
    return this.http.get<any[]>(url);
  }

}
