package com.example.medicalshopmanagemt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileOutputStream;

public class ViewProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
       FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
       FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        DatabaseReference ref = database.getReference("users/"+firebaseUser.getUid());

// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
               // System.out.println(post);
                if (user.getAadhaar().equals(""))
                {}
                else
                    Log.d("AADHAR",user.getAadhaar());

                if (user.getName().equals(""))
                {}
                else
                    Log.d("Name", user.getName());

                if (user.getAge().equals(""))
                {}
                else     Log.d("Age", user.getAge());
                    //age.setText(user.getAge());
                Log.d("email", user.getEmail());
                if (user.getPincode().equals("")) {
                    //    pin.setError("please enter email address");
                    //  pin.requestFocus();}
                }else
                 //   pin.setText(user.getPincode());
                if (user.getPhone().equals("")) {
                    // phone.setError("please enter email address");
                } //phone.requestFocus();}
                else
                    //phone.setText(user.getPhone());
                if (user.getAddress().equals("")) {
                    //   address.setError("please enter email address");
                }// address.requestFocus();}
                else {}
                   // address.setText(user.getAddress());



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
