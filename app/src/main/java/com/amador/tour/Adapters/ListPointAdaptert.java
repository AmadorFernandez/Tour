package com.amador.tour.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amador.tour.Models.InterestPoint;
import com.amador.tour.R;

import java.util.ArrayList;

/**
 * Created by amador on 9/12/16.
 */

public class ListPointAdaptert extends ArrayAdapter<InterestPoint> {

    private Context context;
    private ArrayList<InterestPoint> localList;
    public static final int SORT_NAME = 0;
    public static final int SORT_CATEGORY = 1;
    public static final int SORT_RATING = 2;
    public static final int ONLY_CATEGORY = 3;

    public ListPointAdaptert(Context context, ArrayList<InterestPoint> interestPoints) {
        super(context, R.layout.item_list, new ArrayList<InterestPoint>(interestPoints));

        this.localList = interestPoints;
        this.context = context;
    }


    public void restoreState(ArrayList<InterestPoint> state){

        clear();
        addAll(state);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rootView = convertView;
        InterestPointHolder holder;

        if(rootView == null){

            rootView = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            holder = new InterestPointHolder();

            holder.imvIcon = (ImageView)rootView.findViewById(R.id.imvInteresPointItem);
            holder.txvNamePoint = (TextView)rootView.findViewById(R.id.txvInterestPointItem);
            holder.rtbRating = (RatingBar)rootView.findViewById(R.id.iconRating);

            rootView.setTag(holder);

        }else {

            holder = (InterestPointHolder)rootView.getTag();
        }

        holder.imvIcon.setImageResource(getItem(position).getIcon());
        holder.txvNamePoint.setText(getItem(position).getName());
        holder.rtbRating.setRating((getItem(position).getScore()));
        return rootView;
    }

    public void sortBy(int typeOrder){


        switch (typeOrder){

            case SORT_NAME:
                sort(InterestPoint.ORDERBY_NAME);
                break;
            case SORT_CATEGORY:
                clear();
                addAll(localList);
                sort(InterestPoint.ORDERBY_CATEGORY);
                break;
            case SORT_RATING:
                sort(InterestPoint.ORDERBY_SCORE);
                break;
        }

    }

    public void filterByCategory(String category){

        clear();

        for(InterestPoint tmp: localList){

            if(tmp.getCategory().equalsIgnoreCase(category)){

                add(tmp);
            }
        }
    }

    class InterestPointHolder{

        ImageView imvIcon;
        TextView txvNamePoint;
        RatingBar rtbRating;
    }
}
