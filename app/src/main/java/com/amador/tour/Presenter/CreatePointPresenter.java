package com.amador.tour.Presenter;

import android.content.Context;

import com.amador.tour.Interfaces.ICreatePoint;
import com.amador.tour.Models.InterestPoint;
import com.amador.tour.R;
import com.amador.tour.Reposity.InterestPointRepository;
import com.amador.tour.Utils.CategoryConstans;

/**
 * Created by amador on 9/12/16.
 */

public class CreatePointPresenter implements ICreatePoint.Presenter {

    private InterestPointRepository repository;
    private ICreatePoint.View view;
    private Context context;

    public CreatePointPresenter(ICreatePoint.View view, Context context){

        this.view = view;
        this.repository = InterestPointRepository.getRepository(context);
        this.context = context;
    }

    @Override
    public void validateName(String name) {

        boolean containsName = repository.containsName(name);

        if(name.isEmpty()){

            view.setMessage(context.getString(R.string.name_empty), true);

        }else if(containsName){

            view.setMessage(context.getString(R.string.contains_name), containsName);

        }else {

            view.setMessage(context.getString(R.string.add_new_point), containsName);
        }



    }

    @Override
    public void addPoint(String name, int category, int rating) {

        String[] categories = context.getResources().getStringArray(R.array.category_name);
        int categoryIconId = CategoryConstans.ERROR_ICON;

        switch (category){

            case 0:
                categoryIconId = CategoryConstans.MONUMENT_ICON;
                break;
            case 1:
                categoryIconId = CategoryConstans.TEATRO_ICON;
                break;

            case 2:
                categoryIconId = CategoryConstans.BAR_ICON;
                break;
            case 3:
                categoryIconId = CategoryConstans.MUSEUM_ICON;
                break;
        }

        repository.add(new InterestPoint(repository.size()+1, name, categories[category], categoryIconId, rating));

    }
}
