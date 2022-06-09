package com.example.bibliotekasmadrid.services;

import com.example.bibliotekasmadrid.modelGeo.GeoContainer;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoLocationService {
    //Bibliotecas y bibliobuses en la ciudad de Madrid
    @GET("check")
    Single<GeoContainer> getGeoLocation(@Query("access_key") String accessKey);

}
