import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { SignupService } from 'src/app/Services/signup.service';
import { User } from 'src/app/Models/User';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signUpForm: FormGroup;
  checkInSubmission: boolean;
  constructor(private formBuilder: FormBuilder, private router: Router,private signupService: SignupService) 
  { 
     
     }

  ngOnInit() {
    this.checkInSubmission = false;
    this.signUpForm=this.formBuilder.group({
      userName : ['',Validators.required],
      userEmail :['',[Validators.required,Validators.pattern('^([a-zA-Z0-9_\.-]+)@([a-zA-Z0-9_\.-]+)\\.([a-zA-Z]{2,5})$')]],
      userPassword :['',[Validators.required,Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%?&*_]).{8,16}$')]]

    })
  }

  get check() { return this.signUpForm.controls; }

  onSignup(user: User) {
    this.checkInSubmission = true;
    if (this.signUpForm.invalid) {
      return;
    };
    console.log("inside");
    console.log(user);
    this.signupService.signup(user).subscribe(
      data=>{
      console.log(data);
      if (data =="Registration successfull!"){
        alert("Registration Sucessfull");
          this.router.navigate(['Login']);
        }
        else
        {
          alert("Email Already exist");
          this.router.navigate(['Login']);

        }
      },
      
      error=>{
        alert("Something went wrong, try again");
        
      }
      
      
      );
  }
}
