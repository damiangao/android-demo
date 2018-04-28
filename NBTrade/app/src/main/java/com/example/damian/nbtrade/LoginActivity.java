package com.example.damian.nbtrade;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private boolean showFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new MyDatabaseHelper(this,"User.db",null,5);
        Button register = findViewById(R.id.register);
        Button login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFail=true;

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                EditText account = findViewById(R.id.account);
                EditText password = findViewById(R.id.password);
                String account_string =account.getText().toString();
                String password_string = password.getText().toString();

                Cursor cursor = db.query("User",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String account_name = cursor.getString(cursor.getColumnIndex("account"));
                        String account_password =cursor.getString(cursor.getColumnIndex("password"));
                        Log.d("000", "query account "+account_name);
                        Log.d("000", "query password "+account_password);
                        if(account_name.equals(account_string)&&account_password.equals(password_string)){
                            showFail=false;
                            Intent intent = new Intent(LoginActivity.this,ListActivity.class);
                            startActivity(intent);
                        }
                    }while(cursor.moveToNext());
                    if(showFail){
                        Toast.makeText(LoginActivity.this,"Wrong account or password!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}



