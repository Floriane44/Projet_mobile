package com.projet.florianepeltier.mobileproject.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.projet.florianepeltier.mobileproject.Model.Prenom;
import com.projet.florianepeltier.mobileproject.Model.PrenomDAO;
import com.projet.florianepeltier.mobileproject.R;

import java.util.ArrayList;

/**
 * Created by root on 28/12/17.
 */

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ShowAllViewHolder>  {
    public Context context;
    public PrenomDAO row;
    public ArrayList<Prenom> list;

    public interface PrenomLoader {
        void load(long id);
    }

    private static PrenomLoader _prenomLoader;

    public ShowAllAdapter(Context context, PrenomLoader prenomLoader){
        this.context = context;
        row = new PrenomDAO(context);
        list = new ArrayList<Prenom>();
        row.open();
        list = row.showAll();
        _prenomLoader = prenomLoader;
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

    public class ShowAllViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView firstname;
        private final TextView requester;
        private Button show;
        private Prenom prenom;

        public ShowAllViewHolder(View itemView) {
            super(itemView);

            firstname = ((TextView) itemView.findViewById(R.id.intitule));
            requester = ((TextView) itemView.findViewById(R.id.requester));
            show = ((Button) itemView.findViewById(R.id.show));

        }

        public void display(final Prenom prenom){
            this.prenom = prenom;
            firstname.setText(prenom.getIntitule());
            requester.setText(prenom.getRequester());

            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Intent myIntent = new Intent(context, ShowOneActivity.class);
                    myIntent.putExtra("id", prenom.getId());
                    context.startActivity(myIntent);*/
                    _prenomLoader.load(prenom.getId());
                }
            });

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            /*Log.e("click", "succeed");
            Intent myIntent = new Intent(context, ShowOneActivity.class);
            myIntent.putExtra("id", prenom.getId());
            context.startActivity(myIntent);*/
            _prenomLoader.load(prenom.getId());
        }
    }
}
