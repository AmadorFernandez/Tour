package com.amador.tour.FragmentsJava;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.amador.tour.Adapters.ListPointAdaptert;
import com.amador.tour.HomeActivity;
import com.amador.tour.Models.InterestPoint;
import com.amador.tour.R;
import com.amador.tour.Reposity.InterestPointRepository;

import java.util.ArrayList;

/**
 * Created by amador on 9/12/16.
 */

public class FragmentList extends Fragment {

    private HomeActivity activity;
    private ListView listView;
    private ListPointAdaptert adapter;
    private InterestPointRepository repository;
    private static final String RESTORE_KEY_ADPTER = "adapter";

    public FragmentList(){


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragmet_list, null);
        repository = InterestPointRepository.getRepository(activity);
        listView = (ListView)rootView.findViewById(R.id.list);
        adapter = new ListPointAdaptert(activity, repository);
        if(savedInstanceState != null) {

            adapter.restoreState(savedInstanceState.<InterestPoint>getParcelableArrayList(RESTORE_KEY_ADPTER));

        }

        listView.setAdapter(adapter);

        return rootView;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        ArrayList<InterestPoint> aux = new ArrayList<InterestPoint>();

        for(int i = 0; i < adapter.getCount(); i++){

            aux.add(adapter.getItem(i));
        }

        outState.putParcelableArrayList(RESTORE_KEY_ADPTER, aux);

        super.onSaveInstanceState(outState);
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            this.activity = (HomeActivity)activity;
            setHasOptionsMenu(true);

        }catch (ClassCastException e){

            getFragmentManager().beginTransaction().add(R.id.activity_home, new FragmetMenu()).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_sort_category:
                adapter.sortBy(ListPointAdaptert.SORT_CATEGORY);
                break;
            case R.id.action_sort_name:
                adapter.sortBy(ListPointAdaptert.SORT_NAME);
                break;
            case R.id.action_sort_rating:
                adapter.sortBy(ListPointAdaptert.SORT_RATING);
                break;
            case R.id.action_filter:
                showDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle(getString(R.string.selction_msg));
        final String[] options = getResources().getStringArray(R.array.category_filter);
        builder.setSingleChoiceItems(options, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

                if(i < options.length - 1){

                adapter.filterByCategory(options[i]);

                }else {

                    adapter = new ListPointAdaptert(activity, repository);
                    listView.setAdapter(adapter);
                }

            }
        }).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_list, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }
}
