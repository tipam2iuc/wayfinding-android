package e.plass.acceuilwayfinding;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import e.plass.acceuilwayfinding.model.CustumAdapter;
import e.plass.acceuilwayfinding.model.Ecole;
import e.plass.acceuilwayfinding.model.Util;

import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class SchoolFragment extends android.support.v4.app.Fragment {

    private ArrayList<Ecole> ecoles = new ArrayList<>();
    private RecyclerView     recyclerView;
    private Context context;

    private View view;

    @SuppressLint("ValidFragment")
    public SchoolFragment(Context context) {
        this.context = context;
    }

    public SchoolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ecoles = new ArrayList<>();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_school, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_school);

        Util u = new Util();
        ecoles.addAll(Util.getEcoles());
        initRecycleView();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.nav_items,null);
    }

    public void initRecycleView(){
        CustumAdapter             custumAdapter             = new CustumAdapter(getContext(),ecoles);
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_anim_slide);
        recyclerView.setAdapter(custumAdapter);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
