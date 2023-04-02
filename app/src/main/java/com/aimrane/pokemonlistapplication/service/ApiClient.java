package com.aimrane.pokemonlistapplication.service;

import android.database.Observable;

import com.aimrane.pokemonlistapplication.models.PokemonInfo;

import retrofit2.Call;

public class ApiClient {
    private final RepoServiceApi apiService;

    public ApiClient(RepoServiceApi apiService) {
        this.apiService = apiService;
    }

    public Call<PokemonInfo> observableFetchPokemonInfo(String name) {
        return apiService.pokemonInfo(name);
    }
}
