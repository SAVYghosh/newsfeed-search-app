import { Component, OnInit } from '@angular/core';
import { NewsApiService } from 'src/app/Services/news-api.service';
import { Router } from '@angular/router';
import { Location } from "@angular/common";
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Search } from 'src/app/Models/Search';
import { User } from 'src/app/Models/User';
import { LoginComponent } from '../login/login.component';
import { LoginService } from 'src/app/Services/login.service';

@Component({
	selector: 'app-news-api',
	templateUrl: './news-api.component.html',
	styleUrls: ['./news-api.component.css']
})
export class NewsApiComponent implements OnInit {

	isload: boolean;
	isSearch: boolean;
	searchForm: FormGroup;
	mArticles: Array<any>;
	mSources: Array<any>;
	searchResult: Search;
	user: User = new User();
	constructor(private formBuilder: FormBuilder, private newsapi: NewsApiService, private loginService: LoginService, private location: Location, private router: Router) {

		this.searchResult = new Search();
	}

	ngOnInit() {
			this.isload=true;
			this.isSearch = true;
			//load articles
			// console.log("call article");
			this.newsapi.initArticles().subscribe(data => this.mArticles = data['articles']);
			// //  //load news sources
			// this.newsapi.initSources().subscribe(data=> this.mSources = data['sources']);  
			this.searchForm = this.formBuilder.group({
				search: new FormControl()
			});
		
	}
	searchArticles(source) {
		this.mArticles = [];
		this.newsapi.getArticlesByID(source.search).subscribe(
			data => {
				this.mArticles = data['articles']
				if (this.mArticles.length > 0) {
					this.isSearch = true;
				}
				else {
					this.isSearch = false;
				}
			},
			error => {
				this.newsapi.initArticles().subscribe(data => this.mArticles = data['articles']);

			}
		);
		this.searchResult.searchString = source.search;
		this.searchResult.user = this.user;
		this.newsapi.saveSearch(this.searchResult).subscribe(
			data => {
			});

	}

}