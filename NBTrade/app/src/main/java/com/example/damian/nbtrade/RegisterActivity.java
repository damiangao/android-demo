package com.example.damian.nbtrade;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new MyDatabaseHelper(this,"User.db",null,5);
        setContentView(R.layout.activity_register);
        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText account = findViewById(R.id.account);
                EditText password = findViewById(R.id.password);
                EditText confirm_password = findViewById(R.id.confirm_password);
                String account_string = account.getText().toString();
                String password_string = password.getText().toString();
                String confirm_password_string = confirm_password.getText().toString();
                if(!password_string.equals(confirm_password_string)){
                    Toast.makeText(RegisterActivity.this,"Confirm password is different from password!",Toast.LENGTH_SHORT).show();
                }else if(account_string.equals("")||password_string.equals("")||confirm_password_string.equals("")){
                    Toast.makeText(RegisterActivity.this,"Please complete input.",Toast.LENGTH_SHORT).show();
                }else{
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("account",account_string);
                    values.put("password",password_string);

                    db.insert("User",null,values);
                    values.clear();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}
