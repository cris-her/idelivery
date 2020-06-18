package com.example.liew.idelivery.Remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kundan on 12/18/2017.
 */

public interface IGeoCoordinates {

    @GET("maps/api/geocode/json")
    Call<String> geoCode(@Query("address") String address);

    @GET("maps/api/directions/json")
    Call<String> getDirections(@Query("origin") String origin, @Query("destination") String destination);
}
