package e.plass.acceuilwayfinding;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class Inscription extends AppCompatActivity implements View.OnClickListener{
    private Button btnInscription;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText confirmPassword;
    private Button btnInscriptionGoogle;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mFirebaseAuth;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        mFirebaseAuth =  FirebaseAuth.getInstance();

        if(mFirebaseAuth.getCurrentUser() != null) {

            finish();
            startActivity(new Intent(getApplicationContext(),Profile.class));

        }

        mProgressDialog = new ProgressDialog(this);

        btnInscription = findViewById(R.id.btn_sign_up);
        txtEmail = findViewById(R.id.TxtEmail);
        txtPassword = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.passwordhint);
        btnInscription.setOnClickListener(this);
    }

    private void registerUser() {
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        String passconfirm = confirmPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Veuillez saisir email",Toast.LENGTH_SHORT).show();
            return;

        }

        if(TextUtils.isEmpty(password)){

            Toast.makeText(this,"Veuillez saisir votre mot de passe",Toast.LENGTH_SHORT).show();
            return;


        }
        /*if(TextUtils.){


            Toast.makeText(this,"Les Mots de passe saisis sont Differents",Toast.LENGTH_SHORT).show();
            return;

        }*/


        mProgressDialog.setMessage("Inscription en cours...");
        mProgressDialog.show();



        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, (@NonNull Task<AuthResult> task) ->{
                if(task.isSuccessful()){

                    Intent i = new Intent(this,Profile.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(Inscription.this,"Inscription Reussie", Toast.LENGTH_SHORT).show();


                }else{

                    Toast.makeText(Inscription.this,"Echec d'inscription", Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        );

    }



    @Override
    public void onClick(View v) {
        if (v == btnInscription){

            registerUser();
        }
    }



}
