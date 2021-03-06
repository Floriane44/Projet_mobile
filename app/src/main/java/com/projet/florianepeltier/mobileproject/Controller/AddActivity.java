package com.projet.florianepeltier.mobileproject.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.projet.florianepeltier.mobileproject.Model.Prenom;
import com.projet.florianepeltier.mobileproject.Model.PrenomDAO;
import com.projet.florianepeltier.mobileproject.R;

/**
 * Created by root on 28/12/17.
 */

public class AddActivity extends AppCompatActivity {
    private PrenomDAO row;
    private Prenom firstname;

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

                if (prenomInput.getText().toString().length() == 0){
                    prenomInput.setError(getResources().getString(R.string.requiredF));
                }
                else if(requesterInput.getText().toString().length() == 0) {
                    requesterInput.setError(getResources().getString(R.string.requiredY));
                }
                else {
                    String prenom = prenomInput.getText().toString();
                    String requester = requesterInput.getText().toString();

                    row = new PrenomDAO(AddActivity.this);
                    row.open();
                    firstname = row.add(prenom, requester);

                    Intent myIntent = new Intent(AddActivity.this, ShowOneActivity.class);
                    myIntent.putExtra("id", firstname.getId());
                    startActivity(myIntent);
                }
            }
        });
    }
}
