package com.example.android.googlofeee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondPage extends AppCompatActivity {
  Button bang_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        bang_button=(Button) findViewById(R.id.bang_button);
        bang_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SecondPage.this,ThirdPage.class);
                startActivity(i);
            }
        });
    }
}