package com.example.android_permission_monitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayPermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dispay_permission);

            String [] permissions = onGetPackagePermissions();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, permissions);

            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

            String appName = getIntent().getStringExtra("APP_NAME");
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(appName);

    }

    public String [] onGetPackagePermissions(){
        // String packageName = "com.google.android.keep";
        String packageName = getIntent().getStringExtra("PACKAGE_NAME");
        final PackageManager pm = getPackageManager();
        String [] defaultVal = {"null"};


        try {
            PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            String [] permissions = pi.requestedPermissions;

            for (String permission : permissions) {
                Log.d(null, "permission name:" + permission);
            }

            return permissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return defaultVal;

    }
}
