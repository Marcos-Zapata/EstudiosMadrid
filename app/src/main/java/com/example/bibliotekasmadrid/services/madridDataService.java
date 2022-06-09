package com.example.bibliotekasmadrid.services;

import com.example.bibliotekasmadrid.modelsPlaces.ContenedorRespuesta;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface madridDataService {

    //Guardo el JSON con el que responde la API en una clase de JAVA que contiene un atributo para guardar una clase contexto y otro para guardar una lista de Graph's

    //Bibliotecas y bibliobuses en la ciudad de Madrid
    @GET("catalogo/201747-0-bibliobuses-bibliotecas.json")
    Observable<ContenedorRespuesta> listBibliobuses_Bibliotecas(
            @Query("distancia") Integer distancia,
            @Query("latitud") Double latitud,
            @Query("longitud") Double longitud);

    //Salas de estudio y lectura
    @GET("catalogo/217921-0-salas-estudio.json")
    Observable<ContenedorRespuesta> listSlasLectura(
            @Query("distancia") Integer distancia,
            @Query("latitud") Double latitud,
            @Query("longitud") Double longitud);

    //Sedes. Bibliotecas Especializadas Universitarias y Nacionales
    @GET("catalogo/212763-0-biblioteca-universitaria.json")
    Observable<ContenedorRespuesta>listBibliotecasUniversitarias(
            @Query("distancia") Integer distancia,
            @Query("latitud") Double latitud,
            @Query("longitud") Double longitud);
}
