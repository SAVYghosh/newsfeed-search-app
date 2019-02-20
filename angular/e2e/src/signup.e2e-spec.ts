import { SignupPage } from './signup.po';
import { browser, protractor } from 'protractor';
import { LoginPage } from './login.po';

xdescribe('Protractor Testing - Signup Testing', () => {
    let loginPage: LoginPage;
    let signupPage: SignupPage;
    
    beforeEach(() => {
        loginPage = new LoginPage();
        signupPage = new SignupPage();
        loginPage.navigateTo();
        loginPage.navigateToSignup();
    })
    it('page header check',()=>{
        expect(signupPage.getPageTitleText().getText()).toEqual('Sign Up');
    })

    it('email already exist check',()=>{
        signupPage.setEmail("user@user.com");
        signupPage.setName("asdfgr");
        signupPage.setPassword("ASDer!222");
        signupPage.clickSignup();
        
        // browser.wait(protractor.ExpectedConditions.alertIsPresent(),4000);
        // expect(browser.switchTo().alert().getText()).toEqual("Email Already Exist");
        // browser.switchTo().alert().accept();
        expect(browser.getCurrentUrl()).toEqual("http://localhost:4200/Signup")
    })

    it('successfully register',()=>{
        signupPage.setEmail("sudipq12@gmail.com");
        signupPage.setName("asdfgr");
        signupPage.setPassword("ASDer!222");
        signupPage.clickSignup();
        // browser.wait(protractor.ExpectedConditions.alertIsPresent(),4000);
        // expect(browser.switchTo().alert().getText()).toEqual("Registration Sucessfull");
        // browser.switchTo().alert().accept();
        expect(browser.getCurrentUrl()).toEqual("http://localhost:4200/Login")
    })

})