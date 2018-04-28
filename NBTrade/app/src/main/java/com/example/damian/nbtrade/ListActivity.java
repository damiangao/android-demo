package com.example.damian.nbtrade;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private List<Goods> goods_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        dbHelper = new MyDatabaseHelper(this,"User.db",null,5);
        Button publish = findViewById(R.id.publish);

        initGoodsList();
        GoodsAdapter adapter = new GoodsAdapter(ListActivity.this, R.layout.goods_item, goods_list);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Goods goods = goods_list.get(position);
                String name = goods.getName();
                String describe = goods.getDescribe();
                Double price = goods.getPrice();
                int imageId = goods.getImageId();
                int category = goods.getCategory();
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("describe",describe);
                intent.putExtra("price",price);
                intent.putExtra("imageID",imageId);
                intent.putExtra("category",category);

                startActivity(intent);
            }
        });

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this,PublishActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initGoodsList() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Goods",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String describe = cursor.getString(cursor.getColumnIndex("describe"));
                Double price = cursor.getDouble(cursor.getColumnIndex("price"));
                int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
                int category = cursor.getInt(cursor.getColumnIndex("category"));
                Goods goods = new Goods(name,category,price,describe,imageId);
                goods_list.add(goods);

            }while(cursor.moveToNext());

        }
    }
}
