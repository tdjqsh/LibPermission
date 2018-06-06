package com.ouym.testpermission;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ouym.libpermission.LPBaseActivity;
import com.ouym.libpermission.annotations.PermissionCheck;

public class MainActivity extends LPBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @PermissionCheck({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void test1(View view) {
//        new PermissionCompat.Builder(this)
//                .addPermissionRationale("请求权限")
//                .addPermissions(new String[]{Manifest.permission.INTERNET})
//                .addRequestPermissionsCallBack(new OnRequestPermissionsCallBack() {
//
//                    @Override
//                    public void onGrant() {
//                        Log.e("1234", "权限被授予");
//                    }
//
//                    @Override
//                    public void onDenied(String permission) {
//                        Log.e("1234", "-------权限被拒绝");
//                    }
//                })
//                .build().request();
        Toast.makeText(this, "权限管理通过后才显示", Toast.LENGTH_LONG).show();

    }
}