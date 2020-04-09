package com.example.medicalshopmanagemt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Guide extends AppCompatActivity {
TextView web ,app;
Button for_web,for_app,hw,happ;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
    web=findViewById(R.id.web_guide);
    web.setMovementMethod(new ScrollingMovementMethod());
    for_web=findViewById(R.id.btnweb);
    hw=findViewById(R.id.hideweb);
    hw.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hw.setVisibility(View.GONE);
            web.setVisibility(View.GONE);
        }
    });
    for_web.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            web.setVisibility(View.VISIBLE);
            hw.setVisibility(View.VISIBLE);
        }
    });
    app=findViewById(R.id.app_guide);
    app.setMovementMethod(new ScrollingMovementMethod());
    for_app=findViewById(R.id.btnapp);
    happ=findViewById(R.id.hideapp);
    happ.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            happ.setVisibility(View.GONE);
            app.setVisibility(View.GONE);
        }
    });
    for_app.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            app.setVisibility(View.VISIBLE);
            happ.setVisibility(View.VISIBLE);
        }
    });

    }


}
