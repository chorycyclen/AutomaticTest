package com.cook.selenium;

import com.cook.selenium.dataproviders.CSVDataProvider;
import com.cook.selenium.page_objects.LoginPage;
import com.cook.selenium.utility.LoginLanguage;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * 由 xin 创建于 2016/9/15.
 */
public class BasicTestWD extends DriverFactory {

    @Test(alwaysRun = true,dataProvider = "test1",
            dataProviderClass = CSVDataProvider.class,
            invocationCount = 2,
            description = "选择英文页面登陆然知并且不保持登陆")
    public void adminLoginForEnglishWithoutKeepLogin(String account,String password) throws Exception {
        getDriver().get("http://www.linserver.com/ranzhi/www");
        getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        LoginPage loginPage = new LoginPage();
        loginPage.selectLanguage(LoginLanguage.ENG)
                .enterAccount(account)
                .enterPassword(password)
                .checkKeepLogin(true)
                .clickLogin();
    }

    @Test(enabled = false,description = "选择繁体中文登陆然知并不保持登录")
    public void adminLoginForCHTWithoutKeepLogin() throws Exception {
        getDriver().get("http://www.linserver.com/ranzhi/www");
        LoginPage loginPage = new LoginPage();
        loginPage.selectLanguage(LoginLanguage.CHT)
                .enterAccount("admin")
                .enterPassword("admin")
                .checkKeepLogin(false)
                .clickLogin();
    }
}
