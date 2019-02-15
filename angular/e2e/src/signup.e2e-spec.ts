import { SignupPage } from './signup.po';
import { browser, protractor } from 'protractor';
import { LoginPage } from './login.po';

fdescribe('Protractor Testing - Signup Testing', () => {
    let loginPage: LoginPage;
    let signupPage: SignupPage;

    beforeEach(() => {
        loginPage = new LoginPage();
        signupPage = new SignupPage();
        loginPage.navigateTo();
        loginPage.navigateToSignup();
    })

    it('email already exist check',()=>{
        signupPage.setEmail("sudip@gmail.com");
        signupPage.setName("asdfgr");
        signupPage.setPassword("ASDer!222");
        signupPage.clickSignup();
        
        browser.wait(protractor.ExpectedConditions.alertIsPresent(),4000);
        expect(browser.switchTo().alert().getText()).toEqual("Email Already exist");
        browser.switchTo().alert().accept();
        expect(browser.getCurrentUrl()).toEqual("http://localhost:4200/Login")
    })

    it('successfully register',()=>{
        signupPage.setEmail("sudipq1223456@gmail.com");
        signupPage.setName("asdfgr");
        signupPage.setPassword("ASDer!222");
        signupPage.clickSignup();
        browser.wait(protractor.ExpectedConditions.alertIsPresent(),4000);
        expect(browser.switchTo().alert().getText()).toEqual("Registration Sucessfull");
        browser.switchTo().alert().accept();
        expect(browser.getCurrentUrl()).toEqual("http://localhost:4200/Login")
    })

})