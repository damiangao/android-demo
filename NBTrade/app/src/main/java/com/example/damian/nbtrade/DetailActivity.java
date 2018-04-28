package com.example.damian.nbtrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();

        String name = intent.getStringExtra("name");
        String describe = intent.getStringExtra("describe");
        Double price = intent.getDoubleExtra("price",0);
        int imageId = intent.getIntExtra("imageId",R.drawable.goods);
        int category_num = intent.getIntExtra("category",0);
        String category = "";
        if (category_num==0){
            category="全新";
        }else if (category_num==1){
            category="二手";
        }
        ImageView imageView = findViewById(R.id.img);
        imageView.setImageResource(imageId);
        TextView nameView =  findViewById(R.id.name);
        nameView.setText(name);
        TextView categoryView =  findViewById(R.id.category);
        categoryView.setText(category);
        TextView describeView =  findViewById(R.id.describe);
        describeView.setText(describe);
        TextView priceView =  findViewById(R.id.price);
        priceView.setText(Double.toString(price));
    }
}
