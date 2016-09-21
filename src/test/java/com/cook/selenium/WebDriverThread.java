package com.cook.selenium;


import com.cook.selenium.config.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

class WebDriverThread {

    private WebDriver webDriver;
    private DriverType selectedDriverType;

    private final DriverType defaultDriverType = DriverType.FIREFOX;
    //private final String browser = System.getProperty("browser").toUpperCase();
    private final String browser = getBrowserSetting();
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch").toUpperCase();

    public WebDriver getWebDriver() throws Exception {
        if (webDriver == null) {
            selectedDriverType = determineEffectiveDriverType();
            DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities();
            instantiateWebDriver(desiredCapabilities);
        }
        return webDriver;
    }

    private DriverType determineEffectiveDriverType() {
        DriverType driverType = defaultDriverType;
        Boolean hadDriverChanged = false;
        try {
            for (DriverType t : DriverType.values()
                    ) {
                if (t.toString().equals(browser)) {
                    driverType = DriverType.valueOf(browser);
                    hadDriverChanged = true;
                }
            }
            if (hadDriverChanged && driverType != defaultDriverType) {
                System.out.println("使用指定浏览器：" + driverType.toString());
            } else {
                System.out.println("使用默认浏览器:" + defaultDriverType);
            }
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified,defaulting to '" + defaultDriverType + "'.");
        }
        return driverType;
    }

    private void instantiateWebDriver(DesiredCapabilities desiredCapabilities) throws MalformedURLException {
        System.out.println(" ");
        System.out.println("当前操作系统为：" + operatingSystem);
        System.out.println("当前系统架构为：" + systemArchitecture);
        System.out.println("当前选择浏览器：" + selectedDriverType);
        System.out.println(" ");
        webDriver = selectedDriverType.getWebDriverObject(desiredCapabilities);
    }

    private String getBrowserSetting() {
        String browserSetting;
        try {
            browserSetting = System.getProperty("browser").toUpperCase();
            
        } catch (Exception e) {

            browserSetting = defaultDriverType.toString();
        }
        return browserSetting;
    }

    public void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
