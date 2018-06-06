package com.kcode.permissionslib.main;

/**
 * request permission callback
 * Created by caik on 2017/2/17.
 */

public interface OnRequestPermissionsCallBack{

    void onGrant();

    void onDenied(String permission);
}
