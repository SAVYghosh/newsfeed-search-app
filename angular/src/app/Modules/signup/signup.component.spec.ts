import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupComponent } from './signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SignupService } from 'src/app/Services/signup.service';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';

fdescribe('SignupComponent', () => {
  let component: SignupComponent;
  let fixture: ComponentFixture<SignupComponent>;
  
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignupComponent ],
      imports : [ ReactiveFormsModule, HttpClientModule, RouterTestingModule ],
      providers : [ SignupService]
    })
    .compileComponents();
    fixture = TestBed.createComponent(SignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));


  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('is form invalid-invalid email', () =>{
    let name=component.signUpForm.controls['userName'];
    name.setValue("baaa@baaaa");

    let email=component.signUpForm.controls['userEmail'];
    email.setValue("bbbbbb");

    let pass=component.signUpForm.controls['userPassword'];
    pass.setValue("bbb222!!B");

    expect(component.signUpForm.valid).toBeFalsy();
  });

  it('is form invalid - invalid data', () =>{
    let name=component.signUpForm.controls['userName'];
    name.setValue("b");

    let email=component.signUpForm.controls['userEmail'];
    email.setValue("b");

    let pass=component.signUpForm.controls['userPassword'];
    pass.setValue("b");
  

    expect(component.signUpForm.valid).toBeFalsy();
  });

  it('is form invalid - missing user name', () =>{
    let name=component.signUpForm.controls['userName'];
    name.setValue("");

    let email=component.signUpForm.controls['userEmail'];
    email.setValue("b@b");

    let pass=component.signUpForm.controls['userPassword'];
    pass.setValue("1Bbbbbbb");

    expect(component.signUpForm.valid).toBeFalsy();
  });

  it('is form invalid - invalid password', () =>{
    let name=component.signUpForm.controls['userName'];
    name.setValue("bbsde");

    let email=component.signUpForm.controls['userEmail'];
    email.setValue("bbsde@wer.dd");

    let pass=component.signUpForm.controls['userPassword'];
    pass.setValue("1Bbbbbbb");

    expect(component.signUpForm.valid).toBeFalsy();
  });

  it('is form invalid - invalid password size', () =>{
    let name=component.signUpForm.controls['userName'];
    name.setValue("beeeb");

    let email=component.signUpForm.controls['userEmail'];
    email.setValue("bded@beded.ee");

    let pass=component.signUpForm.controls['userPassword'];
    pass.setValue("Bs!1");

    expect(component.signUpForm.valid).toBeFalsy();
  });
  it('is form valid', () =>{
    let name=component.signUpForm.controls['userName'];
    name.setValue("abcdef");

    let email=component.signUpForm.controls['userEmail'];
    email.setValue("baaa@baa.com");

    let pass=component.signUpForm.controls['userPassword'];
    pass.setValue("Asd123!!");

    expect(component.signUpForm.valid).toBeTruthy();
  });

});
