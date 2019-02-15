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
}