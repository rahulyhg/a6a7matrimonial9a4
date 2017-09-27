package com.techno.matrimonial.Global;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.view.inputmethod.InputMethodManager;

import com.techno.matrimonial.MatrimonialApplication;
import com.techno.matrimonial.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class Global {

        public static final int MY_PERMISSIONS_REQUEST = 123;


    // Function to check Internet Connectivity
    public static synchronized boolean isNetworkAvailable(Context context) {
        boolean isConnected = false;
        if (context != null) {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
        }


        return isConnected;
    }


    public static void storePreference(String key, String value) {
        SharedPreferences.Editor editor = MatrimonialApplication.sharedPref
                .edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void storePreference(String key, int value) {
        SharedPreferences.Editor editor = MatrimonialApplication.sharedPref
                .edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void removePreference(String key) {
        SharedPreferences.Editor editor = MatrimonialApplication.sharedPref
                .edit();
        editor.remove(key);
        editor.commit();
    }

    public static void removePreferences(String keys[]) {
        SharedPreferences.Editor editor = MatrimonialApplication.sharedPref
                .edit();
        for (String key : keys) {
            editor.remove(key);
        }
        editor.commit();
    }

    public static void clearPreferences() {
        SharedPreferences.Editor editor = MatrimonialApplication.sharedPref
                .edit();
        String push = getPreference("FirebaseToken", "");
        editor.clear();
        editor.commit();
        storePreference("FirebaseToken", push);
    }


    public static void storePreference(String key, Boolean value) {
        SharedPreferences.Editor editor = MatrimonialApplication.sharedPref
                .edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static HashMap<String, String> getPreference(String[] keys) {
        HashMap<String, String> parameters = new HashMap<String, String>();
        for (String key : keys) {
            parameters.put(key,
                    MatrimonialApplication.sharedPref.getString(key, null));
        }
        return parameters;
    }

    public static String getPreference(String key, String defValue) {
        return MatrimonialApplication.sharedPref.getString(key, defValue);
    }

    public static int getPreference(String key, Integer defValue) {
        return MatrimonialApplication.sharedPref.getInt(key, defValue);
    }

    public static Boolean getPreference(String key, Boolean defValue) {
        return MatrimonialApplication.sharedPref.getBoolean(key, defValue);
    }

    public static void removeAllPreferences() {

        //Global.removePreferences(new String[]{Constants.IS_LOGGED_IN});
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    public static void activityTransition(Activity activity) {
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void activityBackTransition(Activity activity) {
        activity.overridePendingTransition(R.anim.hold, android.R.anim.fade_out);
    }


    // Function to hide keyboard
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    //for all permission
    public static boolean checkPermission(final Context context) {
        int readExternalStorage = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE);
        int writeExternalStorage = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
        int openCamera = ContextCompat.checkSelfPermission(context, CAMERA);
        int coarseLocation = ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        int fineLocation = ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (readExternalStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(READ_EXTERNAL_STORAGE);
        }
        if (writeExternalStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(WRITE_EXTERNAL_STORAGE);
        }

        if (openCamera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(CAMERA);

        }
        if (fineLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(ACCESS_FINE_LOCATION);

        }
        if (coarseLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) context, listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]), MY_PERMISSIONS_REQUEST);
            return false;
        }
        return true;
    }

    //For Date Convert

    public static String parseAndGetDate(long time) {
        long new_time = time * 1000L;

        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTimeInMillis(new_time);
        String date = DateFormat.format(Constants.APP_DATE_FORMAT, cal).toString();

        return date;
    }



}
