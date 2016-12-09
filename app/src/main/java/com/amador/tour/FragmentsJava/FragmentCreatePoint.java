package com.amador.tour.FragmentsJava;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.amador.tour.HomeActivity;
import com.amador.tour.Interfaces.ICreatePoint;
import com.amador.tour.Presenter.CreatePointPresenter;
import com.amador.tour.R;

/**
 * Created by amador on 9/12/16.
 */

public class FragmentCreatePoint extends Fragment implements ICreatePoint.View {

    private EditText edtNamePoint;
    private FloatingActionButton floatingActionButton;
    private Spinner spinner;
    private SeekBar seekBar;
    private CreatePointPresenter presenter;
    private HomeActivity activity;
    private TextView textViewValue;
    private float value = 1;
    private int categoryIndex;
    private OnCallBackListener callBack;
    public interface OnCallBackListener{

        void onMessage(String msg);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    public FragmentCreatePoint(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_new_point, null);
        presenter = new CreatePointPresenter(FragmentCreatePoint.this, activity);
        textViewValue = (TextView)rootView.findViewById(R.id.textValue);
        edtNamePoint = (EditText)rootView.findViewById(R.id.edtNamePoint);
        spinner = (Spinner)rootView.findViewById(R.id.spiner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.category_name));
        spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               categoryIndex = i;
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {


           }
       });

        seekBar = (SeekBar)rootView.findViewById(R.id.seek);
        seekBar.setProgress(1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                if(i < 1){

                    textViewValue.setText("1");

                }else {

                    textViewValue.setText(String.valueOf(i));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                value = seekBar.getProgress();
            }
        });

        floatingActionButton = (FloatingActionButton)rootView.findViewById(R.id.flo);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.validateName(edtNamePoint.getText().toString());
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.activity = (HomeActivity)activity;
        this.callBack = (OnCallBackListener)activity;
    }

    @Override
    public void setMessage(String msg, boolean error) {

        if(error){

            edtNamePoint.setError(msg);

        }else {

            presenter.addPoint(edtNamePoint.getText().toString(), categoryIndex, seekBar.getProgress());
            callBack.onMessage(getString(R.string.create_new_point));
        }
    }
}
