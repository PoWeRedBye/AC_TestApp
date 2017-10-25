package com.example.maxim_ozarovskiy.artcoraltestapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.dinoExample.maxim_ozarovskiy.artcoraltestapp.R;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoView.Dino;
import com.squareup.picasso.Picasso;

/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */

public class DinoListAdapter extends RecyclerView.Adapter<DinoListAdapter.ViewHolder> {

    private Context context;
    private List<Dino> dinoList;

    public DinoListAdapter(Context context, List<Dino> dinoList){
        this.context = context;
        this.dinoList = dinoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(dinoList.get(position).getDino().getDinoTitle());
        holder.description.setText(dinoList.get(position).getDino().getDinoAbout());
        String dinoImage = dinoList.get(position).getDino().getDinoImage().getSrc();
        if (dinoImage != null){
            Picasso.with(context).load(dinoImage).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return dinoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView description;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image_item);
            name = (TextView) v.findViewById(R.id.dino_name_item);
            description = (TextView) v.findViewById(R.id.description_item);

        }
    }
}
