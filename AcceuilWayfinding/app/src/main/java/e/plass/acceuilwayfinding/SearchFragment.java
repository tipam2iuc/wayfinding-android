package e.plass.acceuilwayfinding;


import android.os.Bundle;
import android.app.Fragment;
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

import e.plass.acceuilwayfinding.model.Ecole;
import e.plass.acceuilwayfinding.model.Formation;
import e.plass.acceuilwayfinding.model.SearchAdapter;
import e.plass.acceuilwayfinding.model.Util;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class SearchFragment extends android.support.v4.app.Fragment {

    private RecyclerView         recyclerView;
    private SearchView           searchview;
    private ArrayList<Formation> formations    = new ArrayList<>();
    private ArrayList<Ecole>     schools       = new ArrayList<>();
    private ArrayList<Ecole>     schools1      = new ArrayList<>();
    private ArrayList<Formation> formations1   = new ArrayList<>();
    private SearchAdapter        searchAdapter = new SearchAdapter(getContext(), Util.getFormations(),Util.getEcoles(),false);
    private Spinner              spinner;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_search_item);
        searchview = view.findViewById(R.id.searchView_search);
        spinner = view.findViewById(R.id.spinner_search);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.spiner_search,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        initRecycleView();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    searchAdapter = new SearchAdapter(getContext(),Util.getFormations(),Util.getEcoles(),false);
                    initRecycleView();
                }else  if(position == 1){
                    searchAdapter = new SearchAdapter(getContext(),Util.getFormations(),Util.getEcoles(),true);
                    initRecycleView();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Inflate the layout for this fragment
        return view;
    }


    public void initRecycleView(){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_fall_down);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void searchToRecyclerFormation(String newText){

    }
}
