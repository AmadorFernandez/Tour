package com.amador.tour.User;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by amador on 8/12/16.
 */

public class User {

    private String name;
    private String mail;
    private static User instance;

    private User(){

        this.name = "";
        this.mail = "";
    }

    public static User getInstance(){

        if(instance == null){

            instance = new User();
        }

        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
