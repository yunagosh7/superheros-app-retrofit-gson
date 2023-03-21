package com.example.superheroappgson.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroappgson.R
import com.example.superheroappgson.model.SuperherosItemResponse

class SuperheroAdapter(var superheroList: List<SuperherosItemResponse> = emptyList()) :
    RecyclerView.Adapter<SuperheroViewHolder>() {

    fun updateList(superheroList: List<SuperherosItemResponse>) {
        this.superheroList = superheroList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = superheroList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        return SuperheroViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.render(superheroList[position])
    }

}