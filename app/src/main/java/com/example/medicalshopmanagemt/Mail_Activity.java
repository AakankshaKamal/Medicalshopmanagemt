package com.example.medicalshopmanagemt;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Mail_Activity extends AppCompatActivity {
    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;
    private Button genbtn, buttonSend;
    private ImageView qrcode;

    LinearLayout idForSaveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        mEditTextMessage = findViewById(R.id.edit_text_message);
        genbtn = findViewById(R.id.genbtn);
        qrcode = findViewById(R.id.qrcode);
        idForSaveView=(LinearLayout)findViewById(R.id.idForSaveView);
        buttonSend=findViewById(R.id.button_send);

        buttonSend = findViewById(R.id.button_send);

        genbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datatogen = mEditTextMessage.getText().toString();
                QRGEncoder qrgEncoder = new QRGEncoder(datatogen, null, QRGContents.Type.TEXT, 500);
                Bitmap qrbits = qrgEncoder.getBitmap();
                qrcode.setImageBitmap(qrbits);
            }


        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickShare(v);
            }
        });

    }




    public void OnClickShare(View view){

        Bitmap bitmap =getBitmapFromView(idForSaveView);
        try {
            File file = new File(this.getExternalCacheDir(),"logicchip.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Share image via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }



}