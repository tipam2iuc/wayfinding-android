package e.plass.acceuilwayfinding.model.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import e.plass.acceuilwayfinding.R;
import e.plass.acceuilwayfinding.model.SearchType;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {
    private Context          context;
    private List<SearchType> searchTypeList;

    public SearchAdapter(Context context, List<SearchType> searchTypeList) {
        this.context = context;
        this.searchTypeList = searchTypeList;
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater
                .from(context)
                .inflate(R.layout.recyclerview_search_item,viewGroup,false);
        return new SearchHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder searchHolder, int i) {
        searchHolder.title.setText(searchTypeList.get(i).getTitle());
        searchHolder.logo.setImageResource(searchTypeList.get(i).getImg());

    }

    @Override
    public int getItemCount() {
        return searchTypeList.size();
    }


    public class SearchHolder extends RecyclerView.ViewHolder {
        private ImageView logo;
        private TextView title;

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.img_icone_sear_type);
            title = itemView.findViewById(R.id.tv_title_search_item);
        }
    }


}
