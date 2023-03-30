package com.aimrane.pokemonlistapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aimrane.pokemonlistapplication.models.Pokemon;

import java.util.ArrayList;

public class PokemonsItemsAdapter extends RecyclerView.Adapter<PokemonsItemsAdapter.ViewHolder>{
    private ArrayList<Pokemon> dataset;

    public PokemonsItemsAdapter() {
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.item_name.setText(p.getName());
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
}
