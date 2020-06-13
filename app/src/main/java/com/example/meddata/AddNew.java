package com.example.meddata;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class AddNew extends AppCompatActivity {
    Button choose_button, upload_button, fillForm;
    ImageView img;
    StorageReference mStorageRef;

    public Uri imguri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        choose_button = findViewById(R.id.choose);
        upload_button = findViewById(R.id.upload);
        img = findViewById(R.id.imgButton);

        fillForm = (Button) findViewById(R.id.button_fill_data);

        fillForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillForm();
            }
        });
        choose_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FileChooser();

            }
        });
        upload_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick( View view){
                    Fileuploader();

            }
        });
    }

    private String getExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void Fileuploader(){
        StorageReference Ref = mStorageRef.child(System.currentTimeMillis() +"."+getExtension(imguri));

        Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       // Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        Toast.makeText(AddNew.this, "Image uploaded!", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(AddNew.this, "Failed to upload...", Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void FileChooser(){
        Intent intent = new Intent();
        intent.setType("image/jpeg");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== 1 && resultCode== RESULT_OK && data!= null && data.getData()!= null){
            imguri = data.getData();
            Log.d("URI", imguri.toString());
            img.setImageURI(imguri);
        }
    }

    public void fillForm(){
        Intent intent = new Intent(this, ManualData.class);
        startActivity(intent);
    }
}

