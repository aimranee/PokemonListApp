package com.aimrane.pokemonlistapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.aimrane.pokemonlistapplication.models.PokemonInfo;
import com.aimrane.pokemonlistapplication.models.PokemonsList;
import com.aimrane.pokemonlistapplication.models.StatesPkemon;
import com.aimrane.pokemonlistapplication.models.TypesResponse;
import com.aimrane.pokemonlistapplication.service.RepoServiceApi;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GridItemActivity extends AppCompatActivity {

    private TextView namePoke;
    private TextView withPoke;
    private TextView heightPoke;
    private ImageView imagePoke;
    private Retrofit retrofit;
    private PokemonInfo pkInfo;
    private PokemonsItemsAdapter pokemonsItemsAdapter;
    private int id;
    private final static MutableLiveData<PokemonInfo> pokemonInfoLiveData = new MutableLiveData<>();
    private final static MutableLiveData<Boolean> progressBarLiveData = new MutableLiveData<>();
    private final static MutableLiveData<String> toastLiveData = new MutableLiveData<>();
    private final List<TypesResponse> typesData = new ArrayList<>();
    private final List<StatesPkemon> stateData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);

        imagePoke = (ImageView) findViewById(R.id.imagePoke); // init a ImageView
        namePoke = (TextView) findViewById(R.id.namePoke); // init a ImageView
        withPoke = (TextView) findViewById(R.id.weight); // init a ImageView
        heightPoke = (TextView) findViewById(R.id.height); // init a ImageView
        Intent intent = getIntent(); // get Intent which we set from Previous Activity4
        id = intent.getIntExtra("id",0);
        Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imagePoke);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RepoServiceApi repoServiceApi = retrofit.create(RepoServiceApi.class);
        Call <PokemonInfo> pokemonInfo = repoServiceApi.pokemonInfo(id);

        pokemonInfo.enqueue(new Callback<PokemonInfo>() {

            @Override
            public void onResponse(Call<PokemonInfo> call, Response<PokemonInfo> response) {
                if(response.isSuccessful()){

                    PokemonInfo pokemonInfo = response.body();


                    //Get name of types Pokemon and Color Types
                    List<TypesResponse> typesList = pokemonInfo.getTypes();

                    for (int i = 0; i < typesList.size(); i++) {
                        TypesResponse type = typesList.get(i);
                        String nameType = type.getType().getName();
                        //System.out.println("     ::"+nameType);
                        typesData.add(new TypesResponse(nameType));
                    }

                    List<StatesPkemon> stateList = pokemonInfo.getStats();
                    for (int i = 0; i < stateList.size(); i++) {
                        StatesPkemon state = stateList.get(i);
                        String baseState = state.getBaseStat();
                        String stateName = state.getState().getName();
                        //System.out.println("    :::"+baseState);
                        stateData.add(new StatesPkemon(stateName,baseState));
                    }

                    pkInfo = new PokemonInfo(pokemonInfo.getName(), stateData, typesData, pokemonInfo.getHeight(), pokemonInfo.getHeight());
                    namePoke.setText(pkInfo.getName());
                    String heightFormatted = String.format("%.1f M", (float) pkInfo.getHeight() / 1);
                    String weightFormatted = String.format("%.1f KG", (float) pkInfo.getWeight() / 1);
                    heightPoke.setText(heightFormatted);
                    withPoke.setText(weightFormatted);
                    progressBarLiveData.setValue(false);
                    pokemonInfoLiveData.setValue(pkInfo);
                }else{
                    Log.e("Info", String.valueOf(response.code()));
                    return;
                }
            }

            @Override
            public void onFailure(Call<PokemonInfo> call, Throwable t) {
                Log.e("Info", " Error: " + t.getMessage());
            }
        });


    }
}