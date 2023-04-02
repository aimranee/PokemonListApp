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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GridItemActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView selectedImage;
    private Retrofit retrofit;
    private String name;
    //private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final static MutableLiveData<PokemonInfo> pokemonInfoLiveData = new MutableLiveData<>();
    private final static MutableLiveData<Boolean> progressBarLiveData = new MutableLiveData<>();
    private final static MutableLiveData<String> toastLiveData = new MutableLiveData<>();
    private final List<TypesResponse> typesData = new ArrayList<>();
    private final List<StatesPkemon> stateData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);

        selectedImage = (ImageView) findViewById(R.id.grid_image); // init a ImageView
        textView = (TextView) findViewById(R.id.item_name); // init a ImageView
        Intent intent = getIntent(); // get Intent which we set from Previous Activity4
        name = intent.getStringExtra("name");

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RepoServiceApi repoServiceApi = retrofit.create(RepoServiceApi.class);
        Call <PokemonInfo> pokemonInfo = repoServiceApi.pokemonInfo(name);

        pokemonInfo.enqueue(new Callback<PokemonInfo>() {

            @Override
            public void onResponse(Call<PokemonInfo> call, Response<PokemonInfo> response) {
                if(response.isSuccessful()){
                    PokemonInfo pokemonInfo = response.body();

                    String heightFormatted = String.format("%.1f M", (float) pokemonInfo.getHeight() / 10);
                    String weightFormatted = String.format("%.1f KG", (float) pokemonInfo.getWeight() / 10);

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

                    PokemonInfo pkInfo = new PokemonInfo(name, stateData, typesData, pokemonInfo.getHeight(), pokemonInfo.getHeight());
                    System.out.println(pkInfo.toString());
                    /*progressBarLiveData.setValue(false);
                    pokemonInfoLiveData.setValue(pkInfo);*/
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