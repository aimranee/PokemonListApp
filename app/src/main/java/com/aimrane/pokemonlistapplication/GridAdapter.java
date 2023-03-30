package com.aimrane.pokemonlistapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aimrane.pokemonlistapplication.models.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    Context context;
    List<String> pokemons;
    List<String> imgs;
    LayoutInflater inflater;

    public GridAdapter(Context context, List<String> pokemons, List<String> image) {
        this.context = context;
        this.pokemons = pokemons;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){

            convertView = inflater.inflate(R.layout.row_items,null);

        }

        ImageView imageView = convertView.findViewById(R.id.grid_image);
        TextView textView = convertView.findViewById(R.id.item_name);

        //imageView.setImageResource(Integer.parseInt(imgs.get(position)));
        //textView.setText(pokemons[position].getName());

        return convertView;
    }
}
