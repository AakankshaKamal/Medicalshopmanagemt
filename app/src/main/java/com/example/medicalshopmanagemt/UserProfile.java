package com.example.medicalshopmanagemt;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfile extends AppCompatActivity {
private EditText useremail;
private Button btn;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;

//    private String userId;
//    //private CircleImageView profilePicture;
//    private EditText name;
//    private EditText age, fathername, address, pin, phone, fax, aadhaar;
//    private Button submit;
//
//
//    RadioGroup radioGroup;
//    //User user;
//    //private static final String TAG = "AccountFragment";
//    String gender = "";
//    int flag = 0;
//
//    RadioButton lastButton;
//    RadioButton radioButton,radioButton2,radioButton3;

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
      //  profilePicture = findViewById(R.id.accountProfilePicture);
       // userId=firebaseUser.getUid();
//        name = findViewById(R.id.label_displayName);
//       // email = findViewById(R.id.label_displayEmail);
//        age = findViewById(R.id.edit_age);
//        //fathername = findViewById(R.id.edit_fathersname);
//        address = findViewById(R.id.edit_address);
//        pin = findViewById(R.id.edit_pin);
//        phone = findViewById(R.id.edit_phone);
//        //fax = findViewById(R.id.edit_fax);
//        aadhaar = findViewById(R.id.edit_aadhaar);
//        radioGroup = findViewById(R.id.radioGroup);
//        lastButton = findViewById(R.id.radio_others);




                }
            }






