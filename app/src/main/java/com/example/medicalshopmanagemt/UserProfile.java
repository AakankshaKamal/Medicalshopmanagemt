package com.example.medicalshopmanagemt;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
private EditText useremail;
private Button sharebtn,updatebtn,okbtn;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
Menu menus;


    private String userId;
    //private CircleImageView profilePicture;
    private EditText name;
    private EditText age, fathername, address, pin, phone, fax, aadhaar;
    private Button submit;

String user_name;
    RadioGroup radioGroup;
    User user;
    //private static final String TAG = "AccountFragment";
    String gender = "";
    int flag = 0;
private
    RadioButton lastButton;
    RadioButton radioButton,radioButton2,radioButton3;


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater=getMenuInflater();
//        menuInflater.inflate(R.menu.sidemenu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//       switch(item.getItemId())
//       {
//           case R.id.dashboard:startActivity(new Intent(UserProfile.this,Dashboard.class));
//           return true;
//
//           case R.id.signout: {FirebaseAuth.getInstance().signOut();
//            Intent intent=new Intent(UserProfile.this,MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);}
//            return true;
//       default:   return super.onOptionsItemSelected(item);
//       }
//
//
//
//    }

    @Override
    public void onBackPressed() {
Toast.makeText(UserProfile.this,"YOU CANNOT GO BACK",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        submit=findViewById(R.id.submitbtn);
    firebaseAuth=FirebaseAuth.getInstance();
    firebaseUser=firebaseAuth.getCurrentUser();
    useremail=findViewById(R.id.label_displayEmail);
    useremail.setText(firebaseUser.getEmail());
sharebtn=findViewById(R.id.sharebtn);
okbtn=findViewById(R.id.okbtn);
updatebtn=findViewById(R.id.updatebtn);

        name = findViewById(R.id.label_displayName);
       // email = findViewById(R.id.label_displayEmail);

        age = findViewById(R.id.edit_age);
        //fathername = findViewById(R.id.edit_fathersname);
        address = findViewById(R.id.edit_address);
        pin = findViewById(R.id.edit_pin);
        phone = findViewById(R.id.edit_phone);
        //fax = findViewById(R.id.edit_fax);
        aadhaar = findViewById(R.id.edit_aadhaar);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton=findViewById(R.id.radio_male);
        radioButton2=findViewById(R.id.radio_female);
        lastButton = findViewById(R.id.radio_others);
      Intent intent=getIntent();
//get the attached extras from the intent
//we should use the same key as we used to attach the data.
    // user_name = intent.getStringExtra("FLAG");
      //  if(user_name.equals("EMPTY"))
       // {flag=1;
user_name=intent.getStringExtra("FLAG");
    //}
        if(user_name.equals("empty"))
        {flag=1;}

        if(flag==1) {
            sharebtn.setEnabled(false);
        okbtn.setEnabled(false);
        updatebtn.setEnabled(false);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {





            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                gender = radioButton.getText().toString();
                Log.d("gener :",gender);

            }
        });
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        if(flag==0) {
            DatabaseReference ref = database.getReference("users/" + firebaseUser.getUid());
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    Log.d("AADHAR", user.getAadhaar());
                    aadhaar.setText(user.getAadhaar());
                    Log.d("Name", user.getName());
                    name.setText(user.getName());
                    Log.d("Age", user.getAge());
                    age.setText(user.getAge());
                    Log.d("email", user.getEmail());
                    pin.setText(user.getPincode());
                    phone.setText(user.getPhone());
                    address.setText(user.getAddress());
                    String gender=user.getGender();
                    if(gender.equals("Male"))
                    {

                        radioButton.setChecked(true);
                        radioButton2.setEnabled(false);
                        lastButton.setEnabled(false);

                    }else if(gender.equals("Female"))
                    {

                        radioButton2.setChecked(true);
                        radioButton.setEnabled(false);
                        lastButton.setEnabled(false);

                    }
                    else
                    {
                        lastButton.setChecked(true);
                        radioButton2.setEnabled(false);
                        radioButton.setEnabled(false);

                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
updatebtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        submit.setVisibility(View.VISIBLE);
        age.setEnabled(true);
        aadhaar.setEnabled(true);
        lastButton.setEnabled(true);
        radioButton.setEnabled(true);
        radioButton2.setEnabled(true);
        name.setEnabled(true);
        pin.setEnabled(true);
        phone.setEnabled(true);
        address.setEnabled(true);
    }
});
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s ="NAME :";
                       s+= name.getText().toString();
                s=s+"\n";
                s+="EMAIL : "+useremail.getText().toString()+"\n";
                s+="AADHAR NO :" +aadhaar.getText().toString();
                s=s+"\n";
                s=s+"CONTACT NO :"+phone.getText().toString();
                s=s+"\n";
                s=s+"PINCODE OF MY AREA "+pin.getText().toString();
                s=s+"\n";
                s=s+"AGE "+age.getText().toString();
                s=s+"\n";
                s=s+"OUR ADDRESS "+address.getText().toString();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(sharingIntent, "Share text via"));
            }
        });

okbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(UserProfile.this,Dashboard.class);
        startActivity(intent);
        finish();
    }
});
age.setEnabled(false);
        aadhaar.setEnabled(false);
        name.setEnabled(false);
        pin.setEnabled(false);
        phone.setEnabled(false);
        address.setEnabled(false);

        if(flag==1)
        { submit.setVisibility(View.VISIBLE);
            age.setEnabled(true);
            aadhaar.setEnabled(true);
            name.setEnabled(true);
            pin.setEnabled(true);
            phone.setEnabled(true);
            address.setEnabled(true);}
submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
int ar=1;
        String nameuser,ageuser,addressuser,pinuser,phoneno,adharno;
        nameuser=name.getText().toString().trim();
        ageuser=age.getText().toString().trim();
        addressuser=address.getText().toString().trim();
        pinuser=pin.getText().toString().trim();
        phoneno=phone.getText().toString().trim();
        adharno=aadhaar.getText().toString().trim();
        if (nameuser.equals("")) {
            name.setError("please fill this field");
            name.requestFocus();ar=0;}
        if (ageuser.equals("")) {
            age.setError("please fill this field");
            age.requestFocus();ar=0;}
        if (addressuser.equals("")) {
            address.setError("please fill this field");
            address.requestFocus();ar=0;}
        if (pinuser.equals("")) {
            pin.setError("please fill this field");
            pin.requestFocus();ar=0;}
//        if (gender.equals("")) {
//            radioButton.setError("please enter password");
//            radioButton.requestFocus();}
        if (adharno.equals("")) {
            aadhaar.setError("please fill this field");
            aadhaar.requestFocus();
        ar=0;}
        String uid=firebaseUser.getUid();
if(ar==1)
{User users=new User(nameuser,firebaseUser.getEmail(),uid,gender,addressuser,ageuser,pinuser,phoneno,adharno,flag);
        users.setName(nameuser);
        users.setAadhaar(adharno);
        users.setAddress(addressuser);
        users.setAge(ageuser);
        users.setPhone(phoneno);
        users.setUserId(uid);
        users.setCheck(flag);

        Toast.makeText(getApplicationContext(),"NAME :"+users.getName()+" AGE :"+users.getAge(),Toast.LENGTH_LONG).show();


        final DatabaseReference mRootRef ;
        mRootRef = FirebaseDatabase.getInstance().getReference();

        mRootRef.child("users").child(uid).setValue(users).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(UserProfile.this,"DATA SUCCESSFULLY ADDED",Toast.LENGTH_SHORT).show();
                submit.setEnabled(false);
                startActivity(new Intent(UserProfile.this,Dashboard.class));
            }
        });}
    }
});


            }
            }






