package e.plass.acceuilwayfinding;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.PointerIcon;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import e.plass.acceuilwayfinding.model.ParamAdapter;
import e.plass.acceuilwayfinding.model.Paramatre;

import java.util.ArrayList;

import static e.plass.acceuilwayfinding.R.string.Close;

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
    float height_default = 0f;

    private boolean isSimpleMenu;

    private float height;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        isSimpleMenu = false;

        //intitialisation
        layout_bottom = findViewById(R.id.layout_bottom);
        layout_content = findViewById(R.id.layout_content);
        burgger = findViewById(R.id.imageButton_burgger);
        previous = findViewById(R.id.imageButton_previous);
        layout_ongle = findViewById(R.id.constraint_layout_onglet);
        recyclerView = findViewById(R.id.recyclerView_paramater);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setIcon(R.drawable.logo);

        height = Resources.getSystem().getDisplayMetrics().heightPixels;
        height_default = height-1000f*layout_ongle.getHeight();
        float k = layout_ongle.getMeasuredHeightAndState();

        //ToolBar define
        setSupportActionBar(toolbar);


        mFrame = findViewById(R.id.frame_nav);
        mMainNav = findViewById(R.id.bottomNavigationView_menu);

        layout_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        homeFragment = new HomeFragment();
        accountFragment = new AccountFragment();
        formationFragment = new FormationFragment();
        schoolFragment = new SchoolFragment(this);
        searchFragment = new SearchFragment();






        //gestion de l'affichage du menu
        layout_bottom.setTranslationY(height_default);
        burgger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_bottom.animate().translationY(-layout_ongle.getHeight()+200f).setDuration(500);
            }
        });


        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_bottom.animate().translationY(height).setDuration(500);
            }
        });

        //gestion du listview
        paramatres = new ArrayList<>();
        paramatres.add(new Paramatre(1,R.string.profil,R.drawable.ic_profile,true));
        paramatres.add(new Paramatre(2,R.string.preference,R.drawable.ic_preference_icons_plan_icons_plan,true));
        paramatres.add(new Paramatre(3,R.string.seting,R.drawable.ic_setting,true));
        paramatres.add(new Paramatre(4,R.string.invitation,R.drawable.ic_shared_icons,true));
        paramatres.add(new Paramatre(5,R.string.about,R.drawable.ic_about_icons_plan_icons_plan,true));
        paramatres.add(new Paramatre(6,R.string.test_text,R.drawable.ic_about_icons_plan_icons_plan,true));
        paramatres.add(new Paramatre(7,R.string.diconnect,R.drawable.ic_power_settings_new_black_24dp,false));
        initParamRecycleView();

        //Navigation
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

        if(mMainNav.getSelectedItemId() == R.id.menu_item_home)
            setFragment(homeFragment);
        else if(mMainNav.getSelectedItemId() == R.id.menu_item_school)
            setFragment(schoolFragment);
        else if(mMainNav.getSelectedItemId() == R.id.menu_item_formation)
            setFragment(formationFragment);
        else if(mMainNav.getSelectedItemId() == R.id.menu_item_account)
            setFragment(accountFragment);
        else
            setFragment(searchFragment);


        /*if(findViewById(R.id.menu_item_home).isSelected())
        else if(findViewById(R.id.menu_item_school).isSelected())
            setFragment(schoolFragment);
        else if(findViewById(R.id.menu_item_formation).isSelected())
            setFragment(formationFragment);
        else if(findViewById(R.id.menu_item_account).isSelected())
            setFragment(accountFragment);
        else
            setFragment(searchFragment);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
