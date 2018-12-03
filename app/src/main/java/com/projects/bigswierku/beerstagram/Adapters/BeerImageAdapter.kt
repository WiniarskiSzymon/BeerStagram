package com.projects.bigswierku.beerstagram.Adapters

import android.support.v7.recyclerview.R.attr.layoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.model.untapped.CheckInPost
import com.projects.bigswierku.beerstagram.model.untapped.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_item.view.*


class BeerImageAdapter(val items: List<Photo>) : RecyclerView.Adapter<BeerImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return  ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateWithUrl(items[position].photoImgMd)


    }


    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val beerImage = view.beer_image_small
        fun updateWithUrl(url: String) {
            Picasso.get().load(url).into(beerImage)
        }


    }
}