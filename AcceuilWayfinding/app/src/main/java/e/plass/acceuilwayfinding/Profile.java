package e.plass.acceuilwayfinding;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.concurrent.Executor;

import e.plass.acceuilwayfinding.model.Util;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1 ;
    private BottomNavigationView mMainNav;
    private static FusedLocationProviderClient fusedLocationProviderClient;

    private HomeFragment homeFragment;
    private SchoolFragment schoolFragment;
    private AccountFragment accountFragment;
    private FormationFragment formationFragment;
    private SearchFragment searchFragment;
    private NavigationView navigationView;

    private android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //intitialisation
        Util util = new Util();
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        
        //fetchLocation();

        navigationView.setNavigationItemSelectedListener(this);


        setSupportActionBar(toolbar);

        //ToolBar define
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("");
        actionbar.setHomeAsUpIndicator(R.drawable.ic_format);
        drawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );


        mMainNav = findViewById(R.id.bottomNavigationView_menu);

        homeFragment = new HomeFragment();
        accountFragment = new AccountFragment();
        formationFragment = new FormationFragment();
        schoolFragment = new SchoolFragment();
        searchFragment = new SearchFragment();






        //gestion de l'affichage du menu



        //Navigation
        mMainNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.menu_item_home:{
                    setFragment(homeFragment);
                }
                return true;
                case R.id.menu_item_school:{
                    setFragment(schoolFragment);
                }
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

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else {

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_nav,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.prin_discon){
            Intent i = new Intent(Profile.this,Aceuil.class);
            startActivity(i);
            this.finish();
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void to(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}
