package com.cook.selenium.config;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Arrays;
import java.util.HashMap;

public enum DriverType implements DriverSetup {

    FIREFOX {
        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            return new FirefoxDriver(desiredCapabilities);
        }

        public DesiredCapabilities getDesiredCapabilities() {
            return DesiredCapabilities.firefox();
        }
    },

    CHROME {
        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            return new ChromeDriver(desiredCapabilities);
        }

        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            HashMap<String, String> chromePreference = new HashMap<String, String>();
            chromePreference.put("profile.password_manager_enabled", "false");
            capabilities.setCapability("chrome.prefs", chromePreference);
            return capabilities;
        }
    },

    MSEDGE {
        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            return new EdgeDriver(desiredCapabilities);
        }

        public DesiredCapabilities getDesiredCapabilities() {
            return DesiredCapabilities.edge();
        }
    },

    IE {
        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            return new InternetExplorerDriver(desiredCapabilities);
        }

        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            capabilities.setCapability("requireWindowFocus", true);
            return capabilities;
        }
    },

    SAFARI {
        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            return new SafariDriver(desiredCapabilities);
        }

        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setCapability("safari.cleanSession", true);
            return capabilities;
        }
    },

    OPERA {
        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            return new OperaDriver(desiredCapabilities);
        }

        public DesiredCapabilities getDesiredCapabilities() {
            return DesiredCapabilities.operaBlink();
        }
    }

}
