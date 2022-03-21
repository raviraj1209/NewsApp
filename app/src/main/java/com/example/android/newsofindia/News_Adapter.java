package com.example.android.newsofindia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;


import java.util.List;
public class News_Adapter extends ArrayAdapter<NewsModel> {


    public News_Adapter(@NonNull Context context, List<NewsModel> list) {
        super(context, 0,list);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listView = convertView;
        if (listView==null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.recycle_view,parent,false);
        }

        NewsModel newsModel = getItem(position);
        TextView title = listView.findViewById(R.id.tittle);
        title.setText(newsModel.getTitle());

        TextView desc = listView.findViewById(R.id.content);
        desc.setText(newsModel.getDesc());

        ImageView img = listView.findViewById(R.id.imageView5);
        Picasso.get().load(newsModel.getNews_image()).into(img);

        return listView;
    }
}
