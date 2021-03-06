package com.projet.florianepeltier.mobileproject.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.projet.florianepeltier.mobileproject.Model.Prenom;
import com.projet.florianepeltier.mobileproject.Model.PrenomDAO;

/**
 * Created by root on 28/12/17.
 */

public class ShowOneActivity extends AppCompatActivity {
    private PrenomDAO row;
    private Prenom prenom;
    private Button likes;
    private Button dislikes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long id = getIntent().getLongExtra("id", 0);
        ShowOneFragment fragment = ShowOneFragment.create(id);

        getFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, fragment)
                .commit()
        ;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ShowOneActivity.this, ShowAllActivity.class));
    }
}
