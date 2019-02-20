import { UserNav } from "./user-nav.po";
import { browser, protractor } from 'protractor';
import { LoginPage } from './login.po';

fdescribe('user dashbord testing',()=>{
    let usernavpage : UserNav;
    let loginpage : LoginPage;
    
    beforeEach(()=>{
        usernavpage=new UserNav();   
        loginpage=new LoginPage();
        loginpage.navigateTo(); 
    });

    it("history navigate testing",()=>{
        
        loginpage.setEmail("user@user.com");
        loginpage.setPassword("User@10");
        loginpage.clickLogin();
        usernavpage.clickHistoryLink()
        browser.pause(3000);
        usernavpage.clickDeleteLink();
        var time=5000;
        browser.wait(protractor.ExpectedConditions.alertIsPresent(),time);
        expect(browser.switchTo().alert().getText()).toEqual('one history deleted');
        browser.switchTo().alert().accept();
        expect(browser.getCurrentUrl()).toContain('http://localhost:4200/Usernav/SearchHistory');
    })

});
