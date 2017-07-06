package com.example.user.p20170706;

/**
 * Created by user on 2017/7/6.
 */

class CheckLogin {
    private String sUID;
    private String sPassword;
    private final String targetUID = "admin";
    private final String targetPassword = "admin";
    public static final int ALL_CHECKED = 0;
    public static final int UID_MISS = 1;
    public static final int INPUT_ERROR = 2;

    CheckLogin() {
    }

    CheckLogin(String id, String pass) {
        sUID = id;
        sPassword = pass;
    }

    public int doCheck() {
        if (sUID.equals(targetUID) && sPassword.equals(targetPassword)) {
            return ALL_CHECKED;
        } else if (sUID.equals("")) {
            return UID_MISS;
        } else {
            return INPUT_ERROR;
        }
    }
}
