package com.amador.tour.FragmentsJava;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amador.tour.HomeActivity;
import com.amador.tour.R;
import com.amador.tour.Reposity.InterestPointRepository;

/**
 * Created by amador on 9/12/16.
 */

public class FragmentsStadist extends Fragment {

    private TextView txvOneStar, txvTwoStar, txvThreeStar, txvFourStar, txvFiveStar,
    txvMuseum, txvBares, txvMonumentos, txvTeatres;
    private RelativeLayout rLStar, rlCount;
    private CheckBox chkValorations, chkStadist;
    private InterestPointRepository repository;
    private int[] countCategories, countStars;
    private HomeActivity activity;


    public FragmentsStadist(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_stadist, null);

        rLStar = (RelativeLayout)rootView.findViewById(R.id.relative);
        rlCount = (RelativeLayout)rootView.findViewById(R.id.relativeCategory);
        chkValorations = (CheckBox)rootView.findViewById(R.id.checkboxValorate);
        chkStadist = (CheckBox)rootView.findViewById(R.id.checkboxCategory);

        chkStadist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                rlCount.setVisibility(b?View.VISIBLE:View.GONE);
            }
        });

        chkValorations.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                rLStar.setVisibility(b?View.VISIBLE:View.GONE);
            }
        });

        repository = InterestPointRepository.getRepository(activity);
        countCategories = repository.getCountCategories();
        countStars = repository.getStarts();

        txvTeatres = (TextView)rootView.findViewById(R.id.txvTeatre);
        txvMonumentos = (TextView)rootView.findViewById(R.id.txvMonumens);
        txvMuseum = (TextView)rootView.findViewById(R.id.txvMuseum);
        txvBares = (TextView)rootView.findViewById(R.id.txvBar);

        txvOneStar = (TextView)rootView.findViewById(R.id.txvOneStar);
        txvTwoStar = (TextView)rootView.findViewById(R.id.txvTwoStar);
        txvThreeStar = (TextView)rootView.findViewById(R.id.txvThreeStar);
        txvFourStar = (TextView)rootView.findViewById(R.id.txvFourStar);
        txvFiveStar = (TextView)rootView.findViewById(R.id.txvFiveStar);

        txvOneStar.append(String.valueOf(countStars[0]));
        txvTwoStar.append(String.valueOf(countStars[1]));
        txvThreeStar.append(String.valueOf(countStars[2]));
        txvFourStar.append(String.valueOf(countStars[3]));
        txvFiveStar.append(String.valueOf(countStars[4]));

        txvMuseum.append(String.valueOf(countCategories[0]));
        txvTeatres.append(String.valueOf(countCategories[1]));
        txvBares.append(String.valueOf(countCategories[2]));
        txvMonumentos.append(String.valueOf(countCategories[3]));


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.activity = (HomeActivity)activity;
    }
}
