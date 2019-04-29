package com.wujie.minewanandroid.util;

import android.text.TextUtils;

/**
 * Time：2019/1/26 0026 下午 15:31
 * Author：WuChen
 * Description：
 **/
public class LoginUtils {

    public static boolean isLogin() {
        String loginInfo = (String) SpUtils.get(Constant.LoginInfo, "");
        if (TextUtils.isEmpty(loginInfo)) {
            return false;
        } else {
            return true;
        }
    }

}
