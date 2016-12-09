package com.amador.tour.Utils;

import android.util.Patterns;

/**
 * Created by amador on 8/12/16.
 */

public class ValidateData {

    public static final int MAX_LENGT_USER_NAME = 20;
    public static final int MIN_LENGT_USER_NAME = 5;


    public static boolean validateUserName(String userName){

        return userName.length() <= MAX_LENGT_USER_NAME &&
                userName.length() >= MIN_LENGT_USER_NAME;
    }

    public static boolean validateUserMail(String userMail){

        return Patterns.EMAIL_ADDRESS.matcher(userMail).matches();
    }
}
