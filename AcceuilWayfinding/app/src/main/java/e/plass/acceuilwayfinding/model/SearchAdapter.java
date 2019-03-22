package e.plass.acceuilwayfinding.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import e.plass.acceuilwayfinding.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHoder> implements Filterable {
    private Context context;
    private ArrayList<Formation> formations = new ArrayList<>();
    private ArrayList<Formation> sortFormations = new ArrayList<>();
    private ArrayList<Ecole> ecoles = new ArrayList<>();
    private ArrayList<Ecole> sortEcoles = new ArrayList<>();
    private boolean isChool;

    public boolean isChool() {
        return isChool;
    }

    public ArrayList<Formation> getFormations() {
        return formations;
    }

    public ArrayList<Ecole> getEcoles() {
        return ecoles;
    }

    public void setChool(boolean chool) {
        isChool = chool;
    }

    public SearchAdapter(Context context, ArrayList<Formation> formations, ArrayList<Ecole> ecoles, boolean isChool) {
        this.context = context;
        this.formations = formations;
        this.ecoles = ecoles;
        this.isChool = isChool;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item_search,viewGroup,false);
        ViewHoder viewHoder = new ViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder viewHoder, int i) {
        if(isChool){
            Ecole current = ecoles.get(i);
            try{
                int id = context.getResources().getIdentifier(current.getImage(),"drawable",context.getPackageName());
                Glide.with(context).load(id).into(viewHoder.image);
            }catch (Exception ex){
                viewHoder.image.setImageResource(R.drawable.ic_do_not_disturb_black_24dp);
            }
            viewHoder.nom.setText(current.getName());
        }else {
            Formation current = formations.get(i);
            try {
/*
                int id = context.getResources().getIdentifier(formations.get(i).getImage(),"drawable",context.getPackageName());
*/
                int id = context.getResources().getIdentifier(current.getImage(),"drawable",context.getPackageName());
                Glide.with(context).load(id).into(viewHoder.image);
            }catch (Exception ex){
                /*viewHoder.image.setImageResource(R.drawable.ic_do_not_disturb_black_24dp);*/
            }
            viewHoder.nom.setText(current.getName());
        }
    }

    @Override
    public int getItemCount() {
        if(isChool)
            return ecoles.size();
        else
            return formations.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        private CircleImageView image;
        private TextView        nom;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.circleImageView_list_item_search);
            nom = itemView.findViewById(R.id.textView_item_search_name);

        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(isChool){
                ArrayList<Ecole> ecoles2 = new ArrayList<>();
                if(constraint == null || constraint.length() == 0){
                    ecoles2.addAll(ecoles);
                }else {
                    String path = constraint.toString().toLowerCase().trim();
                    for(Ecole item : ecoles){
                        if(item.getName().toLowerCase().contains(path)){
                            ecoles2.add(item);
                            Toast.makeText(context,"Found",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = ecoles2;
                return filterResults;
            }else{
                ArrayList<Formation> ecoles2 = new ArrayList<>();
                if(constraint == null || constraint.length() == 0){
                    ecoles2.addAll(formations);
                }else {
                    String path = constraint.toString().toLowerCase().trim();
                    for(Formation item : formations){
                        if(item.getName().toLowerCase().contains(path)){
                            ecoles2.add(item);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = ecoles2;
                return filterResults;
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if(isChool){
                ecoles.clear();
                ecoles.addAll((ArrayList)results.values);
                notifyDataSetChanged();
            }else {
                formations.clear();
                formations.addAll((ArrayList)results.values);
                notifyDataSetChanged();
            }
        }
    };
}
