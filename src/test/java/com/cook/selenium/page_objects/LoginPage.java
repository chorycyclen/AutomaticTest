package com.cook.selenium.page_objects;

import com.cook.selenium.DriverFactory;
import com.cook.selenium.utility.LoginLanguage;
import com.google.common.base.Function;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

/**
 * 然知登陆页面
 */
public class LoginPage {

    @FindBy(how = How.CSS, using = "#langs > button")
    private WebElement languageSwitchLocator;

    @FindBy(how = How.CSS, using = "#langs > ul > li.active > a")
    private WebElement simplifiedChineseLocator;

    @FindBy(how = How.CSS, using = "#langs > ul > li:nth-child(2) > a")
    private WebElement traditionalChineseLocator;

    @FindBy(how = How.CSS, using = "#langs > ul > li:nth-child(3) > a")
    private WebElement englishLocator;

    @FindBy(how = How.CSS, using = "#account")
    private WebElement accountLocator;

    @FindBy(how = How.CSS, using = "#password")
    private WebElement passwordLocator;

    @FindBy(how = How.CSS, using = "#submit")
    private WebElement loginLocator;

    @FindBy(how = How.CSS, using = "#keepLogin1")
    private WebElement keepLoginCheckBoxLocator;

    public LoginPage() throws Exception {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public LoginPage selectLanguage(LoginLanguage language) throws Exception {
        languageSwitchLocator.click();
        String currentTitle=DriverFactory.getDriver().getTitle();
        switch (language) {
            case ZH_CN:
                simplifiedChineseLocator.click();
                break;
            case CHT:
                traditionalChineseLocator.click();
                break;
            case ENG:
                englishLocator.click();
                break;
            default:
                simplifiedChineseLocator.click();
        }
        return this;
    }

    public LoginPage enterAccount(String account) {
        accountLocator.clear();
        accountLocator.sendKeys(account);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordLocator.clear();
        passwordLocator.sendKeys(password);
        return this;
    }

    public void clickLogin() {
        loginLocator.click();
    }

    public LoginPage checkKeepLogin(Boolean keepLogin) {
        if (keepLogin) {
            keepLoginCheckBoxLocator.click();
        }
        return this;
    }

    public void login(String account, String password, LoginLanguage language, Boolean keepLogin) {
        languageSwitchLocator.click();
        switch (language) {
            case ZH_CN:
                simplifiedChineseLocator.click();
                break;
            case CHT:
                traditionalChineseLocator.click();
                break;
            case ENG:
                englishLocator.click();
                break;
            default:
                simplifiedChineseLocator.click();
        }
        accountLocator.clear();
        accountLocator.sendKeys(account);
        passwordLocator.clear();
        passwordLocator.sendKeys(password);
        if (keepLogin) {
            keepLoginCheckBoxLocator.click();
        }
        loginLocator.click();

    }

}
