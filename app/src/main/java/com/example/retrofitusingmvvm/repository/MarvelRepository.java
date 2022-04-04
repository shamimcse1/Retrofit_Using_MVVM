package com.example.retrofitusingmvvm.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitusingmvvm.model.MarvelModel;
import com.example.retrofitusingmvvm.network.ApiInterface;
import com.example.retrofitusingmvvm.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarvelRepository {
    private ApiInterface apiInterface;

    private MutableLiveData mutableLiveData = new MutableLiveData<List<MarvelModel>>();
    public LiveData<List<MarvelModel>> marvelLiveData = mutableLiveData;

    public void getMarvelList() {

        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);

        Call<List<MarvelModel>> call = apiInterface.getMarvelList();

        call.enqueue(new Callback<List<MarvelModel>>() {
            @Override
            public void onResponse(Call<List<MarvelModel>> call, Response<List<MarvelModel>> response) {
                if (response.code() == 200 && response.body() != null) {
                    mutableLiveData.postValue(response.body());
                } else {
                    mutableLiveData.postValue(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<MarvelModel>> call, Throwable t) {

            }
        });
    }
}
