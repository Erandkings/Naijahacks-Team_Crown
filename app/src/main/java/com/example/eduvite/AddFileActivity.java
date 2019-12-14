package com.example.eduvite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class AddFileActivity extends AppCompatActivity {
    private Button addFile, selectFile;
    private TextView fileName;
    Uri pdfUri; // urls that are meant for local storage
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    ProgressBar progressDialog;
    String selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_file);

        addFile = findViewById(R.id.uploadFileBUT);
        selectFile = findViewById(R.id.buttonSelectFile);
        fileName = findViewById(R.id.filenameTV);
        progressDialog = findViewById(R.id.uploadProgress);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        progressDialog.setVisibility(View.GONE);


        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(AddFileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                    selecttheFile();
                }else {
                    ActivityCompat.requestPermissions(AddFileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }
            }
        });

        addFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pdfUri != null){
                    uploadFile(pdfUri);
                }else {
                    Toast.makeText(AddFileActivity.this, "Plase nau, upload a file", Toast.LENGTH_SHORT).show();
                }
            }
        });



        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("STEM");
        spinnerArray.add("Art");
        spinnerArray.add("General Knowledge");
        spinnerArray.add("Leisure");
        spinnerArray.add("Random");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);

        selected = sItems.getSelectedItem().toString();
    }

    private void uploadFile(Uri pdfUri) {

        final String fileName = System.currentTimeMillis()+".pdf";
        final String fileName1 = System.currentTimeMillis()+"";
        StorageReference storageReference = firebaseStorage.getReference();// returns path
        storageReference.child("Uploads").child(selected).child(fileName).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               progressDialog.setVisibility(View.INVISIBLE);
                String url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                // store the url in database

                DatabaseReference reference = firebaseDatabase.getReference();
                reference.child("Uploads").child(selected).child(fileName1).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AddFileActivity.this, " file uploaded", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(AddFileActivity.this, " file not uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(AddFileActivity.this, " file not uploaded failure", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                //track progress
                int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
                progressDialog.setVisibility(View.VISIBLE);

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            selecttheFile();
        }else{
            Toast.makeText(AddFileActivity.this, "Please provide permission", Toast.LENGTH_SHORT).show();
        }
    }
    private void selecttheFile() {


        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //check weather user has selected a file or not
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {

            pdfUri = data.getData();
            fileName.setText("A file s selected: " + data.getData().getLastPathSegment());
        } else {
            Toast.makeText(AddFileActivity.this, "Please select a file", Toast.LENGTH_SHORT).show();
        }
    }
}
