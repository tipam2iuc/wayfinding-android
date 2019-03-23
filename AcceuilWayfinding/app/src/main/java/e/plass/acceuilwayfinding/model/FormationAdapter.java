package e.plass.acceuilwayfinding.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import e.plass.acceuilwayfinding.R;

public class FormationAdapter extends RecyclerView.Adapter<FormationAdapter.FormationViewHolder> {
    private Context context;
    ArrayList<Formation> formations;
    ArrayList<Formation> formationsSearch;
    final int DESC = 100;

    public FormationAdapter(Context context, ArrayList<Formation> formations) {
        this.context = context;
        this.formations = formations;
    }


    @NonNull
    @Override
    public FormationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_formation_item,viewGroup,false);
        FormationViewHolder f = new FormationViewHolder(view);
        return f;
    }
    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(Formation formation);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @Override
    public void onBindViewHolder(@NonNull FormationViewHolder formationAdapter, int i) {
        Formation curren = formations.get(i);
        String description = curren.getDescripetion().substring(0,DESC)+"...";
        try {
            int id = context.getResources().getIdentifier(curren.getImage(),"drawable",context.getPackageName());
            Glide.with(context)
                    .load(id)
                    .into(formationAdapter.imageView);
        }catch (Exception ex){
            Log.d("imgFound", ex.getMessage());
        }
        formationAdapter.name.setText(curren.getName());
        formationAdapter.description.setText(description.trim());
        formationAdapter.note.setText(""+curren.getNotes());
    }

    @Override
    public int getItemCount() {
        return formations.size();
    }



    public class FormationViewHolder extends RecyclerView.ViewHolder{
        private ImageView        imageView;
        private TextView         name;
        private TextView         description;
        private TextView         note;
        private ConstraintLayout content;
        private CardView         cardview;
        private ConstraintLayout contentDesc;
        private Button button;
        private boolean isMore = false;

        public FormationViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview_recycler_item_formation1);
            name = itemView.findViewById(R.id.textView_recyclerview_formation_name);
            description = itemView.findViewById(R.id.textView_recyclerview_formation_description);
            note = itemView.findViewById(R.id.textView_recyclerview_formation_note);
            content = itemView.findViewById(R.id.recyclerview_formation);
            cardview = itemView.findViewById(R.id.cardView_formation);
            contentDesc = itemView.findViewById(R.id.constraintLayout_formation_desc);
            button = itemView.findViewById(R.id.button_formation_more);

            itemView.setOnClickListener(v-> {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(formations.get(position));
                    }
                }
            );
            button.setOnClickListener(v-> {
                if(!isMore){
                    description.setText(formations.get(getAdapterPosition()).getDescripetion());
                    isMore = true;
                    button.setText(R.string.moin);
                }else{
                    description.setText(formations
                            .get(getAdapterPosition())
                            .getDescripetion()
                            .trim().substring(0,DESC)+"...");
                    button.setText(R.string.more);
                    isMore = false;
                }
                }
            );
        }
    }
}
