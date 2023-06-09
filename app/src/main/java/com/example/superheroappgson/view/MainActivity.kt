package com.example.superheroappgson.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call.Details
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroappgson.ApiServices
import com.example.superheroappgson.databinding.ActivityMainBinding
import com.example.superheroappgson.model.SuperherosDataResponse
import com.example.superheroappgson.adapter.SuperheroAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperheroAdapter

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

        adapter = SuperheroAdapter {id ->
            navigateToDetail(id)
        }
        binding.rvSuperheros.setHasFixedSize(true)
        binding.rvSuperheros.layoutManager = LinearLayoutManager(this)
        binding.rvSuperheros.adapter = adapter

    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
         try {
             val myResponse: Response<SuperherosDataResponse> =
                 retrofit.create(ApiServices::class.java).getSuperhero(query)

             if(myResponse.isSuccessful) {
                 Log.i("MainActivity", "Anduvo")
                 val response: SuperherosDataResponse? = myResponse.body()
                 if (response != null) {

                     Log.i("MainActivity", "${response}")

                     runOnUiThread {
                         binding.progressBar.isVisible = false
                         adapter.updateList(response.superherosList)
                     }

                 }

             } else {
                 Log.i("MainActivity", "No anduvo")
             }
         } catch(e: Exception) {
             runOnUiThread {
                 val toast = Toast.makeText(this@MainActivity, "Comprueba tu conexion a internet", Toast.LENGTH_SHORT)
                 toast.show()
                 binding.progressBar.isVisible = false
             }
             Log.i("MainActivity", "Error: ${e.message}")
         }

        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, SuperheroDetailActivity::class.java)
        intent.putExtra(SuperheroDetailActivity.EXTRA_ID ,id)
        startActivity(intent)
    }
}