import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {
  constructor(private http: HttpClient) {}

  baseurl: string = 'http://localhost:6363/';

  getAllUsers(
    offset: number,
    size: number,
    sortField: string,
    sortOrder: number,
    searchText: string
  ): Observable<any> {
    var url =
      this.baseurl +
      'user?offset=' +
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

  addAdmin(adminUser: any): Observable<any> {
    var url = this.baseurl + 'user/signup';
    return this.http.post<any>(url, adminUser);
  }
}
