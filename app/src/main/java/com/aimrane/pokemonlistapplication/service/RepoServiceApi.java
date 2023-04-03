package com.aimrane.pokemonlistapplication.service;

import android.database.Observable;

import com.aimrane.pokemonlistapplication.models.PokemonInfo;
import com.aimrane.pokemonlistapplication.models.PokemonsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RepoServiceApi {

    @GET("pokemon")
    public Call<PokemonsList> pokemonList(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call <PokemonInfo> pokemonInfo(@Path("id") int id);
}
