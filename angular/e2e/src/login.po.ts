import { browser, by, element } from 'protractor';

export class LoginPage {
    getPageTitleText() {
        return element(by.css('#logInHead'));
    }
constructor(){}
navigateTo(){
    return browser.get('/Login');
}

navigateToSignup() {
    element(by.id('signupButton')).click();
}
setEmail(userEmail: string) {
    element(by.id('Email')).clear();
    element(by.id('Email')).sendKeys(userEmail);
}

setPassword(userPassword: string) {
    element(by.id('userPassword')).clear();
    element(by.id('userPassword')).sendKeys(userPassword);
}

clickLogin() {
    element(by.css('#signInbut')).click();
}
}