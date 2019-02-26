import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupComponent } from './signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SignupService } from 'src/app/Services/signup.service';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { User } from 'src/app/Models/User';
import { of } from 'rxjs/internal/observable/of';
import { By } from '@angular/platform-browser';

fdescribe('SignupComponent', () => {
  let component: SignupComponent;
  let fixture: ComponentFixture<SignupComponent>;
  let signupService: SignupService;
  
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignupComponent ],
      imports : [ ReactiveFormsModule, HttpClientModule, RouterTestingModule ],
      providers : [ SignupService]
    })
    .compileComponents();
    fixture = TestBed.createComponent(SignupComponent);
    component = fixture.componentInstance;
    signupService=fixture.debugElement.injector.get(SignupService);
    fixture.detectChanges();
  }));


  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('is form invalid-invalid email', () =>{
    let name=component.signUpForm.controls['userName'];
    name.setValue("bbaaaa");

    let email=component.signUpForm.controls['userEmail'];
    email.setValue("bbbbbb");

    let pass=component.signUpForm.controls['userPassword'];
    pass.setValue("bbb222!!B");

    expect(component.signUpForm.valid).toBeFalsy();
    
    fixture.debugElement.query(By.css('#signUpBut')).nativeElement.click();
    fixture.detectChanges();
    let text = fixture.debugElement.nativeElement.querySelector('#userEmailPatternError');
    expect(text.innerHTML).toContain('Email must be vaild');
  });

  it('is form invalid - invalid data', () =>{
    let name=component.signUpForm.controls['userName'];
    name.setValue("b");

    let email=component.signUpForm.controls['userEmail'];
    email.setValue("b");

    let pass=component.signUpForm.controls['userPassword'];
    pass.setValue("b");
  

    expect(component.signUpForm.valid).toBeFalsy();

    fixture.debugElement.query(By.css('#signUpBut')).nativeElement.click();
    fixture.detectChanges();
    let text1 = fixture.debugElement.nativeElement.querySelector('#userNameMinLengthError');
    expect(text1.innerHTML).toContain('Username min length must be 4');

    fixture.debugElement.query(By.css('#signUpBut')).nativeElement.click();
    fixture.detectChanges();
    let text2 = fixture.debugElement.nativeElement.querySelector('#userEmailPatternError');
    expect(text2.innerHTML).toContain('Email must be vaild');

    fixture.debugElement.query(By.css('#signUpBut')).nativeElement.click();
    fixture.detectChanges();
    let text3 = fixture.debugElement.nativeElement.querySelector('#userPasswordPatternError');
    expect(text3.innerHTML).toContain('Password min size must be 6 an it contains one Uppercase,one lowercase,one digit one special characters');
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
it('is service called',()=>
{
 let response:any;
 let user:User=new User();

 let spy=spyOn(signupService,'signup').and.returnValue(of({status:200}));
 signupService.signup(user).subscribe(data=>
  {
    response=data;
  });
  fixture.debugElement.query(By.css('form')).triggerEventHandler('submit',null);
  expect(response).toEqual({status:200});
  expect(spy).toHaveBeenCalledTimes(1);
});
});
