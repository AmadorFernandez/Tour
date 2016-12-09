package com.amador.tour;

import android.content.Intent;
import android.app.FragmentTransaction;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.amador.tour.FragmentsJava.FragmentCreatePoint;
import com.amador.tour.FragmentsJava.FragmentList;
import com.amador.tour.FragmentsJava.FragmentsStadist;
import com.amador.tour.FragmentsJava.FragmetMenu;

public class HomeActivity extends AppCompatActivity implements FragmetMenu.OnCommunicationListener, FragmentCreatePoint.OnCallBackListener
{

    private FragmetMenu fragmetMenu;
    private FragmentList fragmentList;
    private FragmentsStadist fragmentsStadist;
    private FragmentCreatePoint fragmentCreatePoint;
    private static final String TAG_FRAGMENT_LIST = "list";
    private static final String TAG_FRAGMENT_NEW_POINT = "point";
    private static final String TAG_FRAGMENT_STADIST = "stadist";
    private CoordinatorLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        parent = (CoordinatorLayout) findViewById(R.id.activity_home);
        fragmentList = (FragmentList)getFragmentManager().findFragmentByTag(TAG_FRAGMENT_LIST);
        fragmentCreatePoint = (FragmentCreatePoint) getFragmentManager().findFragmentByTag(TAG_FRAGMENT_NEW_POINT);
        fragmentsStadist = (FragmentsStadist) getFragmentManager().findFragmentByTag(TAG_FRAGMENT_STADIST);

        if(savedInstanceState == null){

            fragmetMenu = new FragmetMenu();
            getFragmentManager().beginTransaction().add(R.id.activity_home, fragmetMenu).commit();

        }

        if(fragmentList == null){

            fragmentList = new FragmentList();
        }

        if(fragmentCreatePoint == null){

            fragmentCreatePoint = new FragmentCreatePoint();
        }

        if(fragmentsStadist == null){

            fragmentsStadist = new FragmentsStadist();
        }


    }

    @Override
    public void onExit() {

        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void onAbout() {

        startActivity(new Intent(HomeActivity.this, AboutActivity.class));
    }

    @Override
    public void onList() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.activity_home, fragmentList, TAG_FRAGMENT_LIST);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void onCreatePoint() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.activity_home, fragmentCreatePoint, TAG_FRAGMENT_NEW_POINT);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void onStadist() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.activity_home, fragmentsStadist, TAG_FRAGMENT_STADIST);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onMessage(String msg) {

        Snackbar.make(parent, msg, Snackbar.LENGTH_LONG).show();
    }
}
