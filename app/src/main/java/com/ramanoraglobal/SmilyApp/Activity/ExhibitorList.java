package com.ramanoraglobal.SmilyApp.Activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.ramanoraglobal.SmilyApp.Adapter.ExhibitorListAdapter;
import com.ramanoraglobal.SmilyApp.Adapter.VisitorAdaptor;
import com.ramanoraglobal.SmilyApp.ModelClass.ModelClass;
import com.ramanoraglobal.SmilyApp.R;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ExhibitorList  extends AppCompatActivity {

    SQLiteDatabase exhibitordatabase;
    ArrayList<ModelClass> vistorlistdata = new ArrayList();
    ArrayList <Bitmap>personImages = new ArrayList();@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibitorlist);
        exhibitordatabase=openOrCreateDatabase("ExhibitorDb", Context.MODE_PRIVATE, null);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    protected void onStart() {
        super.onStart();
        Cursor c=exhibitordatabase.rawQuery("SELECT * FROM ExhibitorTable", null);
        if(c.getCount()==0)
        {
            // showMessage("Error", "No records found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {

            ModelClass  modelClass=new ModelClass();

            Log.e("Checkbitmap",c.getString(2)+"");
            InputStream inputStream  = new ByteArrayInputStream(c.getString(2).getBytes());
            Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
                Log.e("Checkbitmap",bitmap+"");
                modelClass.setVisitorname(c.getString(0));
            modelClass.setVisitordesignation(c.getString(2));
            modelClass.setVisitororgnizzation(c.getString(1));



            vistorlistdata.add(modelClass);
           /* Bitmap b=StringToBitMap(c.getString(2));
            personImages.add(b);
            personNames.add(c.getString(0));*/
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default horizontal orientation and false value for reverseLayout to show the items from start to end
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of CustomAdapter to send the reference and data to Adapter
        VisitorAdaptor customAdapter = new VisitorAdaptor(ExhibitorList.this, vistorlistdata);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

    public Bitmap StringToBitMap(String image){
        try{
            byte [] encodeByte=Base64.decode(image, Base64.DEFAULT);

            InputStream inputStream  = new ByteArrayInputStream(encodeByte);
            Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
