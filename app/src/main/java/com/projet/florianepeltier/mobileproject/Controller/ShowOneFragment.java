package com.projet.florianepeltier.mobileproject.Controller;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.projet.florianepeltier.mobileproject.Model.Prenom;
import com.projet.florianepeltier.mobileproject.Model.PrenomDAO;
import com.projet.florianepeltier.mobileproject.R;

/**
 * Created by root on 29/12/17.
 */

public class ShowOneFragment extends Fragment {
    private long id;
    private PrenomDAO row;
    private Prenom prenom;
    private Button likes;
    private Button dislikes;
    private Button update;
    private Button delete;

    public static ShowOneFragment create(long id) {
        Bundle args = new Bundle();
        args.putLong("id", id);
        ShowOneFragment fragment = new ShowOneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getArguments().getLong("id");
        row = new PrenomDAO(getActivity());
        row.open();
        prenom = row.show(id);

        getActivity().setTitle(prenom.getIntitule());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.show_item, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView firstname = (TextView) view.findViewById(R.id.showPrenom);
        TextView requester = (TextView) view.findViewById(R.id.showRequester);
        likes = (Button) view.findViewById(R.id.like);
        dislikes = (Button) view.findViewById(R.id.dislike);

        firstname.setText(prenom.getIntitule());
        requester.setText(getResources().getString(R.string.requester) + " " + prenom.getRequester());
        likes.setText(String.valueOf(prenom.getLikes()));
        dislikes.setText(String.valueOf(prenom.getDislikes()));

        likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row.increaseLikes(prenom);
                likes.setText(String.valueOf(prenom.getLikes()));
            }
        });
        dislikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row.increaseDislikes(prenom);
                dislikes.setText(String.valueOf(prenom.getDislikes()));
            }
        });

        update = (Button) view.findViewById(R.id.edit);
        delete = (Button) view.findViewById(R.id.delete);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), UpdateActivity.class);
                myIntent.putExtra("id", prenom.getId());
                startActivity(myIntent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getActivity());
                }
                builder.setTitle(getResources().getString(R.string.delete))
                        .setMessage(getResources().getString(R.string.confirmation) + "\n\n" + prenom.getIntitule())
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                row.delete(id);
                                Intent myIntent = new Intent(view.getContext(), ShowAllActivity.class);
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
        });
    }
}
