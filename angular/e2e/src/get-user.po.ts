import { browser, element, by } from 'protractor';

export class GetUserPage{
    constructor(){}

    navigateTo(){
        return browser.get('/GetUser');
    }
    navigateToLogin(){
        return browser.get('/Login');
    }

    getPageTitle(){
        return element(by.css('h3'));
    }

    setSearch(search: string){
        element(by.id('search')).clear();
        element(by.id('search')).sendKeys(search);
    }
    getUserNameFromTable(){
        return element(by.id('0'));
    }
 
    clickSearch() {
        element(by.css('.btn-success')).click();
    }
    clickBlock() {
        element(by.css('.btn-warning')).click();
    }
}