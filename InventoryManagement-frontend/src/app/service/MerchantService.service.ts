import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class MerchantService {
  constructor(private http: HttpClient) {}

  baseurl: string = 'http://localhost:6363/';


  getAllMerchants(
    offset: number,
    size: number,
    sortField: string,
    sortOrder: number,
    searchText: string
  ): Observable<any> {
    var url =
      this.baseurl +
      'merchant?offset=' +
      offset +
      '&size=' +
      size +
      '&sortField=' +
      sortField +
      '&sortOrder=' +
      sortOrder +
      '&searchText=' +
      searchText;
    return this.http.get<any[]>(url);
  }

  getMerchantById(id: String): Observable<any> {
    var url = this.baseurl + 'merchant/getById/' + id;
    return this.http.get<any>(url);
  }

  addMerchant(merchant: any): Observable<any> {
    var url = this.baseurl + 'merchant/addMerchant';
    return this.http.post<any>(url, merchant);
  }

  updateMerchant(merchant: any): Observable<any> {
    var url = this.baseurl + 'merchant/updateMerchant';
    return this.http.put<any>(url, merchant);
  }

  deleteMerchant(id: string): Observable<any> {
    var url = this.baseurl + 'merchant/deleteMerchant?merchantId=' + id;
    return this.http.delete<any>(url);
  }
}
