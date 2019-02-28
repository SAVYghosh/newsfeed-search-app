import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../Models/User';

@Injectable({
  providedIn: 'root'
})
export class GetUserService {

  constructor(private http: HttpClient) { }

  getAllUser() {
    return this.http.get<Array<User>>("http://localhost:8085/admin/getAllUser");
  }

  blockUser(userEmail: string) {
    return this.http.get("http://localhost:8085/admin/blockUser/" + userEmail, { responseType: 'text' });
  }
  unblockUser(userEmail: string) {
    return this.http.get("http://localhost:8085/admin/unblockUser/" + userEmail, { responseType: 'text' });

  }
}
