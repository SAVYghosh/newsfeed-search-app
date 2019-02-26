import { browser,element, by } from 'protractor';

export class SignupPage{

    navigateTo(){
        return browser.get('/Signup');
    }

    getPageTitleText(){
       return element(by.css('#signUpHead'));
    }

    setName(userName: string) {
        element(by.id('userName')).clear();
        element(by.id('userName')).sendKeys(userName);
    }

    setEmail(userEmail: string) {
        element(by.id('userEmail')).clear();
        element(by.id('userEmail')).sendKeys(userEmail);
    }

    setPassword(userPassword: string) {
        element(by.id('userPassword')).clear();
        element(by.id('userPassword')).sendKeys(userPassword);
    }
    clickSignup() {
        element(by.css('#signUpBut')).click();
    }
    navigateToLogin() {
        element(by.css('a')).click();
    }
}