import { LoginPage } from './login.po';
import { browser, ExpectedConditions, protractor } from 'protractor';

xdescribe('Protractor - Login testing', () => {

    let loginPage: LoginPage;

    beforeEach(() => {
        loginPage = new LoginPage();
        loginPage.navigateTo();
    })

    
    it('check title when login page loads', () => {
        expect(loginPage.getPageTitleText().getText()).toEqual('Log In');
    });
    it('check navigation to signup page', () =>{
        loginPage.navigateToSignup();
        browser.wait(protractor.ExpectedConditions.urlContains('/Signup'))
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/Signup');
    });
    it('check blocked user log in',()=>{
        loginPage.setEmail("user2@user.com");
        loginPage.setPassword("User@10");
        loginPage.clickLogin();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/Login');
    });
    it('check wrong credential user log in',()=>{
        loginPage.setEmail("user@user.com");
        loginPage.setPassword("User12@10");
        loginPage.clickLogin();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/Login');
    });
    it('check successful user log in',()=>{
        loginPage.setEmail("user@user.com");
        loginPage.setPassword("User@10");
        loginPage.clickLogin();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/Usernav/News');
    });
    it('check successful admin log in',()=>{
        loginPage.setEmail("admin@admin.com");
        loginPage.setPassword("Admin@10");
        loginPage.clickLogin();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/GetUser');
    });
})