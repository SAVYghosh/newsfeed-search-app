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
  issearched:boolean=false;
  constructor(private formBuilder: FormBuilder,private getUserService: GetUserService,private router: Router) { }



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
        this.analystList.forEach(user=>{
          if(user.userName.includes(data.Name)){
          this.searchedList.push(user);
          }
        })
      }

      blockUser(i:number,user){
        this.searchedList.splice(i,1);
        this.analystList.splice(i,1);
        console.log(user.userEmail);
        this.getUserService.blockUser(user).subscribe(
          data=>{
            console.log(data);
          });
      }
}
