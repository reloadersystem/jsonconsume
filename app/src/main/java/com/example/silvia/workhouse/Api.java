package com.example.silvia.workhouse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {



    String BASE_URL = " http://jsonplaceholder.typicode.com";

    @GET("/posts")
    Call<List<LibrosIn>> getLibros();

}