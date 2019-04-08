package e.plass.acceuilwayfinding;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import e.plass.acceuilwayfinding.model.Ecole;
import e.plass.acceuilwayfinding.model.Formation;
import e.plass.acceuilwayfinding.model.SearchAdapter;
import e.plass.acceuilwayfinding.model.SearchType;
import e.plass.acceuilwayfinding.model.Util;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class SearchFragment extends android.support.v4.app.Fragment {

    private RecyclerView         recyclerView;
    private List<SearchType> searchTypes;
    private SearchAdapter searchAdapter;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Util u = new Util();
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        recyclerView = view.findViewById(R.id.recycler_view_seach_type);
        searchTypes = Util.getSearchTypes();

        initRecycleView();

        // Inflate the layout for this fragment
        return view;
    }


    public void initRecycleView(){
        searchAdapter = new SearchAdapter(getContext(),searchTypes);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }
}
