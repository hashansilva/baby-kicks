/*
 * Copyright (c) Hashan Silva (hashan0314)
 * All Rights Reserved
 * All information contained herein is, and remains the property of the developer.
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from the developer.
 * Written by Hashan 2017.
 */

package hashan0314.babykicks;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class Helper {

    /**
     * Get Configuration Values
     * @param context
     * @param name
     * @return configurationValue
     */
    public static String getConfigValue(Context context, String name){
        Resources resources = context.getResources();

        try {
            InputStream rawResource = resources.openRawResource(R.raw.config);
            Properties properties = new Properties();
            properties.load(rawResource);
            return properties.getProperty(name);
        }catch (Resources.NotFoundException e){
            Log.e(Helper.class.getName(),R.string.error_resource_not_found+e.getMessage());
        } catch (IOException e) {
            Log.e(Helper.class.getName(),R.string.error_file_not_found+e.getMessage());
        }
        return null;
    }
}
