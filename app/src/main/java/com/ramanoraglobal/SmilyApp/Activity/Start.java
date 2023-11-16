package com.ramanoraglobal.SmilyApp.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.ramanoraglobal.SmilyApp.otherclasses.PermissionUtil;
import com.ramanoraglobal.SmilyApp.R;

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
public class Start extends AppCompatActivity {
    Button btn_start;
    String check;
    Dialog dialog_permission;
    private final int PERMISSIONS_REQUEST_READ_LOCATION = 11;
    public static String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,

            Manifest.permission.READ_EXTERNAL_STORAGE,

            Manifest.permission.WRITE_EXTERNAL_STORAGE

    };

    public static String[] permissions_13 = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,

            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        //Log.e("Chek",check);
        btn_start = findViewById(R.id.btn_start);
        dialog_permission = new Dialog(Start.this);
        dialog_permission.setContentView(R.layout.allowpermission);
        dialog_permission.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog_permission.setTitle("Allow Permission");
        CheckAllowPermission();
        Button btn_allowpermission = dialog_permission.findViewById(R.id.btn_allowpermission);
        btn_allowpermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Log.d("test", "onCreate: SDK_INT  if " + Build.VERSION.SDK_INT);
                    if (hasPermissions_13(Start.this, permissions_13)) {


                        dialog_permission.dismiss();
                        // createmediafolderapp();
                    } else {
                        //  //Log.d("test", "has no Permissions: ");
                        ActivityCompat.requestPermissions((Start.this), permissions_13, PERMISSIONS_REQUEST_READ_LOCATION);

                    }


                } else {
                    Log.d("test", "onCreate: else" + Build.VERSION.SDK_INT);
                    if (hasPermissions(Start.this, permissions)) {


                        dialog_permission.dismiss();
                        // createmediafolderapp();
                    } else {
                        //  //Log.d("test", "has no Permissions: ");
                        ActivityCompat.requestPermissions((Start.this), permissions, PERMISSIONS_REQUEST_READ_LOCATION);

                    }

                }
            }
        });
        ActivityCompat.requestPermissions(Start.this, new String[]{Manifest.permission.CAMERA}, 1);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SmilyCamera.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setMessage("Are you sure want exit SmilyApp?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Start.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case PERMISSIONS_REQUEST_READ_LOCATION: {
                if (PermissionUtil.verifyPermissions(grantResults)) {
                    dialog_permission.dismiss();
                    //createmediafolderapp();
                    new Handler().postDelayed(new Runnable() {


                        @Override
                        public void run() {
                            //checkFirstRun();
                        }
                    }, 3000);

                } else {
                    dialog_permission.show();
                    Toast.makeText(Start.this, "Need Allow Required Permission", Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }
    }

    public boolean hasPermissions_13(Context context, String... permissions) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }

        }
        return true;
    }

    private void CheckAllowPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.d("test", "onCreate: SDK_INT  if " + Build.VERSION.SDK_INT);
            if (hasPermissions_13(Start.this, permissions_13)) {


                dialog_permission.dismiss();
                //createmediafolderapp();
            } else {
                dialog_permission.show();
                //  //Log.d("test", "has no Permissions: ");
                //  ActivityCompat.requestPermissions((ActivityDCandCSV.this), permissions_13, PERMISSIONS_REQUEST_READ_LOCATION);

            }


        } else {
            Log.d("test", "onCreate: else" + Build.VERSION.SDK_INT);
            if (hasPermissions(Start.this, permissions)) {


                dialog_permission.dismiss();
                //   createmediafolderapp();
            } else {
                dialog_permission.show();
                //  //Log.d("test", "has no Permissions: ");
                //ActivityCompat.requestPermissions((ActivityDCandCSV.this), permissions, PERMISSIONS_REQUEST_READ_LOCATION);

            }

        }
    }

    public boolean hasPermissions(Context context, String... permissions) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
