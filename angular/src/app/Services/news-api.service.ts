import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Search } from '../Models/Search';
import { LoginService } from './login.service';
import { UseExistingWebDriver } from 'protractor/built/driverProviders';
import { User } from '../Models/User';
import { EmailValidator } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class NewsApiService {

  api_key = '89830f2b03064fb59a9a5d60eb1e3842';
  emailId: String;
  constructor(private http: HttpClient, private loginService: LoginService) {
  }

  initSources() {
    console.log("in source");

    return this.http.get('https://newsapi.org/v2/sources?language=en&apiKey=' + this.api_key);
  }

  initArticles() {
    console.log("in article");
    return this.http.get('https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=' + this.api_key);
  }

  getArticlesByID(source: String) {
    return this.http.get('https://newsapi.org/v2/everything?' +
      'q=' + source +
      '&from=2019-02-13&' +
      'sortBy=popularity&' +
      'apiKey=' + this.api_key);
  }

  saveSearch(searchResult: Search) {
    console.log("call service");
    searchResult.user.userEmail = this.loginService.useUserEmail;
    console.log(searchResult.user.userEmail);
    return this.http.post("http://localhost:8085/user/searchSave", searchResult, { responseType: 'text' })
  }

  getSearch(emailId) {
    return this.http.get<Array<Search>>("http://localhost:8085/user/searchHistory/" + emailId);
  }

  deleteSearch(searchId) {
    return this.http.get("http://localhost:8085/user/searchDelete/" + searchId, { responseType: 'text' });

  }

}
