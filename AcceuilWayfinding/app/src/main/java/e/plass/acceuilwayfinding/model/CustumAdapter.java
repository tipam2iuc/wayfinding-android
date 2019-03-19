package e.plass.acceuilwayfinding.model;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import e.plass.acceuilwayfinding.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

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
        viewHolder.name.setText(ecoles.get(i).getName());
        String description = ecoles.get(i).getDescrition();
        viewHolder.description.setText(description.substring(10)+"...");
        viewHolder.dateNaiss.setText(""+ecoles.get(i).getCreationDate());
        viewHolder.note.setText(""+ ecoles.get(i).getNote());
        Log.d("onBindViewHolder",""+ecoles.get(i).getNote());
    }

    @Override
    public int getItemCount() {
        return ecoles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView  name;
        private TextView  dateNaiss;
        private TextView  description;
        private TextView  note;
        private ConstraintLayout    parent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_name);
            dateNaiss = itemView.findViewById(R.id.textView_date_creation);
            description = itemView.findViewById(R.id.textView_description);
            note = itemView.findViewById(R.id.textView_note);
            parent = itemView.findViewById(R.id.content_item_model);
        }
    }
}
