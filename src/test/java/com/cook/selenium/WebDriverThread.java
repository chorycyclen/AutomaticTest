package com.cook.selenium;


import com.cook.selenium.config.DriverType;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;

class WebDriverThread {

    private WebDriver webDriver;
    private DriverType selectedDriverType;

    private final DriverType defaultDriverType = DriverType.FIREFOX;
    //private final String browser = System.getProperty("browser").toUpperCase();
    private final String browser = getBrowserSetting();
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch").toUpperCase();
    private final boolean isUseRemoteWebDriver = Boolean.getBoolean("remoteDriver");

    WebDriver getWebDriver() throws Exception {
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
        if (isUseRemoteWebDriver) {
            URL seleniumGridURL = new URL(System.getProperty("gridURL"));
            String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
            String desiredPlatform = System.getProperty("desiredPlatform");
            if (desiredPlatform != null && desiredPlatform.length() > 0) {
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }

            if (desiredBrowserVersion != null && desiredBrowserVersion.length() > 0) {
                desiredCapabilities.setVersion(desiredBrowserVersion);
            }

            webDriver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        } else {
            webDriver = selectedDriverType.getWebDriverObject(desiredCapabilities);
        }
    }

    private String getBrowserSetting() {
        String browserSetting = "";
        try {
            browserSetting = readProperty().toUpperCase();
        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            if (browserSetting.equals("")) {
                browserSetting = defaultDriverType.toString().toUpperCase();
            }
        }
        return browserSetting;
    }

    private static String readProperty() throws Exception {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        String myPom = WebDriverThread.class.getResource("/").getPath() + "../../pom.xml";
        Model model = reader.read(new FileReader(myPom));
        return model.getProperties().getProperty("browser").toUpperCase();
    }

    void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
