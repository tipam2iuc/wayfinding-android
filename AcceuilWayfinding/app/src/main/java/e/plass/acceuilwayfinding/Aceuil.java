package e.plass.acceuilwayfinding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class Aceuil extends AppCompatActivity  {

    private Button btnConnexion;
    private Button btnInscription;
    private Button btnpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        btnConnexion = findViewById(R.id.btn_connexion);
        btnInscription = findViewById(R.id.btn_inscription);
        //btnpass = findViewById(R.id.btn_pass);
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

        /*btnpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityProfile();
            }


        });*/
    }
    private void openActivityProfile() {

        startActivity(new Intent(getApplicationContext(),Profile.class));
    }
    private void OpenActivityInscription() {
        startActivity(new Intent(getApplicationContext(),Inscription.class));
    }

    private void OpenActivityConnexion() {
        startActivity(new Intent(getApplicationContext(),Connexion.class));
    }


    /*@Override
    public void onClick(View v) {
        if (v == btnpass) {
            openActivityProfile();
        }
    }*/
}

