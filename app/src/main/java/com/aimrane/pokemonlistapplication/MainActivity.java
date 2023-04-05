package com.aimrane.pokemonlistapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.AdapterView;

import com.aimrane.pokemonlistapplication.models.Pokemon;
import com.aimrane.pokemonlistapplication.models.PokemonsList;
import com.aimrane.pokemonlistapplication.service.RepoServiceApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ArrayList<Pokemon> pkList;
    private PokemonsItemsAdapter pokemonsItemsAdapter;
    private int offset;
    private boolean done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        pokemonsItemsAdapter = new PokemonsItemsAdapter(this);
        recyclerView.setAdapter(pokemonsItemsAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (done){
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount){
                            Log.e("Info", "Fin de la list");
                            done = false;
                            offset +=20;
                            getData(offset);
                        }
                    }
                }
            }
        });


        //this.gridView = (GridView)findViewById(R.id.gridView);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        done = true;
        offset = 0;
        getData(offset);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                @Override public void onItemClick(View view, int position) {
                    // do whatever
                    Intent intent = new Intent(MainActivity.this, GridItemActivity.class);
                    //intent.putExtra("name", pkList.get(position).getName());// put url data in Intent
                    intent.putExtra("id", position+1);// put url data in Intent
                    startActivity(intent);
                }

                @Override public void onLongItemClick(View view, int position) {
                    // do whatever
                }
            })
        );
    }

    private void getData(int offset) {

        RepoServiceApi repoServiceApi = retrofit.create(RepoServiceApi.class);
        Call<PokemonsList> callPokemons = repoServiceApi.pokemonList(20, offset);

        callPokemons.enqueue(new Callback<PokemonsList>() {
            @Override
            public void onResponse(Call<PokemonsList> call, Response<PokemonsList> response) {
                done = true;
                if(response.isSuccessful()){
                    PokemonsList pokemonsList = response.body();
                    pkList = pokemonsList.getPokemons();
                    pokemonsItemsAdapter.listOfPokemons(pkList);
                }else{
                    Log.e("Info", String.valueOf(response.code()));
                    return;
                }
            }

            @Override
            public void onFailure(Call<PokemonsList> call, Throwable t) {
                done = true;
                Log.e("Info", " Error: " + t.getMessage());
            }
        });
    }
}