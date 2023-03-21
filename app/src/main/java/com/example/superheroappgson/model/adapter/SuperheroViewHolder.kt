package com.example.superheroappgson.model.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroappgson.databinding.ItemSuperheroBinding
import com.example.superheroappgson.model.SuperherosItemResponse

class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun render(view: SuperherosItemResponse) {
        binding.tvSuperheroName.text = view.name
    }
}