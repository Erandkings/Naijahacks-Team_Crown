package com.example.eduvite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import static com.example.eduvite.FirebaseUtil.mAuth;

public class CreateAccount extends AppCompatActivity {
    private static String username = "";
    private static String password = "";
    EditText editText, editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        FirebaseUtil.openFbReference("message");

        editText = (EditText) findViewById(R.id.email);
        editText1 = (EditText) findViewById(R.id.pass);

        TextView textView = (TextView) findViewById(R.id.login_text);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        Button btn = (Button) findViewById(R.id.signup_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editText.getText().toString();
                password = editText1.getText().toString();
                createUser();
                Log.v("btn", "btn Clicked: succcessful");
                Toast.makeText(CreateAccount.this, "Button is clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    public static void createUser() {
        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            // Update the UI
                            updateUI(user);
                            Log.v("onComplete", "Authentication: Successful");
                        } else {
//                    Toast.makeText(mActivity, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void updateUI(FirebaseUser user) {
        if (user != null) {
            // Send an Intent to the next activity
            Log.v("user", "User not null");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUtil.attachListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseUtil.detachListener();
    }
}
