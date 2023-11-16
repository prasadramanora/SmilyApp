package com.ramanoraglobal.SmilyApp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.ramanoraglobal.SmilyApp.R;

public class Menu extends AppCompatActivity {
    Button btn_scan, btn_share, btn_newuser;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        btn_scan = findViewById(R.id.btn_scan);
        btn_newuser = findViewById(R.id.btn_newuser);
        btn_share = findViewById(R.id.btn_share);
        btn_newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SmilyCamera.class);
                startActivity(i);
                finish();
            }
        });
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ExhibitorList.class);
                startActivity(i);
                finish();
                //Toast.makeText(QrCodeActivity.this, "Comming Soon", Toast.LENGTH_SHORT).show();

            }
        });
       // tv_qrcodedata = findViewById(R.id.tv_qrcodedata);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scanIntent = new IntentIntegrator(Menu.this);
                scanIntent.setCaptureActivity(ScanActivity.class);
                scanIntent.setBeepEnabled(true);
                scanIntent.setPrompt(getString(R.string.qr_code_scan_instruction));
                scanIntent.initiateScan();
            }
        });
    }
}
