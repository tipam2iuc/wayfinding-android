package e.plass.acceuilwayfinding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mFirebaseAuth;
    private Button button_Logout;
    private TextView nomUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mFirebaseAuth = FirebaseAuth.getInstance();

       /* if(mFirebaseAuth.getCurrentUser() == null){

            startActivity(new Intent(this,Connexion.class));
            finish();
        }*/
        FirebaseUser user  = mFirebaseAuth.getCurrentUser();
        button_Logout = findViewById(R.id.btn_deconnexion);
        nomUser = findViewById(R.id.text12);
        nomUser.setText("Bienvenue "+ user.getEmail());

        button_Logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == button_Logout){

            mFirebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,Connexion.class));
        }
    }
}