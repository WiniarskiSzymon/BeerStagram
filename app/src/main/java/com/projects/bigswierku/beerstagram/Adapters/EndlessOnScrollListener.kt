package com.projects.bigswierku.beerstagram.Adapters

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessOnScrollListener(val onScrollUp: ()-> Unit ={},
                              val onScrollDown :() ->Unit = {},
                              val getMoreData :() -> Unit = {},
                              val layoutManager: LinearLayoutManager): RecyclerView.OnScrollListener(){


    private var loading = true
    private val treshold = 3
    private var previousTotal = 0
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemsCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy>0){
            onScrollDown()
            visibleItemCount = recyclerView.childCount
            totalItemsCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (loading){
                if (totalItemsCount>previousTotal) {
                    loading = false
                    previousTotal = totalItemsCount
                    onScrollDown()
                }
                else{
                    onScrollUp()
                }
            }

            if (!loading &&(totalItemsCount - visibleItemCount)<=(firstVisibleItem + treshold)){
                getMoreData()
                loading = true
            }
        }



    }



}