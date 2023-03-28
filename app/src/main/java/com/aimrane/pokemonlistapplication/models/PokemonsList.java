package com.aimrane.pokemonlistapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PokemonsList {
    @SerializedName("count")
    private int totalPok;
    @SerializedName("results")
    private List<Pokemon> pokemons = new ArrayList<>();

    public PokemonsList() {
    }

    public int getTotalPok() {
        return totalPok;
    }

    public void setTotalPok(int totalPok) {
        this.totalPok = totalPok;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
