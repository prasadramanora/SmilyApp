package com.ramanoraglobal.SmilyApp.utils;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.ramanoraglobal.SmilyApp.Activity.ExhibitorList;
import com.ramanoraglobal.SmilyApp.Activity.SmilyCamera;
import com.ramanoraglobal.SmilyApp.Activity.QrCodeActivity;
import com.ramanoraglobal.SmilyApp.R;


public class TabHostActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost(); // The activity TabHost
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, SmilyCamera.class);
        spec = tabHost.newTabSpec("Take Photo")
                .setIndicator("Take Photo", res.getDrawable(R.drawable.cameraimg))
                .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs

        intent = new Intent().setClass(this, QrCodeActivity.class);
        spec = tabHost.newTabSpec("QrCode")
                .setIndicator("QrCode", res.getDrawable(R.drawable.qrcodeimg))
                .setContent(intent);
        tabHost.addTab(spec);


        intent = new Intent().setClass(this, ExhibitorList.class);
        spec = tabHost
                .newTabSpec("User List")
                .setIndicator("User List",
                        res.getDrawable(R.drawable.userimg))
                .setContent(intent);
        tabHost.addTab(spec);

        //set tab which one you want open first time 0 or 1 or 2
        tabHost.setCurrentTab(0);
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View v = tabHost.getTabWidget().getChildAt(i);
           // v.setBackgroundResource(R.drawable.tabs);

            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.white));
        }
    }

}