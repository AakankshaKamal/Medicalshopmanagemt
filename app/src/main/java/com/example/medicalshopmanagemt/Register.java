package com.example.medicalshopmanagemt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    mauth=FirebaseAuth.getInstance();
    emailed=findViewById(R.id.email);
    passed=findViewById(R.id.epass);
progressBar=findViewById(R.id.progessbar);

        clbtn=findViewById(R.id.click);
clbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String email = emailed.getText().toString().trim();
        String password = passed.getText().toString().trim();

        //String emailAddress = etSignInEmail.getText().toString().trim();
        if (password.length() < 6) {
            passed.setError("password minimum contain 6 character");
                    passed.requestFocus();
        }
        if (passed.getText().toString().equals("")) {
            passed.setError("please enter password");
                    passed.requestFocus();
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailed.setError("please enter valid email address");
                    emailed.requestFocus();
        }
        if (emailed.getText().toString().equals("")) {
            emailed.setError("please enter email address");
                    emailed.requestFocus();
        }
        if (!email.equals("") &&
                passed.getText().toString().length() >= 6 &&
                !passed.getText().toString().trim().equals("")
                && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // do  your action

            progressBar.setVisibility(View.VISIBLE);



        mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Register.this,"REGISTERED SUCCESSFULLY",Toast.LENGTH_LONG).show();
                            Log.d("Success", "createUserWithEmail:success");
                            //FirebaseUser user = mauth.getCurrentUser();
                           // updateUI(user);

Intent intent= new Intent(Register.this, UserProfile.class);
intent.putExtra("FLAG","empty");
startActivity(intent);
finish();
                }
                else
                {
                    Log.w("Failure", "createUserWithEmail:failure", task.getException());
                          Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });




}
    }});
}}

