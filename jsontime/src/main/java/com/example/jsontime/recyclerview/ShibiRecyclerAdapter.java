package com.example.jsontime.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jsontime.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class ShibiRecyclerAdapter extends RecyclerView.Adapter<ShibiRecyclerAdapter.ShibieViewHolder> {

    private Context context;
    private List<String> urls;

    public ShibiRecyclerAdapter(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
    }



    @NonNull
    @Override
    public ShibieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
 View itemView = LayoutInflater.from(context).inflate(R.layout.shibe_item_view,parent);

        return new ShibieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShibieViewHolder holder, int position) {
        String url = urls.get(position);
        holder.tvUrl.setText(url);
        Glide.with(context).load(url).into(holder.ivShibe);




    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class ShibieViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView ivShibe;
        private AppCompatTextView tvUrl;


        public ShibieViewHolder(@NonNull View itemView) {
            super(itemView);
            ivShibe = itemView.findViewById(R.id.iv_shibe);
            tvUrl = itemView.findViewById(R.id.tv_url);

        }
    }


}
