package com.example.superheroappgson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.example.superheroappgson.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()

        initUI()
    }

    private fun initUI() {
        // Importar el
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // Evento que se ejecuta cuando se busca
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Para evitar que se envie un null se envia eso, en caso de que este
                // vacio devuelve la lista completa de heroes
                searchByName(query.orEmpty())

                return false
            }

            // Evento que se ejecuta cuando se hace un cambio en el campo
            override fun onQueryTextChange(newText: String?) = false

        }
        )
    }

    private fun searchByName(name: String) {

    }

    private fun getRetrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }
}