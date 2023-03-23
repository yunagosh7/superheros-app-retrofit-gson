package com.example.superheroappgson

import com.example.superheroappgson.model.SuperheroDetailResponse
import com.example.superheroappgson.model.SuperherosDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    /**
     * Las funciones suspend solo pueden ejecutarse dentro de una corrutina o
     * dentro de otra funcion suspend.
     * Estas funciones suspenden la corrutina hasta que se completa su
     * ejecución, pero no bloquean el hilo de ejecución desde el que se las
     * ha llamado, permitiendo que siga haciendo tareas
     */
    @GET("api.php/6147419042016914/search/{name}")
    suspend fun getSuperhero(@Path("name") superheroName: String): Response<SuperherosDataResponse>


    @GET("api.php/6147419042016914/{id}")
    suspend fun getSuperheroDetails(@Path("id")superheroId: String): Response<SuperheroDetailResponse>
}