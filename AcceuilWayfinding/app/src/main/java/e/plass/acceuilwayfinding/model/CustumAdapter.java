package e.plass.acceuilwayfinding.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Calendar;

import e.plass.acceuilwayfinding.R;

public class CustumAdapter extends RecyclerView.Adapter<CustumAdapter.ViewHolder> {
    private Context          context;
    private ArrayList<Ecole> ecoles;

    /// constructeur

    public CustumAdapter(Context context, ArrayList<Ecole> ecoles) {
        this.context = context;
        this.ecoles = ecoles;
    }

    ///Override methods

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_item_model,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        try{
            int id = context.getResources().getIdentifier(ecoles.get(i).getImage(),"drawable",context.getPackageName());
            viewHolder.imageView.setImageResource(id);
        }catch (Exception ex){
            viewHolder.imageView.setImageResource(R.drawable.ic_do_not_disturb_black_24dp);
        }
        viewHolder.name.setText(ecoles.get(i).getName());
        String description = ecoles.get(i).getDescrition().trim();
        try{
            description = description.substring(0,130)+"...";
            viewHolder.description.setText(description);
        }catch (Exception ex){
            viewHolder.description.setText(ecoles.get(i).getDescrition().trim()+"...");
        }
        String theDate = ecoles.get(i).getCreationDate().get(Calendar.MONTH) + "/" + ecoles.get(i).getCreationDate().get(Calendar.DAY_OF_MONTH) + "/" + ecoles.get(i).getCreationDate().get(Calendar.YEAR);
        viewHolder.dateNaiss.setText(""+theDate);
        viewHolder.note.setText(""+ ecoles.get(i).getNote());
    }

    @Override
    public int getItemCount() {
        return ecoles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView        imageView;
        private TextView         name;
        private TextView         dateNaiss;
        private TextView         description;
        private TextView         note;
        private ConstraintLayout parent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.circleImageView_list_item_formation);
            name = itemView.findViewById(R.id.textView_name);
            dateNaiss = itemView.findViewById(R.id.textView_date_creation);
            description = itemView.findViewById(R.id.textView_description);
            note = itemView.findViewById(R.id.textView_note);
            parent = itemView.findViewById(R.id.content_item_model);
        }
    }
}
