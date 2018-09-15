package com.example.hemadry.sqlinfodata;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = MainActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButton ;
    private AppCompatTextView appCompatTextView;

    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListeners();
        initObject();
    }




    private void initView() {

        nestedScrollView = findViewById(R.id.nest);

        textInputLayoutEmail = findViewById(R.id.inputlayoutEmail);

        textInputLayoutPassword = findViewById(R.id.inputlayoutPassword);

        textInputEditTextEmail = findViewById(R.id.textLayoutEmail);
        textInputEditTextPassword = findViewById(R.id.textInputPass);

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
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.Appcompatlogin :
                verifyFromSQLite();
                break;

            case R.id.accountT:
                Intent intent = new Intent(MainActivity.this,RegistationActivity.class);
                startActivity(intent);
                break;

        }

    }

    private void verifyFromSQLite() {

        if (!inputValidation.inputEditTextEmail(textInputEditTextEmail,textInputLayoutEmail,getString(R.string.error_message_email)))
        {
            return;
        }
        if (!inputValidation.inputEditTextEmail(textInputEditTextEmail,textInputLayoutEmail,getString(R.string.error_message_email)))
        {
            return;
        }
        if (!inputValidation.inputEditTextFild(textInputEditTextPassword,textInputLayoutPassword,getString(R.string.error_message_password)))
        {
            return;
        }
        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim(),textInputEditTextPassword.getText().toString().trim()))
        {
            Intent accountIntent = new Intent(MainActivity.this,UserActivity.class);
            accountIntent.putExtra("EMAIL",textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountIntent);

        }
        else
        {
            Snackbar.make(nestedScrollView,getString(R.string.error_valid_email_password),Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}

