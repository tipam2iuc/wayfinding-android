package e.plass.acceuilwayfinding;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import e.plass.acceuilwayfinding.model.CustumAdapter;
import e.plass.acceuilwayfinding.model.Ecole;

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

        Ecole ecole = new Ecole(1,7.4f,"IUC","1",new Date(10,12,10),"Douala","Logbessou",
                "Essono","At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga");
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);
        ecoles.add(ecole);

        initRecycleView();
        return view;
    }


    public void initRecycleView(){
        try{
            CustumAdapter custumAdapter = new CustumAdapter(getContext(),ecoles);
            recyclerView.setAdapter(custumAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }catch (Exception ex){
            Toast.makeText(getContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}
