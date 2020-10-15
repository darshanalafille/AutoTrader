package com.dfn.autotrader.store;

public class AdminStore {

    private static boolean isLoggedIn;

    public static void setLogin(){
        isLoggedIn = true;
    }

    public static boolean getLoginStatus(){
        return isLoggedIn;
    }

}
