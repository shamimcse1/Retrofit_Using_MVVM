package com.example.retrofitusingmvvm.network;



import com.example.retrofitusingmvvm.model.MarvelModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("photos")
    Call<List<MarvelModel>> getMarvelList();

}
