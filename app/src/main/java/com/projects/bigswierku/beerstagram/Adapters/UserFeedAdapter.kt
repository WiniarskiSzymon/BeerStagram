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
        Picasso.get()
            .load(items[position].beerLabel)
            .into(holder.userAvatar )
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val userAvatar = view.avatar

    }
}