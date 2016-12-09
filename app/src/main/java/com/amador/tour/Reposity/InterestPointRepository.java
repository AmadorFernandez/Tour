package com.amador.tour.Reposity;

import android.content.Context;
import com.amador.tour.Models.InterestPoint;
import com.amador.tour.R;
import java.util.ArrayList;

/**
 * Created by amador on 8/12/16.
 */

public class InterestPointRepository extends ArrayList<InterestPoint> {

    private static InterestPointRepository repository;
    private static Context context;

    public static InterestPointRepository getRepository(Context context){

        if(repository == null){

            repository = new InterestPointRepository(context);
        }

        return repository;
    }

    private InterestPointRepository(Context context1){

        context = context1;
        String[] categories = context.getResources().getStringArray(R.array.category_name);
        add(new InterestPoint(1, "La Mezquita", categories[3], R.drawable.monument, 4));
        add(new InterestPoint(2, "Bar PEPE", categories[2], R.drawable.icono_bar, 3));
        add(new InterestPoint(3, "La Paloma", categories[1], R.drawable.teatro, 3));
        add(new InterestPoint(4, "La Picaso", categories[0], R.drawable.museum, 4));
        add(new InterestPoint(1, "Puente de San Rafael", categories[3], R.drawable.monument, 4));
        add(new InterestPoint(2, "Bar La Pecha", categories[2], R.drawable.icono_bar, 3));
        add(new InterestPoint(3, "Teatro Cordoba", categories[1], R.drawable.teatro, 3));
        add(new InterestPoint(4, "Museo de no me acuerdo", categories[0], R.drawable.museum, 4));
        add(new InterestPoint(1, "Los Baños", categories[3], R.drawable.monument, 4));
        add(new InterestPoint(2, "Bar La Colonia", categories[2], R.drawable.icono_bar, 3));
        add(new InterestPoint(3, "Los Viquingos", categories[1], R.drawable.teatro, 3));
        add(new InterestPoint(4, "Museo de la otra punta", categories[0], R.drawable.museum, 4));
    }

    public int[] getStarts(){

        int[] stars = new int[5];
        InterestPoint tmp;

        for(int i = 0; i < size(); i++){

            tmp = get(i);

            switch ((int)tmp.getScore()){

                case 1:
                    stars[0]++;
                    break;
                case 2:
                    stars[1]++;
                    break;
                case 3:
                    stars[2]++;
                    break;
                case 4:
                    stars[3]++;
                    break;
                case 5:
                    stars[4]++;
                    break;
            }
        }

        return stars;
    }

    public boolean addPoint(InterestPoint point){

        boolean result = false;

        if(!contains(point)){

            add(point);
        }

        return result;
    }

    public int[] getCountCategories(){

        int[] categories = new int[4];
        String[] categoriesName = context.getResources().getStringArray(R.array.category_name);
        InterestPoint tmp;

        for(int i = 0; i < size(); i++){

            tmp = get(i);

           for (int j = 0; j < categoriesName.length; j++){

               if(tmp.getCategory().equalsIgnoreCase(categoriesName[j])){

                   categories[j]++;
               }
           }
        }


        return categories;
    }

    public boolean containsName(String name){

        for (int i = 0; i < size(); i++){

            if(get(i).getName().equalsIgnoreCase(name)){

                return true;
            }
        }

        return false;
    }

    public void removePoint(InterestPoint point){

        remove(point);
    }

    public void updatePoint(InterestPoint oldPoint, InterestPoint newPoint){

        int pos = indexOf(oldPoint);
        remove(oldPoint);
        add(pos, newPoint);
    }



}
