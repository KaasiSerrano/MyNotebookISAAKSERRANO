package com.example.jsontime.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jsontime.R;

import java.util.List;

public class ShibeAdapter extends BaseAdapter  {
private Context context;
private List<String> urls;
    public ShibeAdapter(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;

    }

    @Override
    public int getCount() {

        return urls.size();
    }

    @Override
    public String getItem(int position) {

        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ImageView imageView;
        TextView textView;


        view = LayoutInflater.from(context).inflate(R.layout.shibe_item_view,parent,false);

        imageView = view.findViewById(R.id.iv_shibe);
        textView = view.findViewById(R.id.tv_url);


        Glide.with(context)
                .load(urls.get(position))
                .into(imageView);

        textView.setText(urls.get(position));


        return view;
    }
}
