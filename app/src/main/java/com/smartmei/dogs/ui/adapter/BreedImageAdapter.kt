package com.smartmei.dogs.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartmei.dogs.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_image_breed.view.*
import kotlinx.android.synthetic.main.loading_progress.view.*

class BreedImageAdapter(val breeds: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_ITEM = 1
    private val VIEW_PROG = 2
    private var closeLoad = false

    override fun getItemCount(): Int {
        return this.breeds.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder
        var view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_image_breed, parent, false)
        if (viewType == VIEW_ITEM) {
            holder = BreedImageViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.loading_progress, parent, false)
            holder = ProgressViewHolder(view)
        }
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return if (position != this.breeds.size - 1) VIEW_ITEM else VIEW_PROG
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BreedImageViewHolder) {
            val breed = breeds.get(position)
            val view = holder.itemView
            with(view) {
                Picasso.with(context).load(breed).into(imageBreed, object : Callback {
                    override fun onSuccess() {
                        loading.visibility = View.GONE
                    }

                    override fun onError() {
                        loading.visibility = View.GONE
                    }
                })
            }
        } else {
            if (closeLoad) {
                holder.itemView.progress.setVisibility(View.GONE)
            }
        }
    }

    fun closeLoad() {
        this.closeLoad = true
    }

    class BreedImageViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class ProgressViewHolder(view: View) : RecyclerView.ViewHolder(view)

}