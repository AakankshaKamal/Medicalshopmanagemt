package com.example.medicalshopmanagemt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {
private CardView f1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    f1=findViewById(R.id.first_one);
    s2=findViewById(R.id.secondone);
    f1.setOnClickListener(this);
        s2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.first_one:
                Toast.makeText(Dashboard.this,"CLICKED ON FIRST ",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dashboard.this,QRgenerator.class));
                break;
            case R.id.secondone:
                Toast.makeText(Dashboard.this,"CLICKED ON Second ",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
