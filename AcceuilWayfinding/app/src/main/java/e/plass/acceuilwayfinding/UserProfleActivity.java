package e.plass.acceuilwayfinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import e.plass.acceuilwayfinding.model.Ecole;
import e.plass.acceuilwayfinding.model.Util;

public class UserProfleActivity extends AppCompatActivity {
    private ImageView img;
    private TextView desc,ville,name;
    private RatingBar ratingBar;
    private Ecole ecole;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profle);

        img = findViewById(R.id.img_school_detail);
        desc = findViewById(R.id.tv_school_description);
        name = findViewById(R.id.tv_school_name);
        ville = findViewById(R.id.tv_school_ville);
        ratingBar = findViewById(R.id.ratingBar_school_note);
        toolbar = findViewById(R.id.toolbar_detail_school);

        ecole = Util.getCurrentEcole();

        int id = this.getResources().getIdentifier(ecole.getImage(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(id)
                .into(img);

        desc.setText(ecole.getDescrition());
        name.setText(ecole.getName());
        ville.setText(ecole.getVille());

        ratingBar.setRating(ecole.getNote()/20);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(ecole.getName());
    }
}
