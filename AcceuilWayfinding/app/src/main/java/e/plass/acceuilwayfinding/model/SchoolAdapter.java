package e.plass.acceuilwayfinding.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import e.plass.acceuilwayfinding.R;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchooViewHolder> {
    private Context context;
    private List<Ecole> data;

    public SchoolAdapter(Context context, List<Ecole> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SchooViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater
                .from(context)
                .inflate(R.layout.recyclerview_school_item,viewGroup,false);
        return new SchooViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull SchooViewHolder schooViewHolder, int i) {

        schooViewHolder.img
                .setAnimation(AnimationUtils
                        .loadAnimation(context,R.anim.fade_transition_animation));
        schooViewHolder.card
                .setAnimation(AnimationUtils
                        .loadAnimation(context,R.anim.fade_scale_animation));


        String description = data.get(i).getDescrition().trim();
        try{
            description = description.substring(0,130)+"...";
            schooViewHolder.description.setText(description);
        }catch (Exception ex){
            schooViewHolder.description.setText(data.get(i).getDescrition().trim()+"...");
        }
        schooViewHolder.name.setText(data.get(i).getName());
        schooViewHolder.ville.setText(data.get(i).getVille());
        schooViewHolder.note.setText(data.get(i).getNote()+"");
        try {
            int id = context.getResources().getIdentifier(data.get(i).getImage(),"drawable",context.getPackageName());
            Glide.with(context)
                    .load(id)
                    .into(schooViewHolder.img);
        }catch (Exception ex){
            Log.d("imgFound", ex.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }






    public class SchooViewHolder extends RecyclerView.ViewHolder{


        private TextView name,description,ville,note;
        private ImageView      img;
        private     RelativeLayout card;


        public SchooViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_descrption);
            ville = itemView.findViewById(R.id.tv_date);
            img = itemView.findViewById(R.id.img_school);
            card = itemView.findViewById(R.id.rly_card);
            note = itemView.findViewById(R.id.tv_note);
        }
    }
}
