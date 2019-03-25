package e.plass.acceuilwayfinding;


import android.content.Context;
import android.content.Intent;
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
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import e.plass.acceuilwayfinding.model.CustumAdapter;
import e.plass.acceuilwayfinding.model.Formation;
import e.plass.acceuilwayfinding.model.FormationAdapter;
import e.plass.acceuilwayfinding.model.Util;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class FormationFragment extends android.support.v4.app.Fragment {
  private ArrayList<Formation> formations        = new ArrayList<>();
  private ArrayList<Formation> sortFormations    = new ArrayList<>();
  private ArrayList<Formation> contentFormations = new ArrayList<>();
  private RecyclerView         recyclerView;
  private Context              context;
  private Spinner              spinner;

  private FormationAdapter custumAdapter;


  public FormationFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_formation, container, false);
    recyclerView = v.findViewById(R.id.recyclerview_formation);
    spinner = v.findViewById(R.id.spinner_sort_formation);
    formations.clear();

    Util u = new Util();

    formations = Util.getFormations();
    custumAdapter = new FormationAdapter(getContext(), formations);
    initRecycleView();

    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spiner_formation, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);

    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
          sortFormations();
        } else if (position == 1) {
          sortFormations();
          Collections.reverse(formations);
          initRecycleView();
        } else {
          initRecycleView();
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
    // Inflate the layout for this fragment
    return v;
  }

  public void initRecycleView() {
    LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_anim_slide);
    recyclerView.setAdapter(custumAdapter);

    recyclerView.setLayoutAnimation(layoutAnimationController);
    recyclerView.getAdapter().notifyDataSetChanged();
    recyclerView.scheduleLayoutAnimation();

    custumAdapter.setOnItemClickListener(new FormationAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(Formation formation) {
        Util.setCurrentFormation(formation);
        Intent intent = new Intent(getContext(), DetailFormationActivity.class);
        startActivity(intent);
      }
    });

    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
  }
  public void sortFormations(){
    Collections.sort(formations, (Formation o1, Formation o2) ->
            o1.getName().compareToIgnoreCase(o2.getName())
    );
    custumAdapter.notifyDataSetChanged();
  }


}
