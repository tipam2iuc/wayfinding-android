package e.plass.acceuilwayfinding;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import e.plass.acceuilwayfinding.model.Adapter.FormationAdapter;
import e.plass.acceuilwayfinding.model.DomaineList;
import e.plass.acceuilwayfinding.model.Network.GetDomaineDataService;
import e.plass.acceuilwayfinding.model.Network.RetrofitInstance;
import e.plass.acceuilwayfinding.model.Util;
import e.plass.acceuilwayfinding.model.t_domaine;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class FormationFragment extends android.support.v4.app.Fragment {
    private ArrayList<t_domaine> tdomaines        = new ArrayList<>();
    private ArrayList<t_domaine> sortTdomaines    = new ArrayList<>();
    private ArrayList<t_domaine> contentTdomaines = new ArrayList<>();
    private RecyclerView         recyclerView;
    private Context              context;
    private Spinner              spinner;


    private FormationAdapter      custumAdapter;
    SwipeRefreshLayout swipeRefresh;


    public FormationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_formation, container, false);
        recyclerView = v.findViewById(R.id.recyclerview_formation);
        spinner = v.findViewById(R.id.spinner_sort_formation);
        swipeRefresh = v.findViewById(R.id.swiper);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),R.array.spiner_formation,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    if(position == 1){
                        Collections.reverse(tdomaines);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        GetDomaineDataService getDomaineDataService = RetrofitInstance.getRetrofitInstance().create(GetDomaineDataService.class);
        Call<DomaineList>     call                  = getDomaineDataService.geDomaineData();

        call.enqueue(new Callback<DomaineList>() {
            @Override
            public void onResponse(@NonNull Call<DomaineList> call, @NonNull Response<DomaineList> response) {
                if (response.body() != null) {
                    initRecycleView(response.body().getDomaines());
                }
            }

            @Override
            public void onFailure(@NonNull Call<DomaineList> call, @NonNull Throwable throwable) {
                Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private void generateDomaineList(ArrayList<t_domaine> domaines) {
//        custumAdapter = new FormationAdapter(getContext(),domaines);
//        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_anim_slide);
//        recyclerView.setLayoutAnimation(layoutAnimationController);
//        recyclerView.scheduleLayoutAnimation();
//        recyclerView.setAdapter(custumAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void initRecycleView(ArrayList<t_domaine> domaines){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_anim_slide);
        custumAdapter = new FormationAdapter(getContext(), domaines);

        recyclerView.setAdapter(custumAdapter);

        recyclerView.setLayoutAnimation(layoutAnimationController);
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

        custumAdapter.setOnItemClickListener(new FormationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(t_domaine tdomaine) {
                Util.setCurrentTdomaine(tdomaine);
                Intent intent = new Intent(getContext(),DetailFormationActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    public void sortFormations(){
        Collections.sort(tdomaines, (t_domaine o1, t_domaine o2) ->
                o1.getName().compareToIgnoreCase(o2.getName())
        );
        custumAdapter.notifyDataSetChanged();
    }
}
