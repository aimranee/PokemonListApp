package com.aimrane.pokemonlistapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.SplittableRandom;

public class PokemonInfo {

    private String name;

    @SerializedName("stats")
    private List<StatesPkemon> stats;
    @SerializedName("types")
    private List<TypesResponse> types;

    @SerializedName("height")
    private int height;
    public String heightFormatted;

    @SerializedName("weight")
    private int weight;
    public String weightFormatted;

    public String getName() {
        return name;
    }

    public List<TypesResponse> getTypes() {
        return types;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public List<StatesPkemon> getStats() {
        return stats;
    }

    public PokemonInfo(String name, List<StatesPkemon> stats, List<TypesResponse> types, int height, int weight) {
        this.name = name;
        this.stats = stats;
        this.types = types;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PokemonInfo{" +
                "name='" + name + '\'' +
                ", stats=" + stats +
                ", types=" + types +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
