import { browser, element,by } from 'protractor';

export class UserNav{
    navigateTo(){
        return browser.get('/usernav');
    }

    clickHistoryLink(){
        element(by.id('history')).click();
    }

    clickDeleteLink(){
        element(by.id('1')).click();
    }


}