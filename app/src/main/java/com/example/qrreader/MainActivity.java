package com.example.qrreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText ed;
    Button bt;
    ImageView img;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textView);
        ed=findViewById(R.id.editText);
        bt=findViewById(R.id.button);
        img=findViewById(R.id.imageView);
    }

    public void click(View view) {
        str=ed.getText().toString().trim();
        if(!str.equals(" ")){
            new ImageDownloaderTask(img).execute("https://api.qrserver.com/v1/create-qr-code/?size=1500x1500&data="+str);
        }
        else {
            Toast.makeText(MainActivity.this,"Please write something",Toast.LENGTH_SHORT).show();
        }
    }
}
