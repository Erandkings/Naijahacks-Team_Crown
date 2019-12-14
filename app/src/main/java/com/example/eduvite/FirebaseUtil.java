package com.example.eduvite;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseRef;
    public static FirebaseStorage mFirebaseStorage;
    public static StorageReference mStorageRef;
    public static FirebaseAuth mAuth;
    private static FirebaseAuth.AuthStateListener mAuthListener;
    private static FirebaseUtil mContractClass;
    public Activity mActivity;
    public static Context mContext;
    private static String user_name, pass_word;

    private FirebaseUtil() {
    }

    public static void openFbReference(String ref) {
        if (mContractClass == null) {
            mContractClass = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mAuth = FirebaseAuth.getInstance();
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (mAuth.getCurrentUser() == null) {
//                        CreateAccount.createUser();
                    }
                }
            };
        }
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child(ref);
    }

    public static void attachListener() {
        mAuth.addAuthStateListener(mAuthListener);
    }

    public static void detachListener() {
        mAuth.removeAuthStateListener(mAuthListener);
    }
}
