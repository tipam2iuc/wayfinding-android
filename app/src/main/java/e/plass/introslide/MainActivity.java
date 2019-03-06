package e.plass.introslide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnConnexion;
    private Button btnInscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConnexion = findViewById(R.id.btn_connexion);
        btnInscription = findViewById(R.id.btn_inscription);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityConnexion();
            }
        });

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityInscription();
            }
        });
    }

    private void OpenActivityInscription() {
        Intent intent = new Intent(this,inscription_activity.class);
        startActivity(intent);

    }

    private void OpenActivityConnexion() {
        Intent intent = new Intent(this,connexion_activity.class);
        startActivity(intent);

    }

}

