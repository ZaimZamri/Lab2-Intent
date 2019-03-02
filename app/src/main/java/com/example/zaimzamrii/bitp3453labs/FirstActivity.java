package com.example.zaimzamrii.bitp3453labs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class FirstActivity extends AppCompatActivity {

    TextView txtvwAge;
    EditText edtName,edtYear;
    Button btnClick;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        txtvwAge = (TextView) findViewById(R.id.txtvwAge);
        edtName = (EditText) findViewById(R.id.edtTxtName);
        edtYear = (EditText) findViewById(R.id.edtYear);
        img = (ImageView) findViewById(R.id.imageView);




    }

    public void fnGreet(View vw){

        String strName = edtName.getText().toString();
        txtvwAge.setText("Helllo and Welcome "+ strName );

    }

    public void fnThreadActivity(View vw){
        Intent intent = new Intent(this,ThreadedActivity.class);
        //calculate Date

        Date date = new Date();

        int year = Integer.parseInt(edtYear.getText().toString());

        String strMsg = ((EditText) findViewById(R.id.edtTxtName)).getText().toString();
        intent.putExtra("varstr1",strMsg);
        intent.putExtra("date",getAge(year,1,1));
//        startActivity(intent);
        startActivityForResult(intent,1);
    }


    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }

    protected void onActivityResult(int rqCode,int resultCode,Intent test){
        super.onActivityResult(rqCode,resultCode,test);

        /*Bitmap bp = (Bitmap) test.getExtras().get("data");
        img.setImageBitmap(bp);*/

        byte[] byteArray = test.getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        img.setImageBitmap(bmp);



    }

}
