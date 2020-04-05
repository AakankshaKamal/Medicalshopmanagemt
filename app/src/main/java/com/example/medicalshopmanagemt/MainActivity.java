package com.example.medicalshopmanagemt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import 	androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
 Button lobnt;
private FirebaseAuth auth;
private EditText email,pass;
private String emailid,password;
ProgressBar progressBar;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if(user!=null)
        {
            startActivity(new Intent(MainActivity.this,Dashboard.class));
        }
        progressBar=findViewById(R.id.progessbar);
        toolbar=findViewById(R.id.tool);
        email =findViewById(R.id.loginemail);
        pass = findViewById(R.id.psdlo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("LOGIN");
            toolbar.setTitleTextColor(getResources().getColor(R.color.Black));
        }
        Button reg = findViewById(R.id.btn);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
        lobnt = findViewById(R.id.login);
        lobnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                try {
                    emailid = email.getText().toString().trim();
                    password = pass.getText().toString().trim();
                }
                catch (NullPointerException e)
                { Toast.makeText(MainActivity.this,"EMAIL empty:",Toast.LENGTH_LONG).show();}

                Log.d("EMAIL :",emailid);
                Toast.makeText(MainActivity.this,"EMAIL :"+emailid,Toast.LENGTH_LONG).show();
               fnc(emailid,password);

            }
        });
    }

void fnc(String emailid,String password)
{
    auth.signInWithEmailAndPassword(emailid, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful())
            {progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"LOGEDD IN SUCCESSFULLY",Toast.LENGTH_LONG);
                Intent intent=new Intent(MainActivity.this,Dashboard.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"LOGING failed",Toast.LENGTH_LONG);
            }

        }


    });
}}