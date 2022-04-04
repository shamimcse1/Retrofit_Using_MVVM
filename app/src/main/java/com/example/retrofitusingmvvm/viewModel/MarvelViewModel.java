package com.example.retrofitusingmvvm.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitusingmvvm.model.MarvelModel;
import com.example.retrofitusingmvvm.repository.MarvelRepository;

import java.util.List;

public class MarvelViewModel extends ViewModel {

    public MarvelRepository marvelRepository = new MarvelRepository();
    public LiveData<List<MarvelModel>> marvelData = marvelRepository.marvelLiveData;

    public void loadMarvelData() {

        marvelRepository.getMarvelList();
    }
}
