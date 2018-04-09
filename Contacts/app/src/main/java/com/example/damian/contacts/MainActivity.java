package com.example.damian.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<Contact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initContacts();
        ContactAdapter adapter = new ContactAdapter(MainActivity.this, R.layout.contact_item, contactList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = contactList.get(position);
//                Toast.makeText(MainActivity.this, contact.getName(), Toast.LENGTH_SHORT).show();
                String name = contact.getName();
                String phone = contact.getPhone();
                String email = contact.getEmail();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("phone",phone);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
    }

    private void initContacts() {
        for (int i = 0; i < 6; i++) {
            Contact alex = new Contact("Alex", "123456", "123456@qq.com", R.drawable.contact);
            contactList.add(alex);
            Contact bob = new Contact("Bob", "654321", "654321@qq.com", R.drawable.contact);
            contactList.add(bob);
            Contact cat = new Contact("Cat", "987654", "987654@qq.com", R.drawable.contact);
            contactList.add(cat);
        }
    }

}
