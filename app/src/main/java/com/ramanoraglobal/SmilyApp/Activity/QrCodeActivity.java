package com.ramanoraglobal.SmilyApp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.ramanoraglobal.SmilyApp.R;

public class QrCodeActivity extends AppCompatActivity {

    Button btn_scan, btn_share, btn_newuser;
    ImageView iv_image;
    TextView tv_qrcodedata;

    EditText edt_name,edt_designation,edt_companyname,edt_mobileno;
    public static String visitorname = "";
    public static String visitororgnization = "";
    public static String visitorordesignation = "";
    public static String visitorormobile = "";
    RelativeLayout rl_menubuttun;
    LinearLayout ll_visitordata;
    Button btn_submit;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);
        IntentIntegrator scanIntent = new IntentIntegrator(QrCodeActivity.this);
        scanIntent.setCaptureActivity(ScanActivity.class);
        scanIntent.setBeepEnabled(true);
        ll_visitordata=findViewById(R.id.ll_visitordata);
        btn_submit=findViewById(R.id.btn_submit);
        edt_name=findViewById(R.id.edt_name);
        edt_designation=findViewById(R.id.edt_designation);
        edt_companyname=findViewById(R.id.edt_companyname);
        edt_mobileno=findViewById(R.id.edt_mobileno);

        rl_menubuttun=findViewById(R.id.rl_menubuttun);
        rl_menubuttun.setVisibility(View.GONE);
        scanIntent.setPrompt(getString(R.string.qr_code_scan_instruction));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        scanIntent.initiateScan();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_name.getText().toString().length()==0)
                {
                    edt_name.setError("Enter Name");
                    edt_name.requestFocus();
                }else  if(edt_mobileno.getText().toString().length()==0)
                {
                    edt_mobileno.setError("Enter MobileNo");
                    edt_mobileno.requestFocus();
                }else if(edt_companyname.getText().toString().length()==0)
                {
                    edt_companyname.setError("Enter Company name");
                    edt_companyname.requestFocus();
                } else if (edt_designation.getText().toString().length()==0) {
                    edt_designation.setError("Enter Designation");
                    edt_designation.requestFocus();
                }else {
                    visitorname = edt_name.getText().toString();
                    visitororgnization = edt_companyname.getText().toString();
                    visitorormobile=edt_mobileno.getText().toString();
                    visitorordesignation=edt_designation.getText().toString();
                    Intent i = new Intent(QrCodeActivity.this, QrCodeImageShare.class);
                    startActivity(i);
                    finish();
                }

            }
        });
      /*  iv_image = findViewById(R.id.iv_image);
        iv_image.setImageBitmap(MainActivity.picture);*/
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
        tv_qrcodedata = findViewById(R.id.tv_qrcodedata);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scanIntent = new IntentIntegrator(QrCodeActivity.this);
                scanIntent.setCaptureActivity(ScanActivity.class);
                scanIntent.setBeepEnabled(true);
                scanIntent.setPrompt(getString(R.string.qr_code_scan_instruction));
                scanIntent.initiateScan();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish(); //this method close current activity and return to previous
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result.getContents() != null) {
                tv_qrcodedata.setVisibility(View.VISIBLE);
                String scannedInfo = result.getContents();

                String[] splitqrdata = scannedInfo.split("\t");

                Log.e("MyQrCodeDataScan2leangth", splitqrdata.length + "");
                if (splitqrdata.length > 6) {

                    visitorname = splitqrdata[1];
                    visitororgnization = splitqrdata[3];
                    visitorormobile=splitqrdata[6];
                    visitorordesignation=splitqrdata[2];
                    Intent i = new Intent(QrCodeActivity.this, QrCodeImageShare.class);
                    startActivity(i);
                    finish();
                   /* mC_edtName.setText(splitqrdata[1]);
                    mC_edtdesignation.setText(splitqrdata[2]);
                    mC_edtCompany.setText(splitqrdata[3]);
                    mC_edtAddress.setText(splitqrdata[4]);
                    mC_edtEmail1.setText(splitqrdata[5]);
                    mC_edtPhone1.setText(splitqrdata[6]);
                    edt_qrcode.setVisibility(View.GONE);
                    txtqr.setVisibility(View.GONE);*/
                } else {
                    String[] splitqrdatatype2 = scannedInfo.split("  ");
                    Log.e("MyQrCodeDataScan2leangth", splitqrdatatype2.length + "");
                    if (splitqrdatatype2.length > 6) {

                        visitorname = splitqrdatatype2[1];
                        visitororgnization = splitqrdatatype2[3];
                        visitorormobile=splitqrdatatype2[6];
                        visitorordesignation=splitqrdatatype2[2];
                        Intent i = new Intent(QrCodeActivity.this, QrCodeImageShare.class);
                        startActivity(i);
                        finish();
                       /* mC_edtName.setText(splitqrdatatype2[1]);
                        mC_edtdesignation.setText(splitqrdatatype2[2]);
                        mC_edtCompany.setText(splitqrdatatype2[3]);
                        mC_edtAddress.setText(splitqrdatatype2[4]);
                        mC_edtEmail1.setText(splitqrdatatype2[5]);
                        mC_edtPhone1.setText(splitqrdatatype2[6]);
                        edt_qrcode.setVisibility(View.GONE);
                        txtqr.setVisibility(View.GONE);*/
                    } else {
                        if (scannedInfo.contains("|")) {
                            //  Log.e("MyQrCodeDataScan6leangth",scannedInfo+"");
                            splitqrdata = scannedInfo.split("\\|");
                            visitorname = splitqrdata[1];
                            visitororgnization = splitqrdata[3];
                            visitorormobile=splitqrdata[6];
                            visitorordesignation=splitqrdata[2];
                            Intent i = new Intent(QrCodeActivity.this, QrCodeImageShare.class);
                            startActivity(i);
                            finish();
                            /*mC_edtName.setText(splitqrdata[1]);
                            mC_edtdesignation.setText(splitqrdata[2]);
                            mC_edtCompany.setText(splitqrdata[3]);
                            mC_edtAddress.setText(splitqrdata[4]);
                            mC_edtEmail1.setText(splitqrdata[5]);
                            mC_edtPhone1.setText(splitqrdata[6]);
                            edt_qrcode.setVisibility(View.GONE);*/
                        } else {
                            tv_qrcodedata.setText("QrCodeData:-" + scannedInfo);
                            ll_visitordata.setVisibility(View.VISIBLE);
                            rl_menubuttun.setVisibility(View.GONE);
                           /* txtqr.setVisibility(View.VISIBLE);
                            edt_qrcode.setVisibility(View.VISIBLE);
                            edt_qrcode.setText(scannedInfo);*/
                        }

                    }
                }

                Log.e("MyQrCodeDataScan2leangth", scannedInfo + "");
            } else {
                Log.e("MyQrCodeDataScan2leangth", "QrDataNotFound");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
