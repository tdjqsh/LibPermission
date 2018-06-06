package com.ouym.libpermission;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;

import org.aspectj.lang.ProceedingJoinPoint;

public class RequestPermission implements LPBaseActivity.iGrantPermission {

    private ProceedingJoinPoint joinPoint;


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void doPermissionGant(ProceedingJoinPoint joinPoint, Context activity, String[] notGrants) throws Throwable {

        ((LPBaseActivity) activity).setIgp(this);
        this.joinPoint = joinPoint;

        ActivityCompat.requestPermissions((Activity) activity, notGrants, Macro.Req_Code);

    }

    @Override
    public void permissionGranted() throws Throwable{
        joinPoint.proceed();
    }
}
