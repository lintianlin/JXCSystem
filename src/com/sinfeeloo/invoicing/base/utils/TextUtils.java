package com.sinfeeloo.invoicing.base.utils;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/4/2 13:19
 */
public class TextUtils {

    public static boolean notEmpty(String str) {

        if (null != str && !str.isEmpty()) {
            return true;
        }
        return false;
    }
}
