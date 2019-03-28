package e.plass.acceuilwayfinding;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import e.plass.acceuilwayfinding.model.CustumAdapter;
import e.plass.acceuilwayfinding.model.Ecole;
import e.plass.acceuilwayfinding.model.SchoolAdapter;
import e.plass.acceuilwayfinding.model.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListEcoleFormationFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Ecole> ecoles;
    private SchoolAdapter schoolAdapter;

    public ListEcoleFormationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_ecole_formation, container, false);


        recyclerView = view.findViewById(R.id.RecyclerView_list_school_formation1);
        Util u = new Util();
        ecoles = Util.getEcoles();


        schoolAdapter = new SchoolAdapter(getContext(),ecoles);
        recyclerView.setAdapter(schoolAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }


    public void initRecycleView(){
        CustumAdapter custumAdapter = new CustumAdapter(getContext(),ecoles);
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_anim_slide);
        recyclerView.setAdapter(custumAdapter);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
