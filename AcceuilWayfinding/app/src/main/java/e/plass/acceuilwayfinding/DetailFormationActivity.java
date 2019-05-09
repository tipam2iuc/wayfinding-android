package e.plass.acceuilwayfinding;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import e.plass.acceuilwayfinding.model.t_domaine;
import e.plass.acceuilwayfinding.model.Util;
import e.plass.acceuilwayfinding.model.Adapter.ViewPageAdapter;

public class DetailFormationActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;
    private Button addBtn;

    private CircleImageView imageView;
    private Toolbar toolbar;

    private final int sdk = android.os.Build.VERSION.SDK_INT;
    private boolean isAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
               // WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_formation);
        toolbar = findViewById(R.id.Toolbar_detail_formation);
        imageView = findViewById(R.id.imageView_detail_formation);
        tabLayout = findViewById(R.id.tabLayout_formation_detail);
        viewPager = findViewById(R.id.viewpager_formation_detail);
        addBtn = findViewById(R.id.button_add);

        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());

        /*tabLayout.getTabAt(0).setIcon(R.drawable.ic_description);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_view_list);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_format_align_center);*/

        viewPageAdapter.AddFragment(new DescriptionFormationFragment(),"Description");
        viewPageAdapter.AddFragment(new ListEcoleFormationFragment(),"Ecoles");
        viewPageAdapter.AddFragment(new FiliereFormationFragment(),"Filières");

        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);


        t_domaine currenF = Util.getCurrentTdomaine();
        try {
            int id = this.getResources().getIdentifier(currenF.getImage(),"drawable",this.getPackageName());
            Glide.with(this)
                    .load(id)
                    .into(imageView);
        }catch (Exception ex){
            Log.d("imgFound", ex.getMessage());
        }



        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(currenF.getName());
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        addBtn.setOnClickListener(v -> {
            if(isAdd){
                Drawable img = this.getResources().getDrawable( R.drawable.ic_add_circle );
                addBtn.setText(R.string.add);
                addBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_circle,0,0,0);
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    addBtn.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.custum_backgroung_btn_add_formation) );
                } else {
                    addBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.custum_backgroung_btn_add_formation));
                }
                isAdd = false;
            }else {
                Drawable img = this.getResources().getDrawable( R.drawable.ic_check_circle );
                addBtn.setText(R.string.added);
                addBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle,0,0,0);
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    addBtn.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bc_vert_add) );
                } else {
                    addBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.bc_vert_add));
                }
                isAdd = true;
            }
        });
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
