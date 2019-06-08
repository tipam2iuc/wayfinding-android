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

public class DomainePageAdapter extends RecyclerView.Adapter<DomainePageAdapter.ViewHolder> {
    Context context;
    List<Domaine> domaines;


    public DomainePageAdapter(Context context, List<Domaine> domaines) {
        this.context = context;
        this.domaines = domaines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView;
        layoutView = LayoutInflater.from(context)
                .inflate(R.layout.recycler_domaines_item,viewGroup,false );
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(domaines.get(i).getNom_domaine());
        if(domaines.get(i).getId_secteur() == 2){
            viewHolder.cardView.setBackgroundColor(context.getResources().getColor(R.color.domaine_sec_1 ));
        }else if(domaines.get(i).getId_secteur() == 1){
            viewHolder.cardView.setBackgroundColor(context.getResources().getColor(R.color.domaine_sec_2 ));
        }else {
            viewHolder.cardView.setBackgroundColor(context.getResources().getColor(R.color.domaine_sec_3 ));
        }
    }

    @Override
    public int getItemCount() {
        return domaines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_domaines_name);
            cardView = itemView.findViewById(R.id.cardView_color_left);
        }
    }
}
