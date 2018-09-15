package com.example.hemadry.sqlinfodata;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

public class RegistationActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = RegistationActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayout;
    private TextInputLayout textMainputLayout;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditText;
    private TextInputEditText textMatching;

    private AppCompatButton appCompatButton ;
    private AppCompatTextView appCompatTextView;

    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);

        initView();
        initListeners();
        initObject();
    }



    private void initView() {

        nestedScrollView = findViewById(R.id.nest);

        textInputLayoutEmail = findViewById(R.id.inputlayoutEmail);

        textInputLayoutPassword = findViewById(R.id.inputlayoutPassword);
        textInputLayout = findViewById(R.id.inputlayoutName);
        textMainputLayout =findViewById(R.id.inputlayoutMatPasswords);


        textInputEditTextEmail = findViewById(R.id.textLayoutEmail);
        textInputEditTextPassword = findViewById(R.id.textInputPass);
        textInputEditText = findViewById(R.id.textLayoutName);
        textMatching = findViewById(R.id.textInputMatPasswords);


        appCompatButton = findViewById(R.id.Appcompatlogin);
        appCompatTextView = findViewById(R.id.accountT);
    }
    private void initListeners() {
        appCompatButton.setOnClickListener(this);
        appCompatTextView.setOnClickListener(this);
    }
    private void initObject() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Appcompatlogin:
                postDataToSQLite();
                break;
            case R.id.accountT:
                finish();
                break;
        }
    }

    private void postDataToSQLite() {
        if (!inputValidation.inputEditTextFild(textInputEditText,textInputLayout,getString(R.string.error_massage_name)))
        {
            return;
        }
        if (!inputValidation.inputEditTextFild(textInputEditTextEmail,textInputLayoutEmail,getString(R.string.error_massage_name)))
        {
            return;
        }
        if (!inputValidation.inputEditTextFild(textInputEditTextPassword,textInputLayoutPassword,getString(R.string.error_message_password)))
        {
            return;
        }
        if (!inputValidation.inputEditTextFild(textMatching,textMainputLayout,getString(R.string.error_password_match)))
        {
            return;
        }
        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim(),textInputEditTextPassword.getText().toString().trim()))
        {
            user.setName(textInputEditText.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);
            Snackbar.make(nestedScrollView,getString(R.string.success_message),Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
        }
        else
        {
            Snackbar.make(nestedScrollView,getString(R.string.error_email_exit),Snackbar.LENGTH_LONG).show();
        }




    }

    private void emptyInputEditText() {
        textInputEditText.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textMatching.setText(null);
    }
}
