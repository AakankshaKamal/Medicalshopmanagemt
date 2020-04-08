package com.example.medicalshopmanagemt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {
private CardView f1,s2,s3,s4,s5,s6;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.sidemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
           case R.id.dashboard:
            Toast.makeText(Dashboard.this,"YOU ARE Already on DAshboard Page",Toast.LENGTH_LONG).show();
                return true;

            case R.id.signout: {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(Dashboard.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);}
            return true;
            default:   return super.onOptionsItemSelected(item);
        }



    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    f1=findViewById(R.id.gen_qrcode);
    s2=findViewById(R.id.webpage);
    s2.setOnClickListener(this);
        s6=findViewById(R.id.list);
        s6.setOnClickListener(this);
        s3=findViewById(R.id.scanqrcode);
        s3.setOnClickListener(this);
        s4=findViewById(R.id.vieprofile);
        s4.setOnClickListener(this);
        s5=findViewById(R.id.guide);
        f1.setOnClickListener(this);
        s5.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.list:Toast.makeText(Dashboard.this,"FOR DISPLAYING LIST ",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dashboard.this,See_bills.class));
                break;
            case R.id.gen_qrcode:
                Toast.makeText(Dashboard.this,"CLICKED ON FIRST ",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dashboard.this,Mail_Activity.class));
                break;
                case R.id.scanqrcode:
                Toast.makeText(Dashboard.this,"FOR SCANNING ",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dashboard.this,QRgenerator.class));
                break;
                case R.id.webpage:
                Toast.makeText(Dashboard.this,"FOR VIWING WEB PAGE ",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dashboard.this,WEbone.class));
                break;
                case R.id.vieprofile:  Intent intent=new Intent(Dashboard.this,UserProfile.class);
                intent.putExtra("FLAG","DONE");
                startActivity(intent);
                Toast.makeText(Dashboard.this,"TO VIEW YOUR PROFILE",Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(Dashboard.this,Mail_Activity.class));
                break;


               case R.id.guide:
                Toast.makeText(Dashboard.this,"Guide",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
