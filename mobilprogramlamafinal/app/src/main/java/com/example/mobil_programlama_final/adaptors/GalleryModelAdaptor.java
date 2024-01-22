package com.example.mobil_programlama_final.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobil_programlama_final.Model.GalleryModel;
import com.example.mobil_programlama_final.R;

import java.util.ArrayList;

public class GalleryModelAdaptor extends RecyclerView.Adapter<GalleryModelAdaptor.ItemHolder> {
    private final ArrayList<GalleryModel> cards;
    private Context context;

    public GalleryModelAdaptor(Context context, ArrayList<GalleryModel> cards) {
        this.cards = cards;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_model, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        position = holder.getAdapterPosition();
        Glide.with(context).load(cards.get(position).getImgUrl()).into(holder.imgUrl);
        holder.userName.setText(cards.get(position).getUserName());
        holder.label.setText(cards.get(position).getLabel());
        holder.description.setText(cards.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        TextView userName, label, description;
        ImageView imgUrl;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.cardUsername);
            label = itemView.findViewById(R.id.cardLabel);
            description = itemView.findViewById(R.id.cardDescription);
            imgUrl = itemView.findViewById(R.id.cardImg);

        }
    }
}
