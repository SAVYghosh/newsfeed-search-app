import { browser,element, by } from 'protractor';

export class SignupPage{

    navigateTo(){
        return browser.get('/Signup');
    }

    getPageTitleText(){
       return element(by.css('h1'));
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
        element(by.css('.btn-success')).click();
    }
    navigateToLogin() {
        element(by.css('a')).click();
    }
}