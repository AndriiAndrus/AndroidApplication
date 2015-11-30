package com.subliminals_central.subliminalscentral.subliminalscentral.UserActions;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserData {

    private static String USER_EMAIL;
    private static String USER_PASSWD;

    public static boolean isLogin = false;

    public static final String WEB_URL = "http://gamedevapi.tioo.ru/api/index.php?api=";
    public static final String API_LOGIN = "login";
    public static final String API_REG = "addUser";
    public static final String API_UPD = "update";

    private static int USER_POINTS;

    public static String getUserEmail() {
        return USER_EMAIL;
    }

    public static void setUserEmail(String userEmail) {
        USER_EMAIL = userEmail;
    }

    public static String getUserPasswd() {
        return USER_PASSWD;
    }

    public static void setUserPasswd(String userPasswd) {
        USER_PASSWD = userPasswd;
    }

    public static int getUserPoints() {
        return USER_POINTS;
    }

    public static void setUserPoints(int userPoints) {
        USER_POINTS = userPoints;
    }
}
