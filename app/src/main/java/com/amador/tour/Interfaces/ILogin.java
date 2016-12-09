package com.amador.tour.Interfaces;

/**
 * Created by amador on 8/12/16.
 */

public interface ILogin {

    interface Presenter{

        boolean validateEmail(String email, int idResource);

        boolean validateUserName(String userName, int idResource);

        boolean hasUser();

    }

    interface View{

        void setMessageError(String msg, int idResource);
    }
}
