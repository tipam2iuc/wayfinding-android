package e.plass.introslide;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.assist.AssistStructure;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class connexion_activity extends AppCompatActivity implements View.OnClickListener {

    private Button btnConnexion;
  private EditText EditTextEmail;
  private EditText EdiTextPassword;
   private ProgressDialog progressDialog;

   private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_activity);

        mFirebaseAuth = FirebaseAuth.getInstance();

        if(mFirebaseAuth.getCurrentUser() != null) {

            finish();
            startActivity(new Intent(getApplicationContext(),profileActivity.class));

        }

        btnConnexion = findViewById(R.id.btn_SignIn);
        EditTextEmail = findViewById(R.id.email_connexion);
        EdiTextPassword = findViewById(R.id.password2);
         progressDialog = new ProgressDialog(this);


        btnConnexion.setOnClickListener(this);

    }
     private void userLogin (){

        String email = EditTextEmail.getText().toString().trim();
         String password = EdiTextPassword.getText().toString().trim();
         FirebaseUser user  = mFirebaseAuth.getCurrentUser();

         if(TextUtils.isEmpty(email)){
             Toast.makeText(this,"Veuillez saisir email",Toast.LENGTH_SHORT).show();
             return;

         }

         if(TextUtils.isEmpty(password)){

             Toast.makeText(this,"Veuillez saisir votre mot de passe",Toast.LENGTH_SHORT).show();
             return;


         }




         progressDialog.setMessage("connexion en cours...");
         progressDialog.show();


         mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 progressDialog.dismiss();
                 if (task.isSuccessful()){
                     finish();
                 startActivity(new Intent(getApplicationContext(),profileActivity.class));
                 }
                 else{

                     Toast.makeText(getApplicationContext(),"Identifiants ou mot de passe Incorrects",Toast.LENGTH_SHORT).show();
                     return;

                 }

             }
         });



     }
    @Override
    public void onClick(View v) {
        if(v == btnConnexion){

            userLogin();
        }
    }
}
