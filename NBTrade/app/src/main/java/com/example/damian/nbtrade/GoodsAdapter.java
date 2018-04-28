package com.example.damian.nbtrade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GoodsAdapter extends ArrayAdapter<Goods> {
    private int resourseId;

    public GoodsAdapter(Context context, int textViewResourceId, List<Goods> objects) {
        super(context, textViewResourceId, objects);
        resourseId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Goods goods = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
        } else {
            view = convertView;
        }
        ImageView contactImage = (ImageView) view.findViewById(R.id.goods_image);
        TextView contactName = (TextView) view.findViewById(R.id.goods_name);
        contactImage.setImageResource(goods.getImageId());
        contactName.setText(goods.getName());
        return view;
    }
}
