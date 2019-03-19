package e.plass.acceuilwayfinding;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import e.plass.acceuilwayfinding.model.ParamAdapter;
import e.plass.acceuilwayfinding.model.Paramatre;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    private ConstraintLayout layout_bottom;
    private ConstraintLayout layout_content;
    private ConstraintLayout layout_ongle;
    private ImageButton      burgger;
    private ImageButton      previous;

    private BottomNavigationView mMainNav;
    private FrameLayout mFrame;

    private HomeFragment homeFragment;
    private SchoolFragment schoolFragment;
    private AccountFragment accountFragment;
    private FormationFragment formationFragment;
    private SearchFragment searchFragment;

    private RecyclerView recyclerView;
    private ArrayList<Paramatre> paramatres;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //intitialisation
        layout_bottom = findViewById(R.id.layout_bottom);
        layout_content = findViewById(R.id.layout_content);
        burgger = findViewById(R.id.imageButton_burgger);
        previous = findViewById(R.id.imageButton_previous);
        layout_ongle = findViewById(R.id.constraint_layout_onglet);
        recyclerView = findViewById(R.id.recyclerView_paramater);

        final float height = Resources.getSystem().getDisplayMetrics().heightPixels;
        final float height_default = height-1.8f*80f;
        float k = layout_ongle.getMeasuredHeightAndState();

        mFrame = findViewById(R.id.frame_nav);
        mMainNav = findViewById(R.id.bottomNavigationView_menu);

        homeFragment = new HomeFragment();
        accountFragment = new AccountFragment();
        formationFragment = new FormationFragment();
        schoolFragment = new SchoolFragment(this);
        searchFragment = new SearchFragment();



        //gestion de l'affichage du menu
        layout_bottom.setTranslationY(height_default);
        layout_bottom.setMinHeight(8000);
        Toast.makeText(getApplicationContext(),""+k,Toast.LENGTH_LONG).show();
        burgger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_bottom.animate().translationY(-layout_ongle.getHeight()).setDuration(1000);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_bottom.animate().translationY(height-1.4f*layout_ongle.getHeight()).setDuration(500);
            }
        });

        //gestion du listview
        paramatres = new ArrayList<>();
        paramatres.add(new Paramatre(1,"Profil",R.drawable.ic_profile,true));
        paramatres.add(new Paramatre(2,"Preference",R.drawable.ic_preference_icons_plan_icons_plan,true));
        paramatres.add(new Paramatre(3,"Configuration",R.drawable.ic_setting,true));
        paramatres.add(new Paramatre(4,"Inviter des amis",R.drawable.ic_shared_icons,true));
        paramatres.add(new Paramatre(5,"A propos",R.drawable.ic_about_icons_plan_icons_plan,true));
        paramatres.add(new Paramatre(6,"Deconnection",R.drawable.ic_power_settings_new_black_24dp,false));
        initParamRecycleView();

        //Navigation
        setFragment(homeFragment);
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_item_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.menu_item_school:
                        setFragment(schoolFragment);
                        return true;
                    case R.id.menu_item_formation:
                        setFragment(formationFragment);
                        return true;
                    case R.id.menu_item_account:
                        setFragment(accountFragment);
                        return true;
                    case R.id.menu_item_search:
                        setFragment(searchFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_nav,fragment);
        fragmentTransaction.commit();
    }
    public void initParamRecycleView(){
        try{
            ParamAdapter paramAdapter = new ParamAdapter(this,paramatres);
            recyclerView.setAdapter(paramAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
