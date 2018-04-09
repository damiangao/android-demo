package com.example.damian.contacts;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private int resourseId;

    public ContactAdapter(Context context, int textViewResourceId, List<Contact> objects) {
        super(context, textViewResourceId, objects);
        resourseId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
        } else {
            view = convertView;
        }
        ImageView contactImage = (ImageView) view.findViewById(R.id.contact_image);
        TextView contactName = (TextView) view.findViewById(R.id.contact_name);
        contactImage.setImageResource(contact.getImageId());
        contactName.setText(contact.getName());
        return view;
    }
}