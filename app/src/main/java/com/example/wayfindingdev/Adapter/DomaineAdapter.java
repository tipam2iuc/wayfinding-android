package com.example.wayfindingdev.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.wayfindingdev.Model.Domaine;
import com.example.wayfindingdev.R;

import java.util.List;

public class DomaineAdapter extends RecyclerView.Adapter<DomaineAdapter.ViewHolder> {

    private Context       context;
    private List<Domaine> domaines;

    public DomaineAdapter(Context context, List<Domaine> domaines) {
        this.context = context;
        this.domaines = domaines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View layoutView;
        layoutView = LayoutInflater.from(context)
                .inflate(R.layout.recycler_home_domaine_item,viewGroup,false );
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.name.setText(domaines.get(i).getNom_domaine());
    }

    @Override
    public int getItemCount() {
        return domaines.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        CardView cardView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_home_recycler_name_domaine);
            cardView = itemView.findViewById(R.id.cardView_domaine_item_home);
        }
    }
}
