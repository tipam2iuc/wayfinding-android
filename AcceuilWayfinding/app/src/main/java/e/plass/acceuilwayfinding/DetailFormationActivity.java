package e.plass.acceuilwayfinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import e.plass.acceuilwayfinding.model.Formation;
import e.plass.acceuilwayfinding.model.Util;

public class DetailFormationActivity extends AppCompatActivity {
    private Formation formation;
    private ImageView imageView;
    private TextView  textNameFormation;
    private TextView  textDescriptionFormation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_formation);
        formation = Util.getCurrentFormation();
        imageView = findViewById(R.id.imageView_detail_formation);
        textNameFormation = findViewById(R.id.textView_detail_formation_name);
        int id;
        try {
            id = getResources().getIdentifier(formation.getImage(), "drawable", getPackageName());
        }catch (Exception ex){
            id = R.drawable.ingenierie;
            Log.d("Element_jjv"+formation.getName(),ex.getMessage());
        }
        imageView.setImageResource(id);
        textNameFormation.setText(formation.getName());
    }
}
