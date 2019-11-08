package com.smartmei.dogs.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartmei.dogs.R
import kotlinx.android.synthetic.main.adapter_breed.view.*

class BreedAdapter(val breeds: ArrayList<String>, val onClick: (String) -> Unit) :
    RecyclerView.Adapter<BreedAdapter.BreedViewHolder>() {

    override fun getItemCount(): Int {
        return this.breeds.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_breed, parent, false)
        return BreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed = breeds.get(position)
        val view = holder.itemView
        with(view) {
            txtTitle.text = breed
            setOnClickListener { onClick(breed) }
        }
    }

    class BreedViewHolder(view: View) : RecyclerView.ViewHolder(view)
}