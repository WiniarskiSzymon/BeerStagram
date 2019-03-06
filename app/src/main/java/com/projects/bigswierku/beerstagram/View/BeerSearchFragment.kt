package com.projects.bigswierku.beerstagram.View

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.projects.bigswierku.beerstagram.Adapters.BeerImageAdapter
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.ViewModel.BeerImageViewModel
import com.projects.bigswierku.beerstagram.ViewModel.BeerSearchViewModel
import com.projects.bigswierku.beerstagram.ViewModel.BeerSearchViewModelFactory
import com.projects.bigswierku.beerstagram.model.untapped.BeerSearchResult
import com.projects.bigswierku.beerstagram.model.untapped.Photo
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class BeerSearchFragment: Fragment() {



    @Inject
    lateinit var beerSearchViewModelFactory: BeerSearchViewModelFactory
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private var resultList : MutableList<BeerSearchResult> =  mutableListOf()
    private val beerSearchViewModel by lazy{
        ViewModelProviders.of(this, beerSearchViewModelFactory).get(BeerSearchViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        observeBeerSearchData()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.search_list, container, false)
        viewManager = androidx.recyclerview.widget.GridLayoutManager(this.context, 2)
        viewAdapter = BeerSearchAdapter(resultList)

        recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.search_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return  view
    }

    companion object {
        fun newInstance(): BeerImageFragment = BeerImageFragment()
    }

    private fun observeBeerInfoData() {
        beerSearchViewModel.beerSearchData.observe(this, Observer<List<BeerSearchResult>>{
            it?.let { updateAdapterWithData(it)  }
            viewAdapter.notifyDataSetChanged()
        })
    }

    private fun updateAdapterWithData(postList :List <BeerSearchResult>){
        resultList.clear()
        resultList.addAll(postList)
        viewAdapter.notifyDataSetChanged()
    }
}