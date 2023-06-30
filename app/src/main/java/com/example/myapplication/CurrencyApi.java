package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CurrencyApi {

   // String url = "http://api.freecurrencyapi.com/v1/latest?apikey=z5QS302Qe23nO2bIroG4wilPZytVRLcQMUwLHu6M&currencies=USD&base_currency=BRL";
    @GET("/v1/latest?apikey=z5QS302Qe23nO2bIroG4wilPZytVRLcQMUwLHu6M&currencies=USD&base_currency=BRL")
    Call<ExchangeRate> getExchangeRate();
}
