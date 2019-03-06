package com.projects.bigswierku.beerstagram.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projects.bigswierku.beerstagram.model.untapped.BeerSearchResult


class BeerSearchAdapter(val items : List<BeerSearchResult>) : RecyclerView.Adapter<BeerSearchAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BeerSearchAdapter.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class  ViewHolder(view : View): RecyclerView.ViewHolder(view){

    }
}