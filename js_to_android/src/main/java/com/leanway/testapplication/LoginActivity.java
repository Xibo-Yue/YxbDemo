package com.leanway.testapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView mUsername;
    private TextView mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        assignViews();
        Bundle bundle = getIntent().getExtras();
        mUsername.setText(bundle.getString("username"));
        mPassword.setText(bundle.getString("password"));
    }



    private void assignViews() {
        mUsername = (TextView) findViewById(R.id.username);
        mPassword = (TextView) findViewById(R.id.password);
    }


}
