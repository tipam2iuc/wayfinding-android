package e.plass.acceuilwayfinding;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import e.plass.acceuilwayfinding.model.ConcourAdapter;
import e.plass.acceuilwayfinding.model.SchoolAdapter;
import e.plass.acceuilwayfinding.model.Util;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class HomeFragment extends android.support.v4.app.Fragment {
    private RecyclerView rSchool,rConcours;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rSchool = view.findViewById(R.id.recyclerView_home_ecole);
        rConcours = view.findViewById(R.id.recyclerView_home_concours);

        initRecycleView1();
        initRecycleView2();



        return view;
    }


    public void initRecycleView1(){
        SchoolAdapter custumAdapter = new SchoolAdapter(getContext(), Util.getEcoles());
        rSchool.setAdapter(custumAdapter);
        rSchool.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    public void initRecycleView2(){
        ConcourAdapter custumAdapter = new ConcourAdapter(getContext(),Util.getConcours());
        rConcours.setAdapter(custumAdapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rConcours.setLayoutManager(layoutManager);
    }

}
