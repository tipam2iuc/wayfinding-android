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

import e.plass.acceuilwayfinding.R;

public class ParamAdapter extends RecyclerView.Adapter<ParamAdapter.ViewHolder> {
    private ArrayList<Paramatre> paramatres = new ArrayList<>();
    private Context mContext;

    public ParamAdapter(Context mContext,ArrayList<Paramatre> paramatres) {
        this.paramatres = paramatres;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_parameter_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Paramatre currentParam = paramatres.get(i);
        viewHolder.imageView.setImageResource(currentParam.getIco());
        viewHolder.name.setText(currentParam.getTitle());
        if(currentParam.isNext()){
            viewHolder.next.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return paramatres.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView        imageView;
        private TextView         name;
        private ImageView        next;
        private ConstraintLayout parent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_title_item_param);
            imageView = itemView.findViewById(R.id.imageView_ico_item_param);
            parent = itemView.findViewById(R.id.content_item_model);
            next = itemView.findViewById(R.id.imageView_previous);
        }
    }
}
