package com.cook.selenium;

import com.cook.selenium.utility.LoginLanguage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 由 xin 创建于 2016/9/15.
 */
public class BasicTest {

    @Test
    public void adminLoginForEnglishWithoutKeepLogin() {
        ran_zhiLogin("admin", "admin", LoginLanguage.ENG, Boolean.FALSE);
    }

    @Test
    public void adminLoginForCHTWithoutKeepLogin() {
        ran_zhiLogin("admin", "admin", LoginLanguage.CHT, Boolean.FALSE);
    }

    private void ran_zhiLogin(final String account, final String password,
                              final LoginLanguage language, Boolean keepLogin) {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.linserver.com/ranzhi/www");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.tagName("button"));
        element.click();
        List<WebElement> lanList = driver.findElements(By.tagName("li"));
        switch (language) {
            case ZH_CN:
                element = lanList.get(0);
                break;
            case CHT:
                element = lanList.get(1);
                break;
            case ENG:
                element = lanList.get(2);
                break;
            default:
                element = lanList.get(0);
        }
        element.click();
        (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver input) {
                return input.getTitle().startsWith(language.getNextWebPageTitle());
            }
        });
        element = driver.findElement(By.name("account"));
        element.clear();
        element.sendKeys(account);
        element = driver.findElement(By.name("password"));
        element.clear();
        element.sendKeys(password);
        if (keepLogin) {
            element = driver.findElement(By.id("keepLogin1"));
            element.click();
        }
        driver.findElement(By.id("submit")).click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver input) {
                String expectedTitle;
                switch (language) {
                    case ZH_CN:
                        expectedTitle = "然知协同";
                        break;
                    case CHT:
                        expectedTitle = "然之協同";
                        break;
                    case ENG:
                        expectedTitle = "ranzhi";
                        break;
                    default:
                        expectedTitle = "然知协同";

                }
                return input.getTitle().startsWith(expectedTitle);
            }
        });
        driver.quit();
    }
}
