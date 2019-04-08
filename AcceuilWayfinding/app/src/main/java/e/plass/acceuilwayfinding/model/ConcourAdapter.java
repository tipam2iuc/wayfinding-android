package e.plass.acceuilwayfinding.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import e.plass.acceuilwayfinding.R;

public class ConcourAdapter extends RecyclerView.Adapter<ConcourAdapter.ViewHolder> {
    Context context;
    List<Concours> concours = new ArrayList<>();

    public ConcourAdapter(Context context,List<Concours> concours) {
        this.concours = concours;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_concour_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Concours curr = concours.get(i);
        viewHolder.name.setText(curr.getName());
        viewHolder.datel.setText("Date limite de dépôt "+curr.getDate(curr.getLimite()));
        viewHolder.dated.setText("Date du concours : "+curr.getDate(curr.getDebut()));
    }

    @Override
    public int getItemCount() {
        return concours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,datel,dated;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name  = itemView.findViewById(R.id.textView_concours_item_tilte);
            dated  = itemView.findViewById(R.id.textView_concours_item_date);
            datel = itemView.findViewById(R.id.textView_concours_item_limite_date);
        }
    }
}
