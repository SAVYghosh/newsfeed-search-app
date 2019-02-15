import { Injectable } from '@angular/core';
import { User } from '../Models/User';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

   constructor(private http:HttpClient) { }

  signup(user: User) {
     console.log(user);
    return this.http.post("http://localhost:8085/auth/signup", user, { responseType: 'text' });
  }

}
