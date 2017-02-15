/*
 * Copyright (c) Hashan Silva (hashan0314)
 * All Rights Reserved
 * All information contained herein is, and remains the property of the developer.
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from the developer.
 * Written by Hashan 2017.
 */

package hashan0314.babykicks;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ViewHistoryActivity extends AppCompatActivity {

    private EditText searchDate;
    private ListView lvTimeList;
    private Toolbar toolbar;
    private DatePickerDialog selectDate;
    private Calendar cal;
    private String fileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        this.intiateComponents();
        cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = df.format(cal.getTime());
        searchDate.setText(currentDate);
        fileContent = this.readSavedFile(currentDate);
        searchDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mYear = cal.get(Calendar.YEAR);
                int mMonth = cal.get(Calendar.MONTH);
                int mDay = cal.get(Calendar.DAY_OF_MONTH);

                selectDate = new DatePickerDialog(ViewHistoryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = null;
                        String tmpDateString = year+"-"+(month+1)+"-"+dayOfMonth;
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date tmpDate = (Date) df.parse(tmpDateString);
                            selectedDate = df.format(tmpDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        searchDate.setText(selectedDate);
                        fileContent = readSavedFile(selectedDate);
                        configureListView(fileContent);
                    }
                },mYear,mMonth,mDay);
                selectDate.show();
            }
        });
        this.configureListView(fileContent);
    }

    /**
     * Configure List View
     * @param fileContent
     */
    private void configureListView(String fileContent) {
        lvTimeList.setAdapter(null);
        if(!(fileContent.isEmpty()) || fileContent.length() > 1) {
            String[] timeArray = fileContent.split("-");
            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, timeArray);
            lvTimeList.setAdapter(adapter);
        }else{
            Toast.makeText(getBaseContext(),R.string.msg_no_record_found,Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Initiate UI Components
     */
    private void intiateComponents() {
        searchDate = (EditText) findViewById(R.id.searchDate);
        lvTimeList = (ListView) findViewById(R.id.lvTimeList);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Read the Saved File
     * @param selectedDate
     * @return fileContent
     */
    private String readSavedFile(String selectedDate) {
        String fileName = selectedDate + ".txt";
        String fileContent = "";

        FileInputStream fin = null;
        try {
            fin = openFileInput(fileName);
            int c;
            while ((c = fin.read()) != -1) {
                fileContent = fileContent + Character.toString((char) c);
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT);
            Log.e(ViewHistoryActivity.class.getName(), e.getMessage() + " - " + e.getStackTrace());
        } catch (IOException e) {
            Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT);
            Log.e(ViewHistoryActivity.class.getName(), e.getMessage() + " - " + e.getStackTrace());
        }

        return fileContent;
    }
}
