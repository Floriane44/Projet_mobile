package com.projet.florianepeltier.mobileproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

/**
 * Created by root on 28/12/17.
 */

public class ShowOneActivity extends AppCompatActivity {
    private long id;
    private PrenomDAO row;
    private Prenom prenom;
    private Button likes;
    private Button dislikes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_item);
        id = getIntent().getLongExtra("id", 0);

        row = new PrenomDAO(this);
        row.open();
        prenom = row.show(id);
        Log.e("show", prenom.getIntitule());
        Log.e("show", String.valueOf(prenom.getLikes()));
        setTitle(prenom.getIntitule());

        TextView firstname = (TextView) findViewById(R.id.showPrenom);
        TextView requester = (TextView) findViewById(R.id.showRequester);
        likes = (Button) findViewById(R.id.like);
        dislikes = (Button) findViewById(R.id.dislike);

        firstname.setText(prenom.getIntitule());
        requester.setText(getResources().getString(R.string.requester) + " " + prenom.getRequester());
        likes.setText(String.valueOf(prenom.getLikes()));
        dislikes.setText(String.valueOf(prenom.getDislikes()));
    }

    public void likeOnClick(View view){
        row.increaseLikes(prenom);
        likes.setText(String.valueOf(prenom.getLikes()));
    }

    public void dislikeOnClick(View view){
        row.increaseDislikes(prenom);
        dislikes.setText(String.valueOf(prenom.getDislikes()));
    }

    public void editOnClick(View view){
        Intent myIntent = new Intent(ShowOneActivity.this, UpdateActivity.class);
        myIntent.putExtra("id", prenom.getId());
        startActivity(myIntent);
    }

    public void deleteOnClick(View view){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(getResources().getString(R.string.delete))
                .setMessage(getResources().getString(R.string.confirmation) + "\n\n" + prenom.getIntitule())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        row.delete(id);
                        Intent myIntent = new Intent(ShowOneActivity.this, ShowAllActivity.class);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
