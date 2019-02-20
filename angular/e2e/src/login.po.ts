import { browser, by, element } from 'protractor';

export class LoginPage {
    getPageTitleText() {
        return element(by.css('h1'));
    }
constructor(){}
navigateTo(){
    return browser.get('/Login');
}

navigateToSignup() {
    element(by.id('signupButton')).click();
}
setEmail(userEmail: string) {
    element(by.id('userEmail')).clear();
    element(by.id('userEmail')).sendKeys(userEmail);
}

setPassword(userPassword: string) {
    element(by.id('userPassword')).clear();
    element(by.id('userPassword')).sendKeys(userPassword);
}

clickLogin() {
    element(by.css('.btn-outline-success')).click();
}
}