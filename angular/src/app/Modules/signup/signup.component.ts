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
  correct:boolean;
  incorrect:boolean;


  constructor(private formBuilder: FormBuilder, private router: Router,private signupService: SignupService) 
  { 
     
     }

  ngOnInit() {
    this.checkInSubmission = false;
    this.signUpForm=this.formBuilder.group({
      userName : ['',[Validators.required,Validators.minLength(4),Validators.maxLength(20),Validators.pattern('^[a-zA-Z ]+$')]],
      userEmail :['',[Validators.required,Validators.pattern('^([a-zA-Z0-9_\.-]+)@([a-zA-Z0-9_\.-]+)\\.([a-zA-Z]{2,5})$')]],
      userPassword :['',[Validators.required,Validators.maxLength(12),Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%?&*_]).{6,}$')]]

    })
  }

  get check() { return this.signUpForm.controls; }

  onSignup(user: User) {
    this.checkInSubmission = true;
    if (this.signUpForm.invalid) {
      return;
    };
    this.signupService.signup(user).subscribe(
      data=>{
      if (data =="Registered"){
        this.correct=true;
        new Promise((res) => {
          setTimeout(() => {
          this.correct = false;
          this.router.navigate(['Login']);
          res();
          }, 6000);
          }) 
        }
        else
        {
          this.incorrect=true;
          new Promise((res) => {
            setTimeout(() => {
            this.incorrect = false;
            res();
            }, 6000);
            }) 
            this.router.navigate(['Signup']);

        }
      },
      error=>{
        alert("Something went wrong, try again after sometime");   
      });
  }
}
