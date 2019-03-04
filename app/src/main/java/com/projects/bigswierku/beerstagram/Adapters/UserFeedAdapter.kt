package com.projects.bigswierku.beerstagram.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.model.untapped.CheckInPost
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_feed_item.view.*

class UserFeedAdapter(val  items: List<CheckInPost>) : RecyclerView.Adapter<UserFeedAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFeedAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_feed_item,parent,false))

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserFeedAdapter.ViewHolder, position: Int) {
        holder.apply {
            friendName.text = items[position].userName
            beerName.text = items[position].beerName
            drinkTime.text = items[position].timeFromNow
            comment.text = items[position].checkinComment
            Picasso.get()
                .load(items[position].beerLabel)
                .into(beerLabel)
            Picasso.get()
                .load(items[position].userAvatar)
                .into(userAvatar)
        }
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val beerLabel = view.beer_label
        val friendName = view.friend_name
        val beerName = view.beer_name_feed
        val userAvatar = view.user_avatar
        val drinkTime = view.drink_time
        val comment = view.friend_comment

    }
}