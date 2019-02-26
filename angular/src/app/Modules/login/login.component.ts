import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/Services/login.service';
import { User } from 'src/app/Models/User';
import { AdminNavComponent } from 'src/app/admin-nav/admin-nav.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  checkInSubmission: boolean;
  email:string;
  isBlocked:boolean;
  isWrongCredential:boolean;
  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService) { }

  ngOnInit() {
    this.checkInSubmission = false;
    this.loginForm = this.formBuilder.group({
      userEmail: ['', [Validators.required, Validators.email,Validators.pattern('^([a-zA-Z0-9_\.-]+)@([a-zA-Z0-9_\.-]+)\\.([a-zA-Z]{2,5})$')]],
      userPassword: ['', [Validators.required, ]]
    });
  }

  get check() { return this.loginForm.controls; }

 

  onSubmit(user : User) {
    window.sessionStorage.setItem('email',user.userEmail.toString())

    this.checkInSubmission = true;
    if (this.loginForm.invalid) {
      return;
    };
    this.loginService.login(user).subscribe(
      data => {
        console.log(data);
        if(data=="blocked"){
          this.isBlocked=true;
          new Promise((res) => {
            setTimeout(() => {
            this.isBlocked= false;
            res();
            }, 6000);
            }) 
        }
        else{
        if(user.userEmail=="admin@admin.com")
        {
        //  window.sessionStorage.setItem('Token',data);
          window.sessionStorage.setItem('Token',data);
          this.router.navigate(['GetUser']);  
        }
        else
        {
     //   window.sessionStorage.setItem('Token',data);
        window.sessionStorage.setItem('Token',data);
        this.router.navigate(['Usernav']);   
        }  
      } 
      },
      error =>{
        
          this.isWrongCredential=true;
          new Promise((res) => {
            setTimeout(() => {
            this.isWrongCredential= false;
            this.router.navigate(['Login']);
            res();
            }, 6000);
            }) 
        }
        
    );
  }

}
