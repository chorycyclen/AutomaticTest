package com.cook.selenium.utility;

/**
 * 由 xin 创建于 2016/9/15.
 */
public enum LoginLanguage implements IPredictionTitle {

    //简体中文
    ZH_CN {
        public String getNextWebPageTitle() {
            return "登录";
        }
    },

    //繁体中文
    CHT {
        public String getNextWebPageTitle() {
            return "登錄";
        }
    },

    //英文
    ENG {
        public String getNextWebPageTitle() {
            return "Login";
        }
    }
}
