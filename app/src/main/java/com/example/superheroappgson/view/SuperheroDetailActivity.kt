package com.example.superheroappgson.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.superheroappgson.ApiServices
import com.example.superheroappgson.R
import com.example.superheroappgson.databinding.ActivitySuperheroDetailBinding
import com.example.superheroappgson.model.PowerStatsResponse
import com.example.superheroappgson.model.SuperheroDetailResponse
import com.example.superheroappgson.model.SuperherosDataResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class SuperheroDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperheroDetailBinding


    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperheroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()

        getSuperheroInformation(id)
    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val retrofit = getRetrofit()
                val superheroDetail =
                    retrofit.create(ApiServices::class.java).getSuperheroDetails(id)

                if (superheroDetail.body() != null) {
                    runOnUiThread {
                        createUI(superheroDetail.body()!!)
                    }

                }
            } catch (e: Exception) {
                runOnUiThread {
                    val toast = Toast.makeText(
                        this@SuperheroDetailActivity,
                        "Comprueba tu conexion a internet",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                    backToMainActivity()
                    Log.i("SuperheroDetailActivity", "Error: ${e.message}")
                }
            }
        }
    }

    private fun backToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    private fun createUI(superhero: SuperheroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperheroDetailImage)
        binding.tvSuperheroName.text = superhero.name
        prepareStats(superhero.powerstats)
        binding.tvSuperheroRealname.text = superhero.biography.fullName
        binding.tvSuperheroPublisher.text = superhero.biography.publisher

    }

    private fun prepareStats(powerstats: PowerStatsResponse) {
        binding.viewCombat

        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewPower, powerstats.power)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewStrength, powerstats.strength)
    }


    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}