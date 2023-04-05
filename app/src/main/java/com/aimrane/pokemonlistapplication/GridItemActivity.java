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
import com.skydoves.progressview.ProgressView;

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
    private TextView hpVal;
    private TextView atkVal;
    private TextView defVal;
    private TextView spdVal;
    private TextView expVal;
    private TextView type1;
    private TextView type2;
    private ImageView imagePoke;
    private com.skydoves.progressview.ProgressView progressViewHp;
    private com.skydoves.progressview.ProgressView progressViewAtt;
    private com.skydoves.progressview.ProgressView progressViewDef;
    private com.skydoves.progressview.ProgressView progressViewSpd;
    private com.skydoves.progressview.ProgressView progressViewExp;
    private Retrofit retrofit;
    //private GridItemActivityBinding fragmentDetailBinding;
    private PokemonInfo pkInfo;
    private PokemonsItemsAdapter pokemonsItemsAdapter;
    private int id;
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
        progressViewHp = findViewById(R.id.progress_hp);
        progressViewAtt = findViewById(R.id.progress_atk);
        progressViewDef = findViewById(R.id.progress_def);
        progressViewSpd = findViewById(R.id.progress_spd);
        progressViewExp = findViewById(R.id.progress_exp);
        hpVal = (TextView) findViewById(R.id.hpVal);
        atkVal = (TextView) findViewById(R.id.atkVal);
        defVal = (TextView) findViewById(R.id.defVal);
        spdVal = (TextView) findViewById(R.id.spdVal);
        expVal = (TextView) findViewById(R.id.expVal);
        type2 = (TextView) findViewById(R.id.type2Poke);

        Intent intent = getIntent(); // get Intent which we set from Previous Activity4
        id = intent.getIntExtra("id",0);
        Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/"+id+".png")
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
                        if(i==0)
                            type2.setText(type.getType().getName());
                    }

                    List<StatesPkemon> stateList = pokemonInfo.getStats();
                    for (int i = 0; i < stateList.size(); i++) {
                        StatesPkemon state = stateList.get(i);
                        String baseState = state.getBaseStat();
                        String stateName = state.getState().getName();
                        //System.out.println("    :::"+baseState);
                        stateData.add(new StatesPkemon(stateName,baseState));

                        if (stateName.equals("hp")){
                            progressViewHp.setProgress(Integer.parseInt(baseState));
                            hpVal.setText(baseState+"/300");
                        }

                        if (stateName.equals("attack")) {
                            progressViewAtt.setProgress(Integer.parseInt(baseState));
                            atkVal.setText(baseState+"/300");
                        }
                        if (stateName.equals("defense")) {
                            progressViewDef.setProgress(Integer.parseInt(baseState));
                            defVal.setText(baseState+"/300");
                        }
                        if (stateName.equals("speed")) {
                            progressViewSpd.setProgress(Integer.parseInt(baseState));
                            spdVal.setText(baseState+"/300");
                        }
                    }

                    pkInfo = new PokemonInfo(pokemonInfo.getName(), stateData, typesData, pokemonInfo.getBaseExperience(), pokemonInfo.getHeight(), pokemonInfo.getHeight());
                    namePoke.setText(pkInfo.getName());
                    String heightFormatted = String.format("%.1f M", (float) pkInfo.getHeight() / 1);
                    String weightFormatted = String.format("%.1f KG", (float) pkInfo.getWeight() / 1);
                    heightPoke.setText(heightFormatted);
                    withPoke.setText(weightFormatted);
                    progressViewExp.setProgress(pokemonInfo.getBaseExperience());
                    expVal.setText(pokemonInfo.getBaseExperience()+"/1000");

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