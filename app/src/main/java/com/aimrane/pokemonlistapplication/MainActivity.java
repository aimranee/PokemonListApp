package com.aimrane.pokemonlistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.gridView = (GridView)findViewById(R.id.gridView);

        Pokemon[] pokemons = new Pokemon[]{
                //new Pokemon(1,"khlkj", 1, "yuiop","rtyu","fdgdf"),
                new Pokemon(2,"khlkj", 2, "yuiop","rtyu","fdgdf"),
                new Pokemon(3,"khlkj", 3, "yuiop","rtyu","fdgdf"),
                new Pokemon(4,"khlkj", 4, "yuiop","rtyu","fdgdf"),
                new Pokemon(3,"khlkj", 3, "yuiop","rtyu","fdgdf"),
                new Pokemon(4,"khlkj", 4, "yuiop","rtyu","fdgdf"),
                new Pokemon(3,"khlkj", 3, "yuiop","rtyu","fdgdf"),
                new Pokemon(4,"khlkj", 4, "yuiop","rtyu","fdgdf"),
                new Pokemon(5,"khlkj", 5, "yuiop","rtyu","fdgdf")
                //new Pokemon(6,"khlkj", 6, "yuiop","rtyu","fdgdf"),
                //new Pokemon(7,"khlkj", 7, "yuiop","rtyu","fdgdf")
        };
        int [] imgs = {R.drawable._02,R.drawable._03,R.drawable._04,R.drawable._07,R.drawable._02,R.drawable._03,R.drawable._04,R.drawable._07};
        //ArrayAdapter<Pokemon> arrayAdapter = new ArrayAdapter<Pokemon>(this, android.R.layout.simple_list_item_1, pokemons);
        GridAdapter gridAdapter = new GridAdapter(MainActivity.this, pokemons, imgs);

        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                Intent intent = new Intent(MainActivity.this, GridItemActivity.class);
                intent.putExtra("namePokemon", pokemons[position].toString());
                intent.putExtra("imgPokemen", imgs[position]);// put image data in Intent
                startActivity(intent); // start Intent
            }
        });
    }
}