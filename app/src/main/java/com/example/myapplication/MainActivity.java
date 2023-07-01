package com.example.myapplication;

import static java.lang.Double.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button btnExchange;
    private EditText reaisInput;
    private TextView showResult;
    private ListView historico;
    private double rate;
    ArrayList<String> exchangeLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExchange = findViewById(R.id.btnExchange);
        reaisInput = findViewById(R.id.reaisInput);
        showResult = findViewById(R.id.showResult);
        historico = findViewById(R.id.resultHistorial);

        ArrayAdapter<String> adapter = new
                ArrayAdapter<>(this,R.layout.activity_main,exchangeLog);

        btnExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.freecurrencyapi.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                CurrencyApi requestRate = retrofit.create(CurrencyApi.class);
                requestRate.getExchangeRate().enqueue(new Callback<ExchangeRate>() {
                    @Override
                    public void onResponse(Call<ExchangeRate> call, Response<ExchangeRate> response) {
                       rate = response.body().getExchangeRate();
                       double operationResult = (double) Math.round(((valueOf(reaisInput.getText().toString())*rate)*100)/100);
                       showResult.setText(""+operationResult);
                       String operationDetail = reaisInput.getText().toString()
                               + " x " + rate + " = USD"+operationResult;
                       exchangeLog.add(operationDetail);
                       historico.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(Call<ExchangeRate> call, Throwable t) {

                    }
                });
            }
        });
    }


}