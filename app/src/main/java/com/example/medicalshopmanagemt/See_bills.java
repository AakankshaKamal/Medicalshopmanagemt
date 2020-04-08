package com.example.medicalshopmanagemt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class See_bills extends AppCompatActivity {

    private EditText Date,Mail;
    private Button See;

    private final List<Bill> bill_list=new ArrayList<>();
    private LinearLayoutManager manager;
    private BillAdapter adapter;
    private RecyclerView mrecycler_view;
    private String date,Current_id,mail;
private DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_bills);


        adapter=new BillAdapter(bill_list);
        mrecycler_view=findViewById(R.id.recycler);
        manager=new LinearLayoutManager(this);
        mrecycler_view.setLayoutManager(manager);
        mrecycler_view.setAdapter(adapter);

        Current_id= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        mdatabase= FirebaseDatabase.getInstance().getReference().child("users").child(Current_id).child("QR_List");

        See=findViewById(R.id.see);
        Date=findViewById(R.id.date);
        Mail=findViewById(R.id.mail);


        See.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Mail.getText().toString().equals("")) {
                    Mail.setError("please fill this field");
                    Mail.requestFocus();}
                else  if (Date.getText().toString().equals("")) {
                    Date.setError("please fill this field");
                    Date.requestFocus();}
                else
                show();
            }
        });
    }

    private void show()
    {

        date=Date.getText().toString();
        mail=Mail.getText().toString();


        mdatabase.child(mail).child(date).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

             Bill bills=dataSnapshot.getValue(Bill.class);
                bill_list.add(bills);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
