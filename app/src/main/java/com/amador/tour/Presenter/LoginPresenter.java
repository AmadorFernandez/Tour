package com.amador.tour.Presenter;

import android.content.Context;
import android.view.View;

import com.amador.tour.Interfaces.ILogin;
import com.amador.tour.LoginActivity;
import com.amador.tour.Preferences.Preferences;
import com.amador.tour.R;
import com.amador.tour.User.User;
import com.amador.tour.Utils.ValidateData;

/**
 * Created by amador on 8/12/16.
 */

public class LoginPresenter implements ILogin.Presenter {


    private ILogin.View view;
    private Context context;
    private User user;


    public LoginPresenter(ILogin.View view){

        this.view = view;
        this.context = (Context)view;
    }

    @Override
    public boolean validateEmail(String email, int idResource) {

        boolean result = true;

        if(!ValidateData.validateUserMail(email)){

            result = false;
            view.setMessageError(context.getString(R.string.invalid_email), idResource);
        }

        return result;
    }

    @Override
    public boolean validateUserName(String userName, int idResource) {

        boolean result = true;

        if(!ValidateData.validateUserName(userName)){

            result = false;
            view.setMessageError(context.getString(R.string.invalid_user_name), idResource);
        }

        return result;
    }

    @Override
    public boolean hasUser() {

        user = User.getInstance();
        user.setName(Preferences.getUserName(this.context));
        user.setMail(Preferences.getUserMail(this.context));
        return user.getName() != null && user.getMail() != null;
    }
}
