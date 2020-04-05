package com.example.medicalshopmanagemt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
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
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

public class QRgenerator extends AppCompatActivity {

    EditText data;
    private Button genbtn, sharebtn;
    ImageView qrcode;
    String imgBitmapPath;
    Uri imgUri;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    static boolean save;
    String result, datatogen;
    Bitmap qrbits;
    File photo;
    private static final int FILE_SHARE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);
        data = findViewById(R.id.data);
        genbtn = findViewById(R.id.generatebtn);
        qrcode = findViewById(R.id.qrcode);
        sharebtn = findViewById(R.id.send);
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("click", "onClick: ");
//Bitmap bitmap=getBitmapfromView(qrcode);
//try{
//   // String storageState = Environment.getExternalStorageState();
//    //if (storageState.equals(Environment.MEDIA_MOUNTED)) {
//        File file = new File(QRgenerator.this.getExternalCacheDir(), "logicchip.png");
//        FileOutputStream fOut=new FileOutputStream(file);
//        fOut.flush();
//        fOut.close();
//        file.setReadable(true,false);
//        final Intent intent=new Intent(Intent.ACTION_SEND);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
//        intent.setType("image/png");
//        startActivity(Intent.createChooser(intent,"SHARE VIA "));
//
//
//
//
//            } catch (FileNotFoundException e) {
//    e.printStackTrace();
//} catch (IOException e) {
//    e.printStackTrace();
//}
            }});


        genbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datatogen = data.getText().toString();
                QRGEncoder qrgEncoder = new QRGEncoder(datatogen, null, QRGContents.Type.TEXT, 500);
                qrbits = qrgEncoder.getBitmap();
                qrcode.setImageBitmap(qrbits);

                QRGSaver qrgSaver = new QRGSaver();
                qrgSaver.save(savePath, data.getText().toString().trim(), qrbits, QRGContents.ImageType.IMAGE_JPEG);

            }


        });

    }

    private Bitmap getBitmapfromView(View view) {
    Bitmap returnedBitmap=Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);
    Canvas canvas=new Canvas(returnedBitmap);
    Drawable bgdrawable=view.getBackground();
    if(bgdrawable!=null)
    {
        bgdrawable.draw(canvas);
    }
    else
        canvas.drawColor(Color.WHITE);
    view.draw(canvas);
    return  returnedBitmap;



    }


}