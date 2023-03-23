package com.example.superheroappgson.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroappgson.databinding.ItemSuperheroBinding
import com.example.superheroappgson.model.SuperherosItemResponse
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    private val binding = ItemSuperheroBinding.bind(view)

    fun render(view: SuperherosItemResponse, onItemSelected: (String) -> Unit) {
        binding.tvSuperheroName.text = view.name
        Picasso.get().load(view.image.url).into(binding.ivSuperheroImage)

        binding.root.setOnClickListener {
            onItemSelected(view.id)
        }

    }
}