package com.projects.bigswierku.beerstagram.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.model.untapped.CheckInPost
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.checkins_item.view.*

class CheckInsAdapter(val items: List<CheckInPost>) : RecyclerView.Adapter<CheckInsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.checkins_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.beerNameTextView.text= items[position].beerName
        holder.breweryNameTextView.text = items[position].breweryName
        holder.updateWithUrl(items[position].bigPhotoUrl?: "")
        holder.berrStyleTextView.text = items[position].beerStyle
        holder.beerRatingTextView.text = items[position].rating.toString()
        holder.checkInCommentTextView.text = items[position].checkinComment


    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val beerNameTextView = view.beer_name
        val breweryNameTextView = view.brewery_name
        val beerImage = view.beer_image_small
        val beerRatingTextView = view.beer_rating
        val berrStyleTextView = view.beer_style
        val checkInCommentTextView = view.checkin_comment

        fun updateWithUrl(url: String) {
            Picasso.get().load(url).into(beerImage)
        }


    }
}