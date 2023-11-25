package com.abid.reassms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView.findViewById(R.id.textView);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},
                    0);
        }
        Cursor c = getContentResolver().query(Uri.parse("content://sms/inbox"),
                null,null,null,null);
        if (c.moveToFirst()){
            do{
                String column,data ="";
                for (int i=0;i<c.getColumnCount();i++){
                    column = c.getColumnName(i);
                    if (column.equals("address")){
                        data = data + "Address : - "+c.getString(i)+"\n";
                    }
                    if (column.equals("body")){
                        data = data +"Body : -"+c.getString(i)+"\n\n";
                    }
                }
                textView.append(data);
            }
            while (c.moveToNext());
        }
    }
}
