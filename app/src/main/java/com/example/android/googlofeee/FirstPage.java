package com.example.android.googlofeee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstPage extends AppCompatActivity {
    Button entry_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        entry_button= (Button) findViewById(R.id.entry_button);
        entry_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FirstPage.this,SecondPage.class);
                startActivity(i);
            }
        });
    }
}
