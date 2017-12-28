package com.projet.florianepeltier.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by root on 28/12/17.
 */

public class UpdateActivity extends AppCompatActivity {
    private PrenomDAO row;
    private Prenom prenom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        long id = getIntent().getLongExtra("id", 0);

        row = new PrenomDAO(this);
        row.open();
        prenom = row.show(id);

        setTitle(prenom.getIntitule());
        final EditText prenomInput = (EditText) findViewById(R.id.prenominput);
        final EditText requesterInput = (EditText) findViewById(R.id.requesterinput);

        prenomInput.setText(prenom.getIntitule());
        requesterInput.setText(prenom.getRequester());

        Button valid = (Button) findViewById(R.id.valid);
        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = prenomInput.getText().toString();
                String requester = requesterInput.getText().toString();
                prenom.setIntitule(firstname);
                prenom.setRequester(requester);
                row.update(prenom);
                Intent myIntent = new Intent(UpdateActivity.this, ShowAllActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
