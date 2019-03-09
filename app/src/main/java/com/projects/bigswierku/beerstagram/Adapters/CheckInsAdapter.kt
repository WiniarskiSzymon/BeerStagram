package com.projects.bigswierku.beerstagram.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.model.untapped.ImagePost
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.checkins_item.view.*

class CheckInsAdapter(val items: List<ImagePost>) : androidx.recyclerview.widget.RecyclerView.Adapter<CheckInsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.checkins_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            beerNameTextView.text = items[position].beerName
            breweryNameTextView.text = items[position].breweryName
            updateWithUrl(items[position].bigPhotoUrl ?: "")
            berrStyleTextView.text = items[position].beerStyle
            beerRatingTextView.text = items[position].rating.toString()
            beerDrinker.text =items[position].userName
            if (items[position].checkinComment.isNotBlank()) {
                checkInCommentTextView.text = items[position].checkinComment
            } else checkInCommentTextView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val beerNameTextView = view.beer_name_feed
        val breweryNameTextView = view.brewery_name
        val beerImage = view.beer_image_small
        val beerRatingTextView = view.beer_rating
        val berrStyleTextView = view.beer_style
        val checkInCommentTextView = view.checkin_comment
        val beerDrinker = view.user_name

        fun updateWithUrl(url: String) {
            Picasso
                .get()
                .load(url)
                .into(beerImage)
        }


    }
}