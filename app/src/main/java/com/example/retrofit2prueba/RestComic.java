package com.example.retrofit2prueba;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestComic {
    @GET("{numeroDelComic}/info.0.json")
    Call<Comic> getComic(@Path("numeroDelComic") int numeroComic);
}
