package com.aimrane.pokemonlistapplication.service;

import android.annotation.SuppressLint;
import android.database.Observable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aimrane.pokemonlistapplication.models.PokemonInfo;
import com.aimrane.pokemonlistapplication.models.TypesResponse;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class PokemonRepos {

    private final List<TypesResponse> typesData = new ArrayList<>();
    //private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final static MutableLiveData<PokemonInfo> pokemonInfoLiveData = new MutableLiveData<>();
    private final static MutableLiveData<Boolean> progressBarLiveData = new MutableLiveData<>();
    private final static MutableLiveData<String> toastLiveData = new MutableLiveData<>();
    public void fetchPokemonInfo(String namePoke) {
        progressBarLiveData.setValue(true);

        //Call<PokemonInfo> pokemonInfoAPIObservable = apiClient.observableFetchPokemonInfo(namePoke);

        /*Disposable disposableFetchData = pokemonInfoAPIObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(pokemonInfoAPIObserver);

        compositeDisposable.add(disposableFetchData);*/
    }

    /*private DisposableObserver<PokemonInfo> getPokemonInfoAPIObserver(String namePoke) {
        return new DisposableObserver<PokemonInfo>() {
            @Override
            public void onNext(PokemonInfo pokemonInfo) {
                onResponseSuccess(pokemonInfo, namePoke);
            }

            @Override
            public void onError(Throwable e) {
                onResponseFail(e, namePoke);
            }

            @Override
            public void onComplete() {

            }
        };
    }*/

    /*private void onResponseSuccess(PokemonInfo pokemonInfoAPI, String namePoke) {
        //Get these info: weight, height
        @SuppressLint("DefaultLocale") String heightFormatted = String.format("%.1f M", (float) pokemonInfoAPI.getHeight() / 10);
        @SuppressLint("DefaultLocale") String weightFormatted = String.format("%.1f KG", (float) pokemonInfoAPI.getWeight() / 10);

        //Get Base Performance

        //Get name of types Pokemon and Color Types
        List<TypesResponse> typesList = pokemonInfoAPI.getTypes();

        for (int i = 0; i < typesList.size(); i++) {
            TypesResponse type = typesList.get(i);

            String nameType = type.getType().getName();

            typesData.add(new TypesResponse(nameType));
        }

        public static LiveData<PokemonInfo> getPokemonInfoLiveData() {
            return pokemonInfoLiveData;
        }

        public static LiveData<Boolean> getProgressBarLiveData() {
            return progressBarLiveData;
        }

        public static LiveData<String> getToastLiveData() {
            return toastLiveData;
        }

        progressBarLiveData.setValue(false);
        //pokemonInfoLiveData.setValue(pokemonInfo);
    }*/
    private void onResponseSuccess(PokemonInfo pokemonInfoAPI, String namePoke) {
        //Get these info: weight, height
        @SuppressLint("DefaultLocale") String heightFormatted = String.format("%.1f M", (float) pokemonInfoAPI.getHeight() / 10);
        @SuppressLint("DefaultLocale") String weightFormatted = String.format("%.1f KG", (float) pokemonInfoAPI.getWeight() / 10);

        //Get Base Performance
        /*Float hpFormatted = (float) pokemonInfoAPI.hp;
        Float atkFormatted = (float) pokemonInfoAPI.atk;
        Float defFormatted = (float) pokemonInfoAPI.def;
        Float spdFormatted = (float) pokemonInfoAPI.spd;
        Float expFormatted = (float) pokemonInfoAPI.exp;

        String hpString = pokemonInfoAPI.hpString;
        String atkString = pokemonInfoAPI.atkString;
        String defString = pokemonInfoAPI.defString;
        String spdString = pokemonInfoAPI.spdString;
        String expString = pokemonInfoAPI.expString;*/

        //Get name of types Pokemon and Color Types
        List<TypesResponse> typesList = pokemonInfoAPI.getTypes();

        for (int i = 0; i < typesList.size(); i++) {
            TypesResponse type = typesList.get(i);

            String nameType = type.getType().getName();

            typesData.add(new TypesResponse(nameType));
        }

        /*PokemonInfo pokeInfo = new PokemonInfo(namePoke, typesData, heightFormatted, weightFormatted,
                hpFormatted, atkFormatted, defFormatted, spdFormatted, expFormatted,
                hpString, atkString, defString, spdString, expString);*/

        /*progressBarLiveData.setValue(false);
        pokemonInfoLiveData.setValue(pokeInfo);*/
    }
}
