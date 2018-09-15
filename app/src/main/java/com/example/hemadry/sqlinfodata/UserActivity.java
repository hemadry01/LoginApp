package com.example.hemadry.sqlinfodata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private TextView nameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nameTV = findViewById(R.id.textT);
        String nameIntent = getIntent().getStringExtra("EMAIL");
        nameTV.setText("Welcom"+nameIntent);
    }
}
