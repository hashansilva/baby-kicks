/*
 * Copyright (c) Hashan Silva (hashan0314)
 * All Rights Reserved
 * All information contained herein is, and remains the property of the developer.
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from the developer.
 * Written by Hashan 2017.
 */

package hashan0314.babykicks;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity{

    /**
     * Show message
     * @param message
     */
    public void showMessage(int message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * Show message
     * @param message
     */
    public void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
