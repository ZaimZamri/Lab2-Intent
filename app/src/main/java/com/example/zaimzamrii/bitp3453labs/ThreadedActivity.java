package com.example.zaimzamrii.bitp3453labs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class ThreadedActivity extends AppCompatActivity {
    TextView tv1;
    ImageView iv;
    Button btnTake;
    Bitmap img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaded);

        btnTake = (Button) findViewById(R.id.btnTakePic);
    iv = (ImageView) findViewById(R.id.imageView1);
        Intent intent = getIntent();
        String strMsg = intent.getStringExtra("varstr1");
        String year = intent.getStringExtra("date");


         tv1 = (TextView) findViewById(R.id.txtVwHello);
        tv1.setText("Welcome to new Activity "+strMsg+" You are "+year+" Years Old");

        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnTakePicture();
            }
        });

    }

    public void fnTakePicture(){
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);


                Log.i("Test2","Sini");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText(tv1.getText().toString()+".. This is your picture");

                    }
                });
            }
        };
        Thread th = new Thread(run);
        th.start();
    }

    protected void onActivityResult(int rqCode,int resultCode,Intent test){
        super.onActivityResult(rqCode,resultCode,test);

        Bitmap bp = (Bitmap) test.getExtras().get("data");
        iv.setImageBitmap(bp);

/*        Intent intent=new Intent(this,FirstActivity.class);
        intent.putExtra("image","test");
        setResult(1,intent);
        finish();*/


        //
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        Log.i("SIZE",String.valueOf(byteArray.length));
        Intent intent=new Intent(this,FirstActivity.class);
        intent.putExtra("image",byteArray);
        setResult(1,intent);
        finish();



    }
}
