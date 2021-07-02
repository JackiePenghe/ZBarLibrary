package com.quicknew.zbarlibrary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sscl.baselibrary.utils.PermissionUtil;

/**
 * @author jackie
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scan(View view) {
        boolean b = PermissionUtil.hasPermissions(this, Manifest.permission.CAMERA);
        if (!b){
            showCameraPermissionDialog();
            return;
        }
        Intent intent = new Intent(this,ZbarScanActivity.class);
        startActivity(intent);
    }

    private void showCameraPermissionDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.no_permission)
                .setMessage(R.string.camera_permission_message)
                .setPositiveButton(R.string.go_setting, (dialog, which) -> PermissionUtil.toSettingActivity(MainActivity.this,REQUEST_CODE_CAMERA))
                .setNegativeButton(R.string.cancel,null)
                .setCancelable(false)
                .show();
    }
}