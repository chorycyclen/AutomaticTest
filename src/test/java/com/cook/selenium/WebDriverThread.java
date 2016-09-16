package com.cook.selenium;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

class WebDriverThread {

    private WebDriver webDriver;

    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch").toUpperCase();

    public WebDriver getWebDriver() throws Exception {
        if (webDriver == null) {
            System.out.println(" ");
            System.out.println("当前操作系统为：" + operatingSystem);
            System.out.println("当前系统架构为：" + systemArchitecture);
            System.out.println(" ");
            webDriver = new FirefoxDriver(DesiredCapabilities.firefox());
        }
        return webDriver;
    }

    public void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
