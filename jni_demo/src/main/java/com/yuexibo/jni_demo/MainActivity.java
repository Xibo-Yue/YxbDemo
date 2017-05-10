package com.yuexibo.jni_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_java2c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_java2c = (Button) findViewById(R.id.btn_java2c);
        btn_java2c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = new Java2JNI().java2C();
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
