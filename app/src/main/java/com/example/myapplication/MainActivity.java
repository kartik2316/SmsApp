package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
 EditText text_pnu,txt_mess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_mess=(EditText)findViewById(R.id.txt_messege);
        text_pnu=(EditText)findViewById(R.id.txt_phone_number);
    }
    public void btn_send(View view)
    { int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
       if(permissionCheck == PackageManager.PERMISSION_GRANTED){
           MyMessege();
       }
       else
       {
           ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS} ,0);


       }

    }

    private void MyMessege() {
       String phonenumber = text_pnu.getText().toString().trim();
       String Messege=txt_mess.getText().toString().trim();
       if(!text_pnu.getText().toString().equals("") || !txt_mess.getText().toString().equals("")){
        SmsManager smsManager= SmsManager.getDefault();
        smsManager.sendTextMessage(phonenumber,null,Messege,null,null);
        Toast.makeText(this,"Messege sent",Toast.LENGTH_SHORT).show();}
       else
       {
           Toast.makeText(this,"Please enter messege and phone number",Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
            if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                MyMessege();
            else
                Toast.makeText(this, "You do not ", Toast.LENGTH_SHORT).show();
        }
    }
}