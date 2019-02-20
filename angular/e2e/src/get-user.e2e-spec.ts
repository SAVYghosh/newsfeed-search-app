import { GetUserPage } from "./get-user.po";
import { LoginPage } from './login.po';
import { browser, protractor, Browser } from 'protractor';

xdescribe('Protractor Testing- GetUser Testing',()=>{
    let getuserPage: GetUserPage;
    let loginPage : LoginPage;
    beforeEach(()=>{
        loginPage = new LoginPage();
        loginPage.navigateTo();
        getuserPage= new GetUserPage();
    })

    
    
    it('check title when get user page load',()=>{
        loginPage.setEmail("admin@admin.com");
        loginPage.setPassword("Admin@10");
        loginPage.clickLogin();
        // browser.wait(protractor.ExpectedConditions.visibilityOf(getuserPage.getPageTitle()));
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/GetUser');
        expect(getuserPage.getPageTitle().getText()).toEqual('User Details:');

    });


    // it('search user works properly',()=>{
    //     loginPage.setEmail("admin@admin.com");
    //     loginPage.setPassword("Admin@10");
    //     loginPage.clickLogin();
    //     getuserPage.setSearch("wewewe");
    //     getuserPage.clickSearch();
    //     expect(getuserPage.getUserNameFromTable().getText()).toEqual('wewewe');
    // })
    it('block user works properly',()=>{
        loginPage.setEmail("admin@admin.com");
        loginPage.setPassword("Admin@10");
        loginPage.clickLogin();
        getuserPage.setSearch("wewewe");
        getuserPage.clickSearch();
        getuserPage.clickBlock();
        browser.pause(2000);
        var time=8000;
        browser.wait(protractor.ExpectedConditions.alertIsPresent(),time);
        expect(browser.switchTo().alert().getText()).toEqual('user blocked');
        browser.switchTo().alert().accept();
    })
})