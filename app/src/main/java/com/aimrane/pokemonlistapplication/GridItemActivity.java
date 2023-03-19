package com.aimrane.pokemonlistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GridItemActivity extends AppCompatActivity {

    private TextView textView;
    private int image;
    ImageView selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);
        selectedImage = (ImageView) findViewById(R.id.grid_image); // init a ImageView
        textView = (TextView) findViewById(R.id.item_name); // init a ImageView
        Intent intent = getIntent(); // get Intent which we set from Previous Activity4
        selectedImage.setImageResource(intent.getIntExtra("imgPokemen", 0));
        textView.setText(intent.getStringExtra("namePokemon"));
    }
}