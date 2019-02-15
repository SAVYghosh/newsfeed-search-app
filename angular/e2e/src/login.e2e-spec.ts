import { LoginPage } from './login.po';
import { browser, ExpectedConditions, protractor } from 'protractor';

fdescribe('Protractor - Login testing', () => {

    let loginPage: LoginPage;

    beforeEach(() => {
        loginPage = new LoginPage();
        loginPage.navigateTo();
    })

    
    it('check title when login page loads', () => {
        expect(loginPage.getPageTitleText().getText()).toEqual('Log In Here!!');
    });
    it('check navigation to signup page', () =>{
        loginPage.navigateToSignup();
        browser.wait(protractor.ExpectedConditions.urlContains('/Signup'))
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/Signup');
    })
    
})