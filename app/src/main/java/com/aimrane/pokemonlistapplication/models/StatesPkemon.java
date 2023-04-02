package com.aimrane.pokemonlistapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StatesPkemon {

    private String nameState;

    @SerializedName("base_stat")
    private String baseStat;

    public StatesPkemon(String nameState, String baseStat) {
        this.nameState = nameState;
        this.baseStat = baseStat;
    }

    public String getNameState() {
        return nameState;
    }

    @SerializedName("stat")
    public StatesPkemon.State state;

    public State getState() {
        return state;
    }

    public String getBaseStat() {
        return baseStat;
    }

    public static class State {

        @SerializedName("name")
        public String name;

        public String getName() {
            return name;
        }
    }


    @Override
    public String toString() {
        return "StatesPkemon{" +
                "nameState='" + nameState + '\'' +
                ", baseStat='" + baseStat + '\'' +
                '}';
    }
}
