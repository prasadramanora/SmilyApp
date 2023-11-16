package com.ramanoraglobal.SmilyApp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ramanoraglobal.SmilyApp.ModelClass.ModelClass;
import com.ramanoraglobal.SmilyApp.R;

import java.util.ArrayList;

public class ViewImage  extends AppCompatActivity {

  Button btn_retry,btn_qrcode;
  ImageView iv_userimg;
  public static Bitmap Visitorimage;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewimage);
        iv_userimg=findViewById(R.id.iv_userimg);
       // iv_userimg.setImageBitmap(SmilyCamera.picture);
       // iv_userimg.animate().rotation(360).start();
        Log.e("BitMapSize",SmilyCamera.picture.getWidth()+"");
        Log.e("BitMapSize",SmilyCamera.picture.getHeight()+"");
        Visitorimage=SmilyCamera.picture;
        if(SmilyCamera.picture.getWidth() > SmilyCamera.picture.getHeight())
        {
            Bitmap bMapRotate=null;
            Matrix mat=new Matrix();
            mat.postRotate(270);
            bMapRotate = Bitmap.createBitmap(SmilyCamera.picture, 0, 0,SmilyCamera.picture.getWidth(),SmilyCamera.picture.getHeight(), mat, true);
            SmilyCamera.picture.recycle();
           // SmilyCamera.picture=null;
            iv_userimg.setImageBitmap(bMapRotate);
        }else
        {
            iv_userimg.setImageBitmap(SmilyCamera.picture);
        }

        btn_qrcode=findViewById(R.id.btn_qrcode);
        btn_retry=findViewById(R.id.btn_retry);
        btn_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),QrCodeActivity.class);
                startActivity(i);
                finish();
            }
        });
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),SmilyCamera.class);
                startActivity(i);
                finish();
            }
        });
    }
}
