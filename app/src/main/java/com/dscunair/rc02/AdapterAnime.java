package com.dscunair.rc02;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import static com.dscunair.rc02.DetailActivity.EXTRA_ANIME;

public class AdapterAnime extends RecyclerView.Adapter<AdapterAnime.MyViewHolder> {
    private ArrayList<modelData>listAnime;
    Context context;


    public AdapterAnime(Context context, ArrayList<modelData> list) {
        this.context=context;
        this.listAnime = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        modelData data=listAnime.get(position);
        holder.txt_judul.setText(data.getNama());

        Glide.with(holder.itemView.getContext())
                .load(data.getPhoto())
                .apply(new RequestOptions().override(550,550))
                .into(holder.img_poster);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                context.startActivity(new Intent(context,DetailActivity.class));
                Intent intenData=new Intent(context,DetailActivity.class);
                intenData.putExtra(EXTRA_ANIME,listAnime.get(position));
                context.startActivity(intenData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAnime.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img_poster;
        private final TextView txt_judul;
        private final CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster=itemView.findViewById(R.id.item_poster);
            txt_judul=itemView.findViewById(R.id.item_judul);
            cardView=itemView.findViewById(R.id.card);
        }
    }
}
