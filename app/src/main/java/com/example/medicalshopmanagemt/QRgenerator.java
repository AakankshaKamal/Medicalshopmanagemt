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
import java.io.FileOutputStream;
import java.io.IOException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRgenerator extends AppCompatActivity {

    EditText data;
    private Button genbtn,sharebtn;
    ImageView qrcode;
    private static final int FILE_SHARE_PERMISSION=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);
    data=findViewById(R.id.data);
    genbtn=findViewById(R.id.generatebtn);
    qrcode=findViewById(R.id.qrcode);
    sharebtn=findViewById(R.id.send);
    sharebtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(ContextCompat.checkSelfPermission(QRgenerator.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            {
                // Log.e(TAG, "setxml: peremission prob");
                ActivityCompat.requestPermissions(QRgenerator.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);


            }
            else
            {
                shareQRCode();
            }


        }
    });
    genbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String datatogen=data.getText().toString();
            QRGEncoder qrgEncoder=new QRGEncoder(datatogen,null, QRGContents.Type.TEXT,500);
            Bitmap qrbits=qrgEncoder.getBitmap();
            qrcode.setImageBitmap(qrbits);
        }


    });

    }

//    private void requestpermissionnow() {
//       // if(ActivityCompat.shouldShowRequestPermissionRationale(QRgenerator.this,Manifest.permission
//        //        .WRITE_EXTERNAL_STORAGE))
//        //{Toast.makeText(QRgenerator.this, "HERE 3ll :", Toast.LENGTH_SHORT).show();
//         new AlertDialog.Builder(this).setTitle("PERMISSION NEEDED").
//                 setMessage("CLICK").setPositiveButton("OK", new DialogInterface.OnClickListener() {
//             @Override
//             public void onClick(DialogInterface dialog, int which) {
//                 ActivityCompat.requestPermissions(QRgenerator.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
//                         ,FILE_SHARE_PERMISSION);
//             }
//         }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//             @Override
//             public void onClick(DialogInterface dialog, int which) {
//                 dialog.dismiss();
//             }
//         });
//        }
//        //else
//        //{Toast.makeText(QRgenerator.this, "HERE pl :", Toast.LENGTH_SHORT).show();
//          //  ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
//            //,FILE_SHARE_PERMISSION);
//        //}
//    //}

    private void shareQRCode() {


        View content = findViewById(R.id.qrcode);
        content.setDrawingCacheEnabled(true);

        Bitmap bitmap = content.getDrawingCache();
        File root = Environment.getExternalStorageDirectory();
        File cachePath = new File(root.getAbsolutePath() + "/DCIM/Camera/image.jpg");
        try {
            cachePath.createNewFile();
            FileOutputStream ostream = new FileOutputStream(cachePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(cachePath));
        startActivity(Intent.createChooser(share,"Share via"));

    }

//        Bitmap bitmap=qrcode.getDrawingCache();
//        File file=new File(Environment.getExternalStorageState(),"bar_code.jpg");
//        try {
//            file.createNewFile();
//            FileOutputStream fileOutputStream=new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
//            fileOutputStream.close();
//            Intent intent=new Intent(Intent.ACTION_SEND);
//            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.N){
//                intent.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
//                intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(QRgenerator.this,"com.example.medicalshopmanagemt",file));
//
//            }
//            else
//            {
//                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
//            }intent.setType("image/*");
//            startActivity(intent);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

