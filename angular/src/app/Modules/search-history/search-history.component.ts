import { Component, OnInit } from '@angular/core';
import { NewsApiService } from 'src/app/Services/news-api.service';
import { Router } from '@angular/router';
import { Search } from 'src/app/Models/Search';

@Component({
  selector: 'app-search-history',
  templateUrl: './search-history.component.html',
  styleUrls: ['./search-history.component.css']
})
export class SearchHistoryComponent implements OnInit {
emailId:String;
SearchList: Search[];
  constructor(private newsapi: NewsApiService,private router: Router) { }

  ngOnInit() {
    this.emailId=window.sessionStorage.getItem('email');
    this.newsapi.getSearch(this.emailId).subscribe(
      data=>{
        this.SearchList=data;
        console.log(this.SearchList);
      }
    )
  }

  deleteSearch(i:number, searchId){
    this.SearchList.splice(i,1);
    this.newsapi.deleteSearch(searchId).subscribe(
      data=>
      {
        console.log("deleted")
      }
    )
  }


}
