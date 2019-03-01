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
  isHistory:boolean;
  emailId: String;
  SearchList: Search[];
  isDelete: Boolean;
  constructor(private newsapi: NewsApiService, private router: Router) { }

  ngOnInit() {
    this.emailId = window.sessionStorage.getItem('email');
    this.newsapi.getSearch(this.emailId).subscribe(
      data => {
        this.SearchList = data;
      
      if(this.SearchList.length>0)
      {
        this.isHistory=true;
      }
      else
      this.isHistory=false;
    });
  }

  deleteSearch(i: number, searchId) {
    this.SearchList.splice(i, 1);
    this.newsapi.deleteSearch(searchId).subscribe(
      data => {
        if(this.SearchList.length>0)
        {
          this.isHistory=true;
        }
        else
        this.isHistory=false;
      }
    )
    alert("one history deleted");
    this.isDelete = true;
    new Promise((res) => {
      setTimeout(() => {
        this.isDelete = false;
        res();
      }, 3000);
    })
  }


}
