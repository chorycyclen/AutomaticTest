package com.cook.selenium;

import com.cook.selenium.page_objects.LoginPage;
import com.cook.selenium.utility.LoginLanguage;
import org.testng.annotations.Test;

/**
 * 由 xin 创建于 2016/9/15.
 */
public class BasicTestWD extends DriverFactory {

    @Test
    public void adminLoginForEnglishWithoutKeepLogin() throws Exception {
        getDriver().get("http://www.linserver.com/ranzhi/www");
        LoginPage loginPage = new LoginPage();
        loginPage.selectLanguage(LoginLanguage.ENG)
                .enterAccount("admin")
                .enterPassword("admin")
                .checkKeepLogin(true)
                .clickLogin();
    }

    @Test
    public void adminLoginForCHTWithoutKeepLogin() throws Exception {
        getDriver().get("http://www.linserver.com/ranzhi/www");
        LoginPage loginPage = new LoginPage();
        loginPage.selectLanguage(LoginLanguage.ZH_CN)
                .enterAccount("admin")
                .enterPassword("admin")
                .checkKeepLogin(false)
                .clickLogin();
    }
}
