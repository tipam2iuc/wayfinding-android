package com.example.wayfindingdev.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.wayfindingdev.Model.School;
import com.example.wayfindingdev.R;

import java.util.List;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.ViewHolder> {

    private Context context;
    private List<School> schools;

    public SchoolAdapter(Context context, List<School> schools) {
        this.context = context;
        this.schools = schools;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View layoutView;
        layoutView = LayoutInflater.from(context)
                .inflate(R.layout.recycler_home_school_item,viewGroup,false );
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context)
                .load(schools.get(i).getChemin_logo())
                .fallback(R.drawable.icons_university_48px)
                .error(R.drawable.icons_university_48px)
                .into(viewHolder.logo);
        viewHolder.nom.setText(schools.get(i).getNom_ecole());
        String location = schools.get(i).getQuartier() + " " + schools.get(i).getVille() + "- Cameroun";
        viewHolder.localisation.setText(location);
        viewHolder.type.setText(schools.get(i).getIntitule_type_ecole());

        int note = (int) (schools.get(i).getNote() * 300f / 20f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            viewHolder.progressBar.setProgress(note,true );
        }else{
            viewHolder.progressBar.setProgress(note);
        }

    }

    @Override
    public int getItemCount() {
        return schools.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView nom,localisation,type;
        ProgressBar progressBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.imageView_recycler_school_item);
            nom = itemView.findViewById(R.id.textView_recyclerview_name_item);
            localisation = itemView.findViewById(R.id.textView_recyclerview_location_school_item);
            type = itemView.findViewById(R.id.textView_recyclerview_type_school_item);
            progressBar = itemView.findViewById(R.id.progressBar_item_school);


        }
    }
}