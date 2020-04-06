package com.example.medicalshopmanagemt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import 	androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
 Button lobnt,reset,sendbtn;
private FirebaseAuth auth;
private EditText email,pass,passwrdreset;
private String emailid,password;
ProgressBar progressBar;
TextView alert;

Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
reset=findViewById(R.id.pswdreset);
sendbtn=findViewById(R.id.sendbtn);
        FirebaseUser user = auth.getCurrentUser();
        if(user!=null)
        {Toast.makeText(MainActivity.this,"Email :"+user.getEmail(),Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this,Dashboard.class));
        }
        progressBar=findViewById(R.id.progessbar);
        alert=findViewById(R.id.texthere);  passwrdreset=findViewById(R.id.emailtogenerate);
        toolbar=findViewById(R.id.tool);
        email =findViewById(R.id.loginemail);
        pass = findViewById(R.id.psdlo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("ENTER THE FOLLOWING :");
            toolbar.setTitleTextColor(getResources().getColor(R.color.Black));

        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.setVisibility(View.VISIBLE);
                passwrdreset.setVisibility(View.VISIBLE);
                //sendbtn.setVisibility(View.VISIBLE);


                sendbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (passwrdreset.getText().toString().trim().equals("")) {
                            passwrdreset.setError("please enter email address");
                            passwrdreset.requestFocus();
                        } else {
                            FirebaseAuth.getInstance().sendPasswordResetEmail(passwrdreset.getText().toString().trim())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("Here", "Email sent.");
                                                alert.setText("RESET LINK SENT TO GIVEN EMAIL");
                                            }
                                        }
                                    });
                        }
                    }
                });

            }});


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
          //      progressBar.setVisibility(View.VISIBLE);

                try {
                    emailid = email.getText().toString().trim();
                    password = pass.getText().toString().trim();
                    if (password.equals("")) {
                        pass.setError("please enter password");
                        pass.requestFocus();}
                        if (emailid.equals("")) {
                            email.setError("please enter email address");
                            email.requestFocus();}
                }
                catch (NullPointerException e)
                { Toast.makeText(MainActivity.this,"EMAIL empty:",Toast.LENGTH_LONG).show();}

                Log.d("EMAIL :",emailid);
                Toast.makeText(MainActivity.this,"EMAIL :"+emailid,Toast.LENGTH_LONG).show();
                        if (!emailid.equals("") &&
                                !password.equals("")
                                )
                        {  progressBar.setVisibility(View.VISIBLE);
                            fnc(emailid,password);
                        }

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

                Toast.makeText(getApplicationContext(),"LOGEDD IN SUCCESSFULLY",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,Dashboard.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"LOGING failed",Toast.LENGTH_LONG).show();
            }

        }


    });
}

}