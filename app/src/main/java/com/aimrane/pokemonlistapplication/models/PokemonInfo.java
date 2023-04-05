package com.aimrane.pokemonlistapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.SplittableRandom;

public class PokemonInfo {
    public PokemonInfo() {
    }

    public final int maxHP = 300;
    public final int maxATK = 300;
    public final int maxDEF = 300;
    public final int maxSPD = 300;
    public final int maxEXP = 1000;

    private String name;

    @SerializedName("stats")
    private List<StatesPkemon> stats;
    @SerializedName("types")
    private List<TypesResponse> types;
    @SerializedName("base_experience")
    private int baseExperience;

    @SerializedName("height")
    private int height;
    public String heightFormatted;

    @SerializedName("weight")
    private int weight;
    public String weightFormatted;

    public int getBaseExperience() {
        return baseExperience;
    }

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

    public PokemonInfo(String name, List<StatesPkemon> stats, List<TypesResponse> types, int baseExperience, int height, int weight) {
        this.name = name;
        this.stats = stats;
        this.types = types;
        this.baseExperience = baseExperience;
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
