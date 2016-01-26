package com.example.swapnilpatil.client_app;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    private String fileNameTime = "storage_file_TIME";
    private String fileNameCust = "storage_file_CUSTOMERS";
    private int startTime = 0;
    private int numberOfCustomers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            openFile();
        } catch (IOException e) {
            try {
                createFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void createFile() throws IOException {
        FileOutputStream fos = openFileOutput(fileNameTime, Context.MODE_PRIVATE);
        FileOutputStream fos2 = openFileOutput(fileNameCust, Context.MODE_PRIVATE);
        int currentTimeNum = (int)Calendar.getInstance().getTimeInMillis();
        startTime = currentTimeNum;
        String currentTime = ""+currentTimeNum;
        fos.write(currentTime.getBytes());
        fos2.write("0".getBytes());
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

    public void addCustomer() throws IOException
    {
        numberOfCustomers++;
        FileOutputStream fos2 = openFileOutput(fileNameCust, Context.MODE_PRIVATE);
        fos2.write((""+numberOfCustomers).getBytes());
        fos2.close();
    }

    public double calculateRate()
    {
        int timeElapsed = (int)Calendar.getInstance().getTimeInMillis() - startTime;
        return (numberOfCustomers*1.00)/(timeElapsed*1.00);
    }
    
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void openFile() throws IOException
    {
        FileInputStream fis = new FileInputStream(fileNameTime);
        FileInputStream fis2 = new FileInputStream(fileNameCust);
        String line = "";
        String line2 = "";
        //FileInputStream fis = openFileInput(fileName); //Log.d(TAG, "Opened File Input - reading");
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis)))
        {
            while  ( ( line = bufferedReader.readLine()) != null )
            {
                line2 += line;
            }
            fis.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        startTime = Integer.parseInt(line2);

        line2 = "";
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis2)))
        {
            while  ( ( line = bufferedReader.readLine()) != null )
            {
                line2 += line;
            }
            fis2.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        numberOfCustomers = Integer.parseInt(line2);
    }
}
