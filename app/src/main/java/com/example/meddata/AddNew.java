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

    private static final int PIC_PICKER = 2;

    ///////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        choose_button = findViewById(R.id.choose);
        upload_button = findViewById(R.id.upload);
        img = findViewById(R.id.imgButton);



      ///////////  upload_button = (Button) findViewById(R.id.button_upload);
        fillForm = (Button) findViewById(R.id.button_fill_data);

        fillForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillForm();
            }
        });
//***************
//        upload_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/jpeg");
//                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
//                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PIC_PICKER);
//            }
//        });
////***************
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

        // get the Firebase storage reference

        // on pressing btnSelect SelectImage() is called
//        btnSelect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                SelectImage();
//            }
//        });

        // on pressing btnUpload uploadImage() is called
//        btnUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadImage();
//            }
//        });
        /////////////

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
                        // Get a URL to the uploaded content
                       // Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        Toast.makeText(AddNew.this, "Image uploaded!", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
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
    // Override onActivityResult method
//    @Override
//    protected void onActivityResult(int requestCode,
//                                    int resultCode,
//                                    Intent data)
//    {
//
//        super.onActivityResult(requestCode,
//                resultCode,
//                data);
//
//        // checking request code and result code
//        // if request code is PICK_IMAGE_REQUEST and
//        // resultCode is RESULT_OK
//        // then set image in the image view
//        if (requestCode == PICK_IMAGE_REQUEST
//                && resultCode == RESULT_OK
//                && data != null
//                && data.getData() != null) {
//
//            // Get the Uri of data
//            filePath = data.getData();
//            Log.d("AddNew", String.valueOf(filePath));
////            try {
////
////                // Setting image on image view using Bitmap
////                Bitmap bitmap = MediaStore
////                        .Images
////                        .Media
////                        .getBitmap(
////                                getContentResolver(),
////                                filePath);
////                imageView.setImageBitmap(bitmap);
////            }
////
////            catch (IOException e) {
////                // Log the exception
////                e.printStackTrace();
////            }
//        }
//    }

    // UploadImage method
//    private void uploadImage()
//    {
//        if (filePath != null) {
//
//            // Code for showing progressDialog while uploading
////            ProgressDialog progressDialog
////                    = new ProgressDialog(this);
////            progressDialog.setTitle("Uploading...");
////            progressDialog.show();
//
//            // Defining the child of storageReference
//            StorageReference ref
//                    = storageReference
//                    .child(
//                            "images/"
//                                    + UUID.randomUUID().toString());
//
//            // adding listeners on upload
//            // or failure of image
//            ref.putFile(filePath)
//                    .addOnSuccessListener(
//                            new OnSuccessListener<UploadTask.TaskSnapshot>() {
//
//                                @Override
//                                public void onSuccess(
//                                        UploadTask.TaskSnapshot taskSnapshot)
//                                {
//
//                                    // Image uploaded successfully
//                                    // Dismiss dialog
//                                   // progressDialog.dismiss();
//                                    Toast
//                                            .makeText(AddNew.this,
//                                                    "Image Uploaded!!",
//                                                    Toast.LENGTH_SHORT)
//                                            .show();
//                                }
//                            });
//
//        }
//    }

    public void fillForm(){
        Intent intent = new Intent(this, ManualData.class);
        startActivity(intent);
    }

}

