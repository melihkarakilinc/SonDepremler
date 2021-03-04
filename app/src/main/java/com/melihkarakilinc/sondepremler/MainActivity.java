package com.melihkarakilinc.sondepremler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btn_allMap;
    AdView banner;

    ArrayList<DepremInf> listem;
    Gson gson = new GsonBuilder().setLenient().create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Cons.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        banner=findViewById(R.id.banner);


        btn_allMap=findViewById(R.id.btn_allmap);
        recyclerView=findViewById(R.id.recyclerview);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);

        GetDeprem();

        btn_allMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AllMapsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("listem",listem);
                startActivity(intent);
            }
        });
    }

    public void initDeprem(ArrayList<DepremInf> firma){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        DepremAdapter depremAdapter = new DepremAdapter(firma,getApplicationContext());
        recyclerView.setAdapter(depremAdapter);
    }

    public void GetDeprem(){

        DepremApi depremApi = retrofit.create(DepremApi.class);

        Call<List<DepremInf>> call = depremApi.GetDeprem();
        call.enqueue(new Callback<List<DepremInf>>() {
            @Override
            public void onResponse(Call<List<DepremInf>> call, Response<List<DepremInf>> response) {

                if (response.isSuccessful()){
                    listem = new ArrayList<>();



                    listem=(ArrayList<DepremInf>) response.body();
                    initDeprem(listem);

                }

            }

            @Override
            public void onFailure(Call<List<DepremInf>> call, Throwable t) {

            }
        });
    }
}