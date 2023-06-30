package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CurrencyApi {

    @GET("currencies=USD&base_currency=BRL")
    public Call<ExchangeRate> getExchangeRate(@Path())
}
