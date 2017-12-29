package com.projet.florianepeltier.mobileproject.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.projet.florianepeltier.mobileproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(R.layout.activity_main);
    }

    public void startOnClick(View view){
        Intent myIntent = new Intent(MainActivity.this, ShowAllActivity.class);
        startActivity(myIntent);
    }

}