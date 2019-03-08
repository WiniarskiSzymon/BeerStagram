package com.projects.bigswierku.beerstagram.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.model.untapped.BeerSearchResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_item.view.*


class BeerSearchAdapter(val items : List<BeerSearchResult>) : RecyclerView.Adapter<BeerSearchAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BeerSearchAdapter.ViewHolder, position: Int) {
        holder.apply {
            beerName.text = items[position].beerName
            breweryName.text = items[position].breweryName
            breweryCountry.text = items[position].originCountry
            beerDescription.text = items[position].description
            Picasso.get().load(items[position].beerLabel).into(beerLabel)
        }
    }

    class  ViewHolder(view : View): RecyclerView.ViewHolder(view){
        val beerLabel = view.beer_search_label
        val beerName = view.beer_search_name
        val breweryName = view.brewer_search_name
        val breweryCountry = view.beer_search_country
        val beerDescription = view.beer_search_descrption

    }
}