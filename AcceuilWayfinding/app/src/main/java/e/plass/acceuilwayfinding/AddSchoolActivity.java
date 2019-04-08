package e.plass.acceuilwayfinding;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import e.plass.acceuilwayfinding.model.Ecole;

public class AddSchoolActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private EditText  schoolName,note,descr,id,date,ville;
    private Button button;
    private ProgressBar progressBar;
    private Uri imageUri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_school);
        imageView = findViewById(R.id.img_schooladd);
        schoolName = findViewById(R.id.et_school_name);
        note = findViewById(R.id.et_note_school);
        descr = findViewById(R.id.et_description);
        button = findViewById(R.id.btn_addFirebase);
        progressBar = findViewById(R.id.pb_schoolAdd);
        id = findViewById(R.id.et_school_id);
        date = findViewById(R.id.et_date_school);
        ville = findViewById(R.id.et_ville);


        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        imageView.setOnClickListener(v->{
            openFileChoose();
        });
        button.setOnClickListener(v->{
            uploadFile();
        });

    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if(imageUri != null){
            StorageReference fstorageReference = storageReference.child(System.currentTimeMillis()
            +"."+getFileExtension(imageUri));

            fstorageReference.putFile(imageUri)
            .addOnSuccessListener((UploadTask.TaskSnapshot taskSnapshot)-> {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    progressBar.setProgress(0);
                },500);
                Toast.makeText(this,"Succes",Toast.LENGTH_LONG).show();
                Ecole e = new Ecole(Integer.parseInt(id.getText().toString().trim()),
                        Float.parseFloat(note.getText().toString().trim()),
                        schoolName.getText().toString().trim(),
                        taskSnapshot.getUploadSessionUri().toString(),
                        (Calendar) date.getText(),
                        ville.getText().toString().trim(),"","",
                        descr.getText().toString().trim()

                        );
                String uploadId = databaseReference.push().getKey();
                databaseReference.child(uploadId).setValue(e);
                }
            )
            .addOnFailureListener(command -> {
                Toast.makeText(this,command.getMessage(),Toast.LENGTH_SHORT).show();
            })
            .addOnProgressListener(command -> {
                double progress = (100.0 * command.getBytesTransferred()/command.getTotalByteCount());
                progressBar.setProgress((int)progress);
            })
            ;
        }else{
            Toast.makeText(this,"No file selected",Toast.LENGTH_SHORT).show();
        }
    }

    private void openFileChoose() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
        && data != null && data.getData() != null){
            imageUri = data.getData();

            Glide.with(this).load(imageUri).into(imageView);


        }
    }
}
