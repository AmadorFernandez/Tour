package com.amador.tour;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amador.tour.Interfaces.ILogin;
import com.amador.tour.Preferences.Preferences;
import com.amador.tour.Presenter.LoginPresenter;
import com.amador.tour.User.User;

public class LoginActivity extends AppCompatActivity implements ILogin.View {


    private TextInputLayout tilEmail, tilUserName;
    private EditText edtMail, edtUserName;
    private Button btnOk;
    private LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(LoginActivity.this);
        init();
    }

    private void init() {



        if(presenter.hasUser()){

           lauchActivityHome();

        }else {

            loadWidgets();
        }
    }

    private void lauchActivityHome(){

        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    private void loadWidgets() {

        tilEmail = (TextInputLayout)findViewById(R.id.tilEmail);
        tilUserName = (TextInputLayout)findViewById(R.id.tilName);
        btnOk = (Button)findViewById(R.id.btnOk);
        edtMail = (EditText)findViewById(R.id.edtMail);
        edtUserName = (EditText)findViewById(R.id.edtName);
        presenter = new LoginPresenter(LoginActivity.this);

        //region TextChangedListeners
        edtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                tilUserName.setError(null);

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                tilEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //endregion

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               boolean validName = presenter.validateUserName(edtUserName.getText().toString(),
                       R.id.edtName);
                boolean validEmail = presenter.validateEmail(edtMail.getText().toString(),
                        R.id.edtMail);

                if(validName && validEmail){

                    Preferences.setUserName(edtUserName.getText().toString(), LoginActivity.this);
                    Preferences.setUserMail(edtMail.getText().toString(), LoginActivity.this);
                    lauchActivityHome();
                }
            }
        });
    }



    @Override
    public void setMessageError(String msg, int idResource) {


        switch (idResource){

            case R.id.edtName:
                tilUserName.setError(msg);
                break;
            case R.id.edtMail:
                tilEmail.setError(msg);
                break;
        }

    }
}
