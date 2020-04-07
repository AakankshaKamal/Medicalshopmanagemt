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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Mail_Activity extends AppCompatActivity {
    private EditText mailId;
    private EditText mEditTextMessage;
    private Button genbtn, buttonSend;
    private ImageView qrcode;
    private DatabaseReference mdatabase;
    LinearLayout idForSaveView;
    private String mCurrent;
    private FirebaseAuth mAuth;
    private String mail;
    private StorageReference QR_ref;
    private  String SaveCurTime,SaveCurDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        mCurrent=FirebaseAuth.getInstance().getCurrentUser().getUid();

        QR_ref= FirebaseStorage.getInstance().getReference().child("QR_Codes");


        mailId=findViewById(R.id.mail_id);

        mEditTextMessage = findViewById(R.id.edit_text_message);
        genbtn = findViewById(R.id.genbtn);
        qrcode = findViewById(R.id.qrcode);
        idForSaveView=(LinearLayout)findViewById(R.id.idForSaveView);
        buttonSend=findViewById(R.id.button_send);



        mdatabase=FirebaseDatabase.getInstance().getReference().child("users").child(mCurrent).child("QR_List");

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

        final Bitmap bitmap =getBitmapFromView(idForSaveView);
        try {
            File file = new File(this.getExternalCacheDir(),"logicchip.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


            intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file) );
            intent.setType("image/png");

//

            final StorageReference filePath =QR_ref.child(Uri.fromFile(file).toString() + " .jpg");
            filePath.putFile(Uri.fromFile(file)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(Mail_Activity.this, "Upload successful!", Toast.LENGTH_LONG).show();

                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String url = uri.toString();
                            mail=mailId.getText().toString();
                            mdatabase.child(mail).child("qr").setValue(url);
                            CurrentTime();

                        }
                    });
                }
            });



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


    private void CurrentTime()
    {

        Calendar calendar=Calendar.getInstance();

        SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd,yyyy");
        SaveCurDate= currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("hh:mm a");
        SaveCurTime= currentTime.format(calendar.getTime());


        mdatabase.child(mail).child("date").setValue(SaveCurDate);
        mdatabase.child(mail).child("time").setValue(SaveCurTime);


    }



}