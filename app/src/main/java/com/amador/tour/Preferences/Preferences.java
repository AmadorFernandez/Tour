package com.amador.tour.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by amador on 8/12/16.
 */

public class Preferences {

    public static final String USER_NAME_KEY = "user";
    public static final String USER_MAIL_KEY = "mail";
    private static SharedPreferences preferences;

    public static void setUserName(String userName, Context context){

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_NAME_KEY, userName);
        editor.commit();

    }

    public static void setUserMail(String userMail, Context context){

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_MAIL_KEY, userMail);
        editor.commit();

    }

    public static String getUserName(Context context){

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(USER_NAME_KEY, null);
    }

    public static String getUserMail(Context context){

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(USER_MAIL_KEY, null);
    }

}
