package com.aimrane.pokemonlistapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PokemonsList {
    @SerializedName("count")
    private int totalPok;
    @SerializedName("results")
    private ArrayList<Pokemon> pokemons;

    public int getTotalPok() {
        return totalPok;
    }

    public void setTotalPok(int totalPok) {
        this.totalPok = totalPok;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
