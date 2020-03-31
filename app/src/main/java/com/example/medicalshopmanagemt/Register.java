package com.example.medicalshopmanagemt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private FirebaseAuth mauth;
private EditText emailed;
private EditText passed;
private Button clbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    mauth=FirebaseAuth.getInstance();
    emailed=findViewById(R.id.email);
    passed=findViewById(R.id.epass);


        clbtn=findViewById(R.id.click);
clbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String email = emailed.getText().toString().trim();
        String password = passed.getText().toString().trim();


        mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(Register.this,"DONE",Toast.LENGTH_LONG).show();
                            Log.d("Success", "createUserWithEmail:success");
                            FirebaseUser user = mauth.getCurrentUser();
                           // updateUI(user);

                }
                else
                {
                    Log.w("Failure", "createUserWithEmail:failure", task.getException());
                          Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }});


    }
}
