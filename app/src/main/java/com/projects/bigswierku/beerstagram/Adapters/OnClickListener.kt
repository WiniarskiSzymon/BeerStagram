package com.projects.bigswierku.beerstagram.Adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OnClickListener( val performAction: (beerID : Int)->  Unit) : View.OnClickListener {

    override fun onClick(v: View?) {
        performAction
    }

}