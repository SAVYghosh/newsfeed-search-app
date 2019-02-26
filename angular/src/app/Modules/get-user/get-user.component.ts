import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { GetUserService } from 'src/app/Services/get-user.service';
import { Router } from '@angular/router';
import { User } from 'src/app/Models/User';

@Component({
  selector: 'app-get-user',
  templateUrl: './get-user.component.html',
  styleUrls: ['./get-user.component.css']
})
export class GetUserComponent implements OnInit {
  name: String;
	searchForm: FormGroup;
  analystList:User[];
  searchedList:User[];
  user: User;
  buttonList:number[];
  issearched:boolean=false;
  isBlock:boolean;
  isSearchBlock:boolean;
  isFound:boolean;
  constructor(private formBuilder: FormBuilder,private getUserService: GetUserService,private router: Router) {

    this.buttonList=new Array();

   }



  ngOnInit() {
    this.searchedList=new Array();
    this.issearched=false;
    this.searchForm = this.formBuilder.group({
      Name: ['']
      });
  
      this.getUserService.getAllUser().subscribe(
        data => {
          this.analystList=data;   
        });    
      }
      
      searchUser(data){  
        this.issearched=true;
        this.searchedList=[];
        console.log("search call");
        this.analystList.forEach(user=>{
          if(user.userEmail.includes(data.Name)){
          this.searchedList.push(user);
          }
        })
        if(this.searchedList.length==0){
          this.isFound=true;
          new Promise((res) => {
            setTimeout(() => {
            this.isFound= false;
            res();
            }, 4000);
            })         }
      }

      blockUser(i:number,user){

        this.buttonList[i]=1;
        this.getUserService.blockUser(user).subscribe(
          data=>{
          });

          this.isBlock=true;
          new Promise((res) => {
            setTimeout(() => {
            this.isBlock= false;
            res();
            }, 4000);
            }) 
          this.analystList[i].userStatus=false;
          alert("user blocked");


      }
      blockSearchUser(i:number,user){

        this.buttonList[i]=1;
        this.getUserService.blockUser(user).subscribe(
          data=>{
          });
          this.isSearchBlock=true;
          new Promise((res) => {
            setTimeout(() => {
            this.isSearchBlock= false;
            res();
            }, 4000);
            }) 
           this.searchedList[i].userStatus=false;
            alert("user blocked");
      }

      unblockSearchUser(i:number,user){
        this.getUserService.unblockUser(user).subscribe(
          data=>{

          }
        );
        this.searchedList[i].userStatus=true;
        alert("user unblocked");
      }
      unblockUser(i:number,user){
        this.getUserService.unblockUser(user).subscribe(
          data=>{

          }
        );
        this.analystList[i].userStatus=true;
        alert("user unblocked");
      }
}
