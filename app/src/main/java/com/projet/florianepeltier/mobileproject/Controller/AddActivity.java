package com.projet.florianepeltier.mobileproject.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.projet.florianepeltier.mobileproject.Model.PrenomDAO;
import com.projet.florianepeltier.mobileproject.R;

/**
 * Created by root on 28/12/17.
 */

public class AddActivity extends AppCompatActivity {
    private PrenomDAO row;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.add);
        setContentView(R.layout.add);

        Button valid = (Button) findViewById(R.id.valid);
        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText prenomInput = (EditText) findViewById(R.id.prenominput);
                EditText requesterInput = (EditText) findViewById(R.id.requesterinput);

                String prenom = prenomInput.getText().toString();
                String requester = requesterInput.getText().toString();

                row = new PrenomDAO(AddActivity.this);
                row.open();
                row.add(prenom, requester);

                Intent myIntent = new Intent(AddActivity.this, ShowAllActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
