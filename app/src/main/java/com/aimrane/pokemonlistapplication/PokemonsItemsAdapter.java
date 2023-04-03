package com.aimrane.pokemonlistapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aimrane.pokemonlistapplication.models.Pokemon;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class PokemonsItemsAdapter extends RecyclerView.Adapter<PokemonsItemsAdapter.ViewHolder>{
    private ArrayList<Pokemon> dataset;
    private Context context;

    public PokemonsItemsAdapter(Context context)
    {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.item_name.setText(p.getName());
        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+p.getNumber()+".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.grid_image);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void listOfPokemons(ArrayList<Pokemon> pokemons){
        dataset.addAll(pokemons);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView grid_image;
        private TextView item_name;

        public ViewHolder(View itemView){
            super(itemView);
            grid_image = (ImageView) itemView.findViewById(R.id.grid_image);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

    public ArrayList<Pokemon> getDataset() {
        return dataset;
    }
}
