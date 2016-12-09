package com.amador.tour.Presenter;

import android.content.Context;
import android.widget.RelativeLayout;

import com.amador.tour.FragmentsJava.FragmentCreatePoint;
import com.amador.tour.FragmentsJava.FragmetMenu;
import com.amador.tour.Interfaces.IMenu;
import com.amador.tour.Preferences.Preferences;

/**
 * Created by amador on 8/12/16.
 */

public class MenuPresenter implements IMenu.Presenter {

    private Context context;
    private IMenu.View view;



    public MenuPresenter(IMenu.View view, Context context){

        this.context = context;
        this.view = view;
    }

    @Override
    public void removeDataUser() {

        Preferences.setUserName(null, context);
        Preferences.setUserMail(null, context);
        view.exit();
    }


}
