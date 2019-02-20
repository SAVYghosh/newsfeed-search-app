import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../Models/User';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
useUserEmail:String;
  constructor(private http: HttpClient) { }

    login(user : User) {
    this.useUserEmail=user.userEmail;
    return this.http.post("http://localhost:8085/auth/login",user,{responseType:'text'});
  }

}
