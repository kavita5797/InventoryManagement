import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {
  constructor(private http: HttpClient) {}

  baseurl: string = 'http://localhost:6363/';

  login(loginObj: any): Observable<any> {
    var url = this.baseurl + 'user/login';
    return this.http.post<any[]>(url, loginObj);
  }

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

  getUserById(id: String): Observable<any> {
    var url = this.baseurl + 'user/getById/' + id;
    return this.http.get<any>(url);
  }

  signup(adminUser: any): Observable<any> {
    var url = this.baseurl + 'user/signup';
    return this.http.post<any>(url, adminUser);
  }

  updateUser(adminUser: any): Observable<any> {
    var url = this.baseurl + 'user/updateUser';
    return this.http.post<any>(url, adminUser);
  }

  deleteUser(id: string): Observable<any> {
    var url = this.baseurl + 'user/deleteUser?userId=' + id;
    return this.http.delete<any>(url);
  }
}
