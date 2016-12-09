package com.amador.tour.FragmentsJava;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amador.tour.HomeActivity;
import com.amador.tour.Interfaces.IMenu;
import com.amador.tour.Presenter.MenuPresenter;
import com.amador.tour.R;

/**
 * Created by amador on 8/12/16.
 */

public class FragmetMenu extends Fragment implements IMenu.View {


    private Button btnExit, btnNewPoit, btnList, btnAbout, btnStadist;
    private MenuPresenter presenter;
    private HomeActivity activity;
    private OnCommunicationListener callBack;

    public interface OnCommunicationListener {

        void onExit();

        void onAbout();

        void onList();

        void onCreatePoint();

        void onStadist();

    }


   public FragmetMenu(){


   }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_menu, null);

        presenter = new MenuPresenter(FragmetMenu.this, activity);
        //region btnExitOnclick
        btnExit = (Button)rootView.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(getString(R.string.alert));
                builder.setIcon(R.drawable.ic_action_alert);
                builder.setMessage(getString(R.string.alert_exit_msg));

                builder.setPositiveButton(getString(R.string.acept), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        presenter.removeDataUser();

                    }
                }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();
                    }
                }).show();

            }
        });
        //endregion

        btnAbout = (Button)rootView.findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(callBack != null){

                   callBack.onAbout();
               }
            }
        });

        btnStadist = (Button)rootView.findViewById(R.id.btnStadist);
        btnStadist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(callBack != null){

                    callBack.onStadist();
                }
            }
        });

        btnList = (Button)rootView.findViewById(R.id.btnList);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(callBack != null){

                    callBack.onList();
                }
            }
        });

        btnNewPoit = (Button)rootView.findViewById(R.id.btnNewPoint);
        btnNewPoit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(callBack != null){

                    callBack.onCreatePoint();
                }
            }
        });

        return rootView;
    }




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            this.activity = (HomeActivity)activity;
            this.callBack = (OnCommunicationListener)activity;

        }catch (ClassCastException e){

            throw new ClassCastException(e.getMessage()+getResources().getString(R.string.not_interface_implents));
        }
    }

    @Override
    public void exit() {

        if(callBack != null){

            callBack.onExit();
        }

    }
}
