package com.example.swapnilpatil.client_app;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private String fileNameTime = "storage_file_TIME";
    private String fileNameCust = "storage_file_CUSTOMERS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            openFile();
        } catch (IOException e) {
            createFile();
        }
    }

    public void createFile() throws IOException {
        FileOutputStream fos = openFileOutput(fileNameTime, Context.MODE_APPEND);
        FileOutputStream fos2 = openFileOutput(fileNameCust, Context.MODE_APPEND);
        
        //Log.d(TAG, "Created file");
        fos.close();
        fos2.close();
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

    public void addCustomer()
    {

    }
    
    public void openFile()
    {
        
    }
}
