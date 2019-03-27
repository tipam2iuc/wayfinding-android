package e.plass.acceuilwayfinding;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewParentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import e.plass.acceuilwayfinding.model.CustumAdapter;
import e.plass.acceuilwayfinding.model.Ecole;
import e.plass.acceuilwayfinding.model.Formation;
import e.plass.acceuilwayfinding.model.Util;
import e.plass.acceuilwayfinding.model.ViewPageAdapter;

public class DetailFormationActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;

    private CircleImageView imageView;
    private TextView        textNameFormation;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_formation);
        toolbar = findViewById(R.id.Toolbar_detail_formation);
        imageView = findViewById(R.id.imageView_detail_formation);
        textNameFormation = findViewById(R.id.textView_title_formation_detail);
        tabLayout = findViewById(R.id.tabLayout_formation_detail);
        viewPager = findViewById(R.id.viewpager_formation_detail);

        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());

        /*tabLayout.getTabAt(0).setIcon(R.drawable.ic_description);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_view_list);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_format_align_center);*/

        viewPageAdapter.AddFragment(new DescriptionFormationFragment(),"Description");
        viewPageAdapter.AddFragment(new ListEcoleFormationFragment(),"Ecoles");
        viewPageAdapter.AddFragment(new FiliereFormationFragment(),"Filières");

        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);


        Formation currenF = Util.getCurrentFormation();
        try {
            int id = this.getResources().getIdentifier(currenF.getImage(),"drawable",this.getPackageName());
            Glide.with(this)
                    .load(id)
                    .into(imageView);
        }catch (Exception ex){
            Log.d("imgFound", ex.getMessage());
        }
        textNameFormation.setText(currenF.getName());



        setSupportActionBar(toolbar);
        toolbar.setTitle(currenF.getName());
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();
                break;

        }
        return true;
    }


}
