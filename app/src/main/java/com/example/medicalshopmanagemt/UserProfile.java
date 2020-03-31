package com.example.medicalshopmanagemt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfile extends AppCompatActivity {
private EditText useremail;
private Button btn;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

    firebaseAuth=FirebaseAuth.getInstance();
    firebaseUser=firebaseAuth.getCurrentUser();
    useremail=findViewById(R.id.label_displayEmail);
    useremail.setText(firebaseUser.getEmail());
//    btn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            FirebaseAuth.getInstance().signOut();
//            Intent intent=new Intent(UserProfile.this,MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    });
    }
}
