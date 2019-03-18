package e.plass.acceuilwayfinding;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.assist.AssistStructure;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Connexion extends AppCompatActivity implements View.OnClickListener {

    private static final int GOOGLE_SIGN = 123;
    private Button btnConnexion;
    private EditText EditTextEmail;
    private EditText EdiTextPassword;
    private ProgressDialog progressDialog;
    private Button btnFacebook;
    private Button btnTwitter;
    private Button btnGoogle;
    GoogleSignInClient mGoogleSignInClient;

    ProgressBar mProgressBar;


    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        btnGoogle = findViewById(R.id.btn_google);
        btnConnexion = findViewById(R.id.btn_SignIn);
        EditTextEmail = findViewById(R.id.email_connexion);
        EdiTextPassword = findViewById(R.id.password2);


        mFirebaseAuth = FirebaseAuth.getInstance();

        if (mFirebaseAuth.getCurrentUser() != null) {

            finish();
            startActivity(new Intent(getApplicationContext(), Profile.class));

        }


        progressDialog = new ProgressDialog(this);


        btnConnexion.setOnClickListener(this);
    }

        private void userLogin(){

            String email = EditTextEmail.getText().toString().trim();
            String password = EdiTextPassword.getText().toString().trim();
            FirebaseUser user = mFirebaseAuth.getCurrentUser();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Veuillez saisir email", Toast.LENGTH_SHORT).show();
                return;

            }

            if (TextUtils.isEmpty(password)) {

                Toast.makeText(this, "Veuillez saisir votre mot de passe", Toast.LENGTH_SHORT).show();
                return;


            }


            progressDialog.setMessage("connexion en cours...");
            progressDialog.show();


            mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                    } else {

                        Toast.makeText(getApplicationContext(), "Identifiants ou mot de passe Incorrects", Toast.LENGTH_SHORT).show();
                        return;

                    }

                }
            });


        }

        
    @Override
    public void onClick (View v){
        if (v == btnConnexion) {

            userLogin();
        }
    }




}
