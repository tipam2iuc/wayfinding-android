package e.plass.acceuilwayfinding.model.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Objects;

import e.plass.acceuilwayfinding.DetailFormationActivity;
import e.plass.acceuilwayfinding.R;
import e.plass.acceuilwayfinding.model.Util;
import e.plass.acceuilwayfinding.model.t_domaine;

public class FormationAdapter extends RecyclerView.Adapter<FormationAdapter.FormationViewHolder> {
    private Context              context;
    private ArrayList<t_domaine> tdomaines;
    private final int            DESC = 100;
    private       RequestOptions options ;

    public FormationAdapter(Context context, ArrayList<t_domaine> tdomaines) {
        this.context = context;
        this.tdomaines = tdomaines;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_30)
                .error(R.drawable.ic_30);
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
        void onItemClick(t_domaine tdomaine);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @Override
    public void onBindViewHolder(@NonNull FormationViewHolder formationAdapter, int i) {
        t_domaine curren      = tdomaines.get(i);
        String    description = (curren.getDescripetion().length()>=100?curren.getDescripetion().substring(0,DESC)+"...":curren.getDescripetion());
//        try {
//            int id = context.getResources().getIdentifier(curren.getImage(),"drawable",context.getPackageName());
//            Glide.with(context)
//                    .load(id)
//                    .into(formationAdapter.imageView);
//        }catch (Exception ex){
//            Log.d("imgFound", ex.getMessage());
//        }
        Glide.with(context).load("https://vitrine123.000webhostapp.com/way_pic/imm/"+curren.getId()+".jpg").apply(options).into(formationAdapter.imageView);

        formationAdapter.name.setText(curren.getName());
        formationAdapter.description.setText(description.trim());
        formationAdapter.note.setText(String.format("%s", curren.getNotes()));
    }

    @Override
    public int getItemCount() {
        return tdomaines.size();
    }



    class FormationViewHolder extends RecyclerView.ViewHolder{
        private ImageView        imageView;
        private TextView         name;
        private TextView         description;
        private TextView         note;
        private ConstraintLayout content;
        private CardView         cardview;
        private ConstraintLayout contentDesc;
        private Button button;
        private boolean isMore = false;

        FormationViewHolder(@NonNull final View itemView) {
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
                        listener.onItemClick(tdomaines.get(position));
                    }
                }
            );
            button.setOnClickListener(v-> {
                if(!isMore){
                    description.setText(tdomaines.get(getAdapterPosition()).getDescripetion());
                    isMore = true;
                    button.setText(R.string.moin);
                }else{
                    description.setText(String.format("%s...", tdomaines
                            .get(getAdapterPosition())
                            .getDescripetion()
                            .trim().substring(0, DESC)));
                    button.setText(R.string.more);
                    isMore = false;
                }
                }
            );
            imageView.setOnClickListener((v)->{
                Intent i = new Intent(context, DetailFormationActivity.class);
                Util.setCurrentTdomaine(tdomaines.get(getAdapterPosition()));
                ActivityOptionsCompat option = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity) context,imageView, Objects.requireNonNull(ViewCompat.getTransitionName(imageView)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    context.startActivity(i,option.toBundle());
                }else{
                    context.startActivity(i);
                }
            });
        }
    }
}
