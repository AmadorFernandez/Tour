package com.amador.tour.Interfaces;

/**
 * Created by amador on 9/12/16.
 */

public interface ICreatePoint {

    interface Presenter{

        void validateName(String name);

        void addPoint(String name, int category, int rating);

    }

    interface View{

        void setMessage(String msg, boolean error);


    }
}
