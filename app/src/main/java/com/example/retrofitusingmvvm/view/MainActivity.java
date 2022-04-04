package com.example.retrofitusingmvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.retrofitusingmvvm.R;
import com.example.retrofitusingmvvm.adapter.MarvelAdapter;
import com.example.retrofitusingmvvm.databinding.ActivityMainBinding;
import com.example.retrofitusingmvvm.model.MarvelModel;
import com.example.retrofitusingmvvm.viewModel.MarvelViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MarvelViewModel marvelViewModel;
    private ActivityMainBinding binding;
    public List<MarvelModel> marvelModelList;
    private MarvelAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        marvelViewModel = new ViewModelProvider(this).get(MarvelViewModel.class);
        marvelViewModel.loadMarvelData();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                marvelViewModel.loadMarvelData();
            }
        });

        initView();
        getAllData();

    }

    private void initView() {
        binding.Recyclerview.setHasFixedSize(true);
        binding.Recyclerview.setLayoutManager(new LinearLayoutManager(this));
        marvelModelList = new ArrayList<>();
        adapter = new MarvelAdapter(this, marvelModelList);

    }

    private void getAllData() {

        marvelViewModel.marvelData.observe(this, new Observer<List<MarvelModel>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<MarvelModel> marvelModels) {
                progressDialog.dismiss();
                binding.swipeContainer.setRefreshing(false);
                marvelModelList.clear();
                if (marvelModels != null) {
                    marvelModelList.addAll(marvelModels);
                    binding.Recyclerview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }
}