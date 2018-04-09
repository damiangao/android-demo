package com.example.damian.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String phone=intent.getStringExtra("phone");
        String email=intent.getStringExtra("email");
        TextView nameView = (TextView) findViewById(R.id.textView);
        nameView.setText(name);
        Button phoneButton = (Button) findViewById(R.id.button);
        phoneButton.setText(phone);
        Button emailButton = (Button) findViewById(R.id.button2);
        emailButton.setText(email);
    }
}
