package com.ouym.libpermission;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

public class LPBaseActivity extends FragmentActivity {

    public interface iGrantPermission{
        void permissionGranted() throws Throwable;
    }


    protected iGrantPermission igp;

    public void setIgp(iGrantPermission igp) {
        this.igp = igp;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == Macro.Req_Code){
            String[] noGot = new String[permissions.length];
            int j = 0;
            boolean[] b = new boolean[permissions.length];

            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    noGot[j++] = permissions[i];
                    b[i] = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                    Log.e("1234", permissions[i] + "没有获得权限,没有勾选don't ask " + b[i]);
                }
            }

            if (j == 0){
                try {
                    igp.permissionGranted();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else {
                //TODO 感觉UI自行修改
                Toast.makeText(this, "允许权限以便执行", Toast.LENGTH_SHORT).show();

                if (!Utils.isBoolArrayAllTrue(b)){
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            }
        }
    }
}
