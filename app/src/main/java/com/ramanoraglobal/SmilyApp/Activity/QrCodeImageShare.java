package com.ramanoraglobal.SmilyApp.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.ramanoraglobal.SmilyApp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class QrCodeImageShare extends AppCompatActivity {
    Button btn_share;
    public static final String MEDIA_DIRECTORY_NAME = "Exhibitors Image";

    SQLiteDatabase exhibitordatabase;
    TextView tv_name, tv_designation, tv_orginization;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.share);
        tv_name = findViewById(R.id.tv_name);
        tv_orginization = findViewById(R.id.tv_orginization);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_designation = findViewById(R.id.tv_designation);
        try {
            tv_name.setText(QrCodeActivity.visitorname);
            tv_designation.setText(QrCodeActivity.visitorordesignation);
            tv_orginization.setText(QrCodeActivity.visitororgnization);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageView iv_image = findViewById(R.id.iv_image);



        if(SmilyCamera.VisitorImage.getWidth() > SmilyCamera.VisitorImage.getHeight())
        {
            Bitmap bMapRotate=null;
            Matrix mat=new Matrix();
            mat.postRotate(270);
            bMapRotate = Bitmap.createBitmap(SmilyCamera.VisitorImage, 0, 0,SmilyCamera.VisitorImage.getWidth(),SmilyCamera.VisitorImage.getHeight(), mat, true);
            SmilyCamera.VisitorImage.recycle();
            // SmilyCamera.picture=null;
            iv_image.setImageBitmap(bMapRotate);
        }else
        {
            iv_image.setImageBitmap(SmilyCamera.VisitorImage);
        }

        exhibitordatabase = openOrCreateDatabase("ExhibitorDb", Context.MODE_PRIVATE, null);
        exhibitordatabase.execSQL("INSERT INTO ExhibitorTable VALUES('" + QrCodeActivity.visitorname + "','" +
                QrCodeActivity.visitororgnization + "','" + QrCodeActivity.visitorordesignation + "');");


        btn_share = findViewById(R.id.btn_share);

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.rl_img);
                layout.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(layout.getDrawingCache());
                // Toast.makeText(QrCodeActivity.this, "Comming Soon", Toast.LENGTH_SHORT).show();
                saveImage(bitmap);
                String path = MediaStore.Images.Media.insertImage(QrCodeImageShare.this.getContentResolver(), bitmap, "Title", null);
                Uri imageUri = Uri.parse(path);

                @SuppressWarnings("unused")
                //  PackageInfo info = pm.getPackageInfo(pack, PackageManager.GET_META_DATA);

                Intent waIntent = new Intent(Intent.ACTION_SEND);
                waIntent.setType("image/*");
                waIntent.setPackage("com.whatsapp");
                waIntent.putExtra("jid", "91" + QrCodeActivity.visitorormobile + "@s.whatsapp.net");
                waIntent.putExtra(android.content.Intent.EXTRA_STREAM, imageUri);
                // waIntent.putExtra(Intent.EXTRA_TEXT, pack);
                startActivity(Intent.createChooser(waIntent, "Share with"));
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(getApplicationContext(), Menu.class);
                startActivity(i);
                finish();
                //this method close current activity and return to previous
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveImage(Bitmap picture) {
        // Log.d(TAG, "Taken picture is here!");


        FileOutputStream out = null;

        try {
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), MEDIA_DIRECTORY_NAME);

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    return;
                }
            }

            //create a new file, specifying the path, and the filename
            //which we want to save the file as.
            File file = new File(mediaStorageDir, "NF_" + System.currentTimeMillis() + "_PIC.jpg");

            out = new FileOutputStream(file);
            picture.compress(Bitmap.CompressFormat.JPEG, 50, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(getApplicationContext(), Menu.class);
        startActivity(i);
        finish();
    }
}
