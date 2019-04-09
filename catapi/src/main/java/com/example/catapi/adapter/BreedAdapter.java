package com.example.catapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.catapi.R;
import com.example.catapi.model.Breed;
import com.example.catapi.model.Image;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class BreedAdapter extends RecyclerView.Adapter<BreedAdapter.BreedViewHolder> {
TextView tvBreedName;
    private Context context;
    private List<Breed> breeds;
    private List<Image> images;


    public BreedAdapter(Context context, List<Breed> breeds) {
        this.context = context;
        this.breeds = breeds;
    }

    @NonNull
    @Override
    public BreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.breed_item_view, parent, false);
        return new BreedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedViewHolder holder, int position) {
        Breed breed = breeds.get(position);
        Image image = images.get(position);
        holder.tvBreedName.setText(breed.getName());
        holder.tv_Description.setText(breed.getDescription());
        holder.tv_Origin.setText(breed.getOrigin());

        ImageView Breed_Image;

        Breed_Image = this.findViewById(R.id.iv_breed_image);

        Glide.with(context).load(urls.get(position)).into(Breed_Image);

    }

    @Override
    public int getItemCount() {
        return breeds.size();
    }

    public void setData(List<Breed> breeds) {
        this.breeds = breeds;
        //notifyDataSetChanged();
        notifyItemRangeChanged(0, breeds.size());
    }

    class BreedViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvBreedName;
        AppCompatTextView tv_Description;
        AppCompatTextView tv_Origin;
        BreedViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBreedName = itemView.findViewById(R.id.tv_Breed_Name);
            tv_Description = itemView.findViewById(R.id.tv_Description_);
            tv_Origin = itemView.findViewById(R.id.Breed_Origin);





        }
    }
}




