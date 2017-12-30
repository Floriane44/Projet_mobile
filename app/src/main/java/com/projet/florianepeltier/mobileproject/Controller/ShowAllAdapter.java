package com.projet.florianepeltier.mobileproject.Controller;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.projet.florianepeltier.mobileproject.Model.Prenom;
import com.projet.florianepeltier.mobileproject.R;

import java.util.Comparator;
import java.util.List;

/**
 * Created by root on 28/12/17.
 */

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ShowAllViewHolder>  {
    private Context context;

    private final SortedList<Prenom> mSortedList = new SortedList<>(Prenom.class, new SortedList.Callback<Prenom>() {

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public int compare(Prenom o1, Prenom o2) {
            return mComparator.compare(o1, o2);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(Prenom oldItem, Prenom newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areItemsTheSame(Prenom item1, Prenom item2) {
            return item1.getId() == item2.getId();
        }
    });

    private final Comparator<Prenom> mComparator;

    public interface PrenomLoader {
        void load(long id);
    }

    private static PrenomLoader _prenomLoader;

    ShowAllAdapter(Context context, PrenomLoader prenomLoader, Comparator<Prenom> comparator){
        this.context = context;
        LayoutInflater mInflater = LayoutInflater.from(context);
        mComparator = comparator;
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
        Prenom prenom = mSortedList.get(position);
        holder.display(prenom);
    }

    @Override
    public int getItemCount() {
        return mSortedList.size();
    }

    public void add(Prenom prenom) {
        mSortedList.add(prenom);
    }

    public void add(List<Prenom> prenoms) {
        mSortedList.addAll(prenoms);
    }

    void replaceAll(List<Prenom> prenoms) {
        mSortedList.beginBatchedUpdates();
        for (int i = mSortedList.size() - 1; i >= 0; i--) {
            final Prenom prenom = mSortedList.get(i);
            if (!prenoms.contains(prenom)) {
                mSortedList.remove(prenom);
            }
        }
        mSortedList.addAll(prenoms);
        mSortedList.endBatchedUpdates();
    }

    public class ShowAllViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView firstname;
        private final TextView requester;
        private Button show;
        private Prenom prenom;

        ShowAllViewHolder(View itemView) {
            super(itemView);

            firstname = ((TextView) itemView.findViewById(R.id.intitule));
            requester = ((TextView) itemView.findViewById(R.id.requester));
            show = ((Button) itemView.findViewById(R.id.show));

        }

        void display(final Prenom prenom){
            this.prenom = prenom;
            firstname.setText(prenom.getIntitule());
            String text = context.getResources().getString(R.string.requester) + " " + prenom.getRequester();
            requester.setText(text);

            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _prenomLoader.load(prenom.getId());
                }
            });

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            _prenomLoader.load(prenom.getId());
        }
    }
}
