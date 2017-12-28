package com.projet.florianepeltier.mobileproject;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 28/12/17.
 */

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ShowAllViewHolder>  {
    public Context context;
    public PrenomDAO row;
    public ArrayList<Prenom> list;

    public ShowAllAdapter(Context context){
        this.context = context;
        row = new PrenomDAO(context);
        list = new ArrayList<Prenom>();
        row.open();
        list = row.showAll();
    }

    @Override
    public ShowAllViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cursor_raw, parent, false);
        return new ShowAllViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowAllViewHolder holder, int position) {
        Prenom prenom = list.get(position);
        holder.display(prenom);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ShowAllViewHolder extends RecyclerView.ViewHolder {
        private final TextView firstname;
        private final TextView requester;
        private Button show;

        public ShowAllViewHolder(View itemView) {
            super(itemView);

            firstname = ((TextView) itemView.findViewById(R.id.intitule));
            requester = ((TextView) itemView.findViewById(R.id.requester));
            show = ((Button) itemView.findViewById(R.id.show));

        }

        public void display(final Prenom prenom){
            firstname.setText(prenom.getIntitule());
            requester.setText(prenom.getRequester());

            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(context, ShowOneActivity.class);
                    myIntent.putExtra("id", prenom.getId());
                    context.startActivity(myIntent);
                }
            });
        }
    }
}
