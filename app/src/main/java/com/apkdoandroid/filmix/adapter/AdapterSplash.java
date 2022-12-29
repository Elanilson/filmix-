package com.apkdoandroid.filmix.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apkdoandroid.filmix.R;

import com.flaviofaria.kenburnsview.KenBurnsView;


public class AdapterSplash extends RecyclerView.Adapter<AdapterSplash.MyViewHoldwer> {

    @NonNull
    @Override
    public MyViewHoldwer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View item= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_container,parent,false);
        return new MyViewHoldwer(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoldwer holder, int position) {
        //Tema tema = temas.get(position);
        holder.kbLocation.setImageResource(R.drawable.wallpaper);
        //Picasso.get().load(R.drawable.teste).into(holder.kbLocation);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class MyViewHoldwer extends RecyclerView.ViewHolder{
        private KenBurnsView kbLocation;
        public MyViewHoldwer(@NonNull View itemView) {
            super(itemView);
            kbLocation = itemView.findViewById(R.id.kblocation);

        }
    }

}
