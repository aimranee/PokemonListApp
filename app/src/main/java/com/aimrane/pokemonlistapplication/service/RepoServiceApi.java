package com.aimrane.pokemonlistapplication.service;

import com.aimrane.pokemonlistapplication.models.PokemonsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepoServiceApi {

    @GET("pokemon")
    public Call<PokemonsList> pokemonList(@Query("limit") int limit, @Query("offset") int offset);
}
