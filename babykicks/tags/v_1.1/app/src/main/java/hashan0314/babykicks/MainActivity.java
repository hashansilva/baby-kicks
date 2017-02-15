/*
 * Copyright (c) Hashan Silva (hashan0314)
 * All Rights Reserved
 * All information contained herein is, and remains the property of the developer.
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from the developer.
 * Written by Hashan 2017.
 */

package hashan0314.babykicks;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView ivBabyKicks = (ImageView) findViewById(R.id.iv_babykicks);
        this.showMessage(R.string.msg_home_page);
        ivBabyKicks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog viewDateDialog = new AlertDialog.Builder(MainActivity.this).create();
                viewDateDialog.setTitle(R.string.dialog_date);
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String currentDateAsString = df.format(cal.getTime());
                viewDateDialog.setMessage(currentDateAsString);
                viewDateDialog.setIcon(R.drawable.action_save);
                viewDateDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Save",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Calendar cal = Calendar.getInstance();
                                writeDateTime(cal);
                                dialog.dismiss();
                            }
                        });
                viewDateDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                viewDateDialog.show();
            }
        });
    }

    /**
     * Writing Date and Time to internal storage
     * @param cal
     */
    private void writeDateTime(Calendar cal) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        String fileName = df.format(cal.getTime())+".txt";
        FileOutputStream fos;
        try {
            fos = openFileOutput(fileName, Context.MODE_APPEND);
            fos.write((tf.format(cal.getTime())+"-").getBytes());
            this.showMessage(R.string.msg_time_saved);
            fos.close();
        } catch (FileNotFoundException e) {
            this.showMessage(e.getMessage());
            Log.e(MainActivity.class.getName(),e.getMessage() + " - " +e.getStackTrace());
        } catch (IOException e) {
            this.showMessage(e.getMessage());
            Log.e(MainActivity.class.getName(), e.getMessage() + " - " + e.getStackTrace());
        }
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
        int menuId = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (menuId){
            /*case R.id.action_settings:
                return true;*/
            case R.id.action_view_history:
                Intent intent = new Intent("hashan0314.babykicks.VIEWHISTORYACTIVITY");
                this.startActivity(intent);
                break;
            case R.id.action_clear_history:
                this.clearRecentHistory();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * Clean the Internal Storage
     */
    private void clearRecentHistory() {
        AlertDialog viewDateDialog = new AlertDialog.Builder(MainActivity.this).create();
        viewDateDialog.setTitle(R.string.dialog_delete);
        viewDateDialog.setMessage("Do you want to clear the history ?");
        viewDateDialog.setIcon(R.drawable.delete_action);
        viewDateDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Delete",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] fileList = fileList();
                        for(String file:fileList){
                            if(file.contains(".txt")){
                                getBaseContext().deleteFile(file);
                                Toast.makeText(getBaseContext(),"File Deleted Successfully"+" - "+file,Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        viewDateDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        viewDateDialog.show();
    }
}
