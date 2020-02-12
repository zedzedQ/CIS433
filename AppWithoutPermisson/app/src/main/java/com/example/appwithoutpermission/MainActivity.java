package com.example.appwithoutpermission;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {


    private final static String PATH = "/sdcard/digu";
    private final static String FILENAME = "/notes.txt";
    String text1 ="888888888888888888888888888888888";

    //向sdcard写文件
    /**
     * 写文件
     */
    private void onWrite(String str) {
        try {
            Log.e("555555555555555", "Start Write");
            //1.判断是否存在sdcard
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                //目录
                File path = new File(PATH);
                //文件
                File f = new File(PATH + FILENAME);
                if(!path.exists()){
                    //2.创建目录，可以在应用启动的时候创建
                    path.mkdirs();
                }
                if (!f.exists()) {
                    //3.创建文件
                    f.createNewFile();
                }else if(f.exists()){
                    f.delete();
                    f.createNewFile();
                }
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f));
                //4.写文件，从EditView获得文本值
                osw.write(str);
                osw.close();
            }
        } catch (Exception e) {
            Log.d("66666666666666", "file create error");
        }

    }

    public String  readtex(){
        //文件
        File f = new File(PATH + FILENAME);
        FileInputStream is;
        try {
            is = new FileInputStream(f);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] array = new byte[1024];
            int len = -1;
            while( (len = is.read(array)) != -1){
                bos.write(array,0,len);
                bos.close();
                is.close();
                Toast.makeText(MainActivity.this,"读出来的数据" +bos.toString(), Toast.LENGTH_SHORT).show();
                Log.e("读出来的数据", bos.toString());
                return bos.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
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
        onWrite("www888888888888888888888888888888888");
        readtex();
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
}
