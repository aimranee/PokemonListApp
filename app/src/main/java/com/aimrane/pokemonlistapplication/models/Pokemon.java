package com.aimrane.pokemonlistapplication.models;

import com.google.gson.annotations.SerializedName;

public class Pokemon {
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String image;
    private String height;
    private String cat;
    private String gender;

    public Pokemon(int id, String name, String image, String height, String cat, String gender) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.height = height;
        this.cat = cat;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +

                '}';
    }
}