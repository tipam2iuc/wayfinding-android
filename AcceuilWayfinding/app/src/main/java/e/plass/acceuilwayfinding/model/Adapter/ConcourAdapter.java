package e.plass.acceuilwayfinding.model.Adapter;

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
import e.plass.acceuilwayfinding.model.Concours;

public class ConcourAdapter extends RecyclerView.Adapter<ConcourAdapter.ViewHolder> {
    private Context        context;
    private List<Concours> concours = new ArrayList<>();

    public ConcourAdapter(Context context,List<Concours> concours) {
        this.concours = concours;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_concour_item,viewGroup,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Concours curr = concours.get(i);
        viewHolder.name.setText(curr.getName());
        viewHolder.datel.setText(String.format("Date limite de dépôt %s", curr.getDate(curr.getLimite())));
        viewHolder.dated.setText(String.format("Date du concours : %s", curr.getDate(curr.getDebut())));
    }

    @Override
    public int getItemCount() {
        return concours.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,datel,dated;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name  = itemView.findViewById(R.id.textView_concours_item_tilte);
            dated  = itemView.findViewById(R.id.textView_concours_item_date);
            datel = itemView.findViewById(R.id.textView_concours_item_limite_date);
        }
    }
}
