package e.plass.acceuilwayfinding;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import e.plass.acceuilwayfinding.model.Formation;
import e.plass.acceuilwayfinding.model.FormationAdapter;
import e.plass.acceuilwayfinding.model.Util;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class FormationFragment extends android.support.v4.app.Fragment {
  private final String               URL_JSON = "localhost/Services/_getAllDomaine.php";
  private       JsonArrayRequest     ArrayRequest ;
  private       RequestQueue         requestQueue ;


  private       ArrayList<Formation> formations        = new ArrayList<>();
  private       ArrayList<Formation> sortFormations    = new ArrayList<>();
  private       ArrayList<Formation> contentFormations = new ArrayList<>();
  private       RecyclerView         recyclerView;
  private       Context              context;
  private       Spinner              spinner;
  private       FormationAdapter     custumAdapter;


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
      jsoncall();

    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spiner_formation, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    // Inflate the layout for this fragment
    return v;
  }

    public void jsoncall() {

      Formation f = new Formation(1,"jkjkjkj",null,"njnjnj",10f,"nnnjn");
      formations.add(f);

        ArrayRequest = new JsonArrayRequest(URL_JSON, response -> {

            Toast.makeText(getContext(), "jsoncall", Toast.LENGTH_SHORT).show();
            JSONObject jsonObject = null;


            for (int i = 0 ; i<response.length();i++) {

                Toast.makeText(getContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();

                try {

                    jsonObject = response.getJSONObject(i);
                    Formation formation = new Formation();

                    formation.setName(jsonObject.getString("nom"));
                    formation.setId(jsonObject.getInt("idT_domaine"));
                    formation.setNotes(15f);
                    formation.setDescripetion("lorem");

                    //Toast.makeText(MainActivity.this,anime.toString(),Toast.LENGTH_SHORT).show();
                    formations.add(formation);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


            Toast.makeText(getContext(),"Size of Liste "+String.valueOf(formations.size()),Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(),formations.get(1).toString(),Toast.LENGTH_SHORT).show();


            custumAdapter = new FormationAdapter(getContext(), formations);
            initRecycleView();
        }, error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show());


        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(ArrayRequest);
    }

    public void setRvadapter (ArrayList<Formation> formations) {

        custumAdapter = new FormationAdapter(getContext(), formations);

        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_anim_slide);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
        custumAdapter.setOnItemClickListener(formation -> {
            Util.setCurrentFormation(formation);
            Intent intent = new Intent(getContext(), DetailFormationActivity.class);
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    public void initRecycleView() {
    LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_anim_slide);
    recyclerView.setAdapter(custumAdapter);

    recyclerView.setLayoutAnimation(layoutAnimationController);
    recyclerView.getAdapter().notifyDataSetChanged();
    recyclerView.scheduleLayoutAnimation();

    custumAdapter.setOnItemClickListener(formation -> {
      Util.setCurrentFormation(formation);
      Intent intent = new Intent(getContext(), DetailFormationActivity.class);
      startActivity(intent);
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
