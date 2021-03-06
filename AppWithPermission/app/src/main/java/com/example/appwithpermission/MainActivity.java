package com.example.appwithpermission;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private final static String PATH = "/sdcard/skipPermission";
    private final static String FILENAME = "/contacts.txt";

    // ref: https://blog.csdn.net/aicpzl/article/details/51451984
    private void onWrite(String str) {
        try {
            Log.e("Running", "Start Write");
            // check if sd card exist
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                File path = new File(PATH);
                File f = new File(PATH + FILENAME);
                if(!path.exists()){
                    path.mkdirs();
                }
                if (!f.exists()) {
                    f.createNewFile();
                }else if(f.exists()){
                }
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f, true));
                osw.write(str);
                osw.close();
            }
        } catch (Exception e) {
            Log.d("Error", "file create error");
        }

    }

    private void updateFile() {
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                File path = new File(PATH);
                File f = new File(PATH + FILENAME);
                if(f.exists()){
                    f.delete();
                    f.createNewFile();
                }

            }
        } catch (Exception e) {
            Log.d("Error", "file create error2");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getAllContacts(View view){
        updateFile();
        // reference: https://stackoverflow.com/questions/12562151/android-get-all-contacts
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        onWrite("Name: " + name + "\n");
                        onWrite("Phone: " + phoneNo + "\n");
                        Log.i(null, "Name: " + name);
                        Log.i(null, "Phone Number: " + phoneNo);
                    }
                    pCur.close();
                }
            }
        }
        if(cur!=null){
            cur.close();
        }
    }
}


