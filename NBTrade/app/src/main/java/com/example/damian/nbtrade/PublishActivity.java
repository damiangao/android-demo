package com.example.damian.nbtrade;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static android.graphics.BitmapFactory.*;

public class PublishActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private Spinner spinner;
    private String[] list1;
    private Button chooseImgBtn;
    private ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        dbHelper = new MyDatabaseHelper(this,"User.db",null,5);
        //spinner
        list1 = new String[]{"全新","二手"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, list1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner =  findViewById(R.id.category);
        spinner.setAdapter(adapter);

        //choose img
        chooseImgBtn = findViewById(R.id.choose_img);
        imgView = findViewById(R.id.img);

        chooseImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image:/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);
            }

        });
        Button publish = findViewById(R.id.publish);

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.name);
                Spinner category =findViewById(R.id.category);
                EditText price = findViewById(R.id.price);
                EditText describe = findViewById(R.id.describe);

                if(name.getText().length()==0||price.getText().length()==0||describe.getText().length()==0){
                    Toast.makeText(PublishActivity.this,"Please complete input.",Toast.LENGTH_SHORT).show();
                    return;
                }
                String name_string = name.getText().toString();
                int category_int = category.getSelectedItemPosition();
//                Log.d("000", Integer.toString(category.getSelectedItemPosition()));
                Double price_num = Double.parseDouble(price.getText().toString());
                String describe_string = describe.getText().toString();

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name",name_string);
                values.put("category",category_int);
                values.put("price",price_num);
                values.put("describe",describe_string);
                values.put("imageId",R.drawable.goods);

                db.insert("Goods",null,values);
                values.clear();
                Intent intent = new Intent(PublishActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Uri uri = data.getData();
            ContentResolver cr=this.getContentResolver();
            try{
                Bitmap mbitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                imgView.setImageBitmap(mbitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }


    }
}
