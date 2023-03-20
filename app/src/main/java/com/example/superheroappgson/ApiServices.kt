package com.example.superheroappgson

import retrofit2.http.GET

interface ApiServices {

    /**
     * Las funciones suspend solo pueden ejecutarse dentro de una corrutina o
     * dentro de otra funcion suspend.
     * Estas funciones suspenden la corrutina hasta que se completa su
     * ejecución, pero no bloquean el hilo de ejecución desde el que se las
     * ha llamado, permitiendo que siga haciendo tareas
     */
    @GET("api.php/6147419042016914/search/{name}")
    suspend fun getSuperhero(name: String) :

}