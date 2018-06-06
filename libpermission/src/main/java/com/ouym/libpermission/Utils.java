package com.ouym.libpermission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sc5 on 2018/6/5.
 */

public class Utils {

    /**
     * 权限组中拿到哪些权限是没有授权的
     */
    public static String[] getPermissionNotGranted(Context cxt, String[] permissions){
        List<String> notGranted = new ArrayList<>();
        for (String permission : permissions){
            if (ContextCompat.checkSelfPermission(cxt, permission) != PackageManager.PERMISSION_GRANTED){
                notGranted.add(permission);
            }
        }
        return notGranted.toArray(new String[notGranted.size()]);
    }

    public static boolean isBoolArrayAllTrue(boolean[] booleans){
        if (null == booleans || 0 == booleans.length){
            return false;
        }

        boolean result = true;
        for (int i = 0; i < booleans.length; i++){
            result = result &booleans[i];
        }

        return result;
    }
}
