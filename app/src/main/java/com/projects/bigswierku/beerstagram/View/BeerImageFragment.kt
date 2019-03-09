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
import com.projects.bigswierku.beerstagram.ViewModel.BeerImageViewModelFactory
import com.projects.bigswierku.beerstagram.model.untapped.Photo
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class BeerImageFragment: Fragment() {



    @Inject
    lateinit var beerImageViewModelFactory: BeerImageViewModelFactory
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private var photoList : MutableList<Photo> =  mutableListOf()
    private var lastBeerId :String? = null
    private val beerImageViewModel by lazy{
        ViewModelProviders.of(this, beerImageViewModelFactory).get(BeerImageViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        observeBeerInfoData()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.images_list, container, false)
        viewManager = androidx.recyclerview.widget.GridLayoutManager(this.context, 2)
        viewAdapter = BeerImageAdapter(photoList)

        recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.images_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return  view
    }
    override fun onResume(){
        super.onResume()
        val beerID = this.arguments?.getString("beerID")
        beerID?.let{
            if(it!= lastBeerId){
                beerImageViewModel.getBeerInfo(it)
                lastBeerId = beerID
            }
        }
    }

    companion object {
        fun newInstance(): BeerImageFragment = BeerImageFragment()
    }

    private fun observeBeerInfoData() {
        beerImageViewModel.beerInfoData.observe(this, Observer<List<Photo>>{
            it?.let { updateAdapterWithData(it)  }
            viewAdapter.notifyDataSetChanged()
        })
    }

    private fun updateAdapterWithData(postList :List <Photo>){
        photoList.clear()
        photoList.addAll(postList)
        viewAdapter.notifyDataSetChanged()
    }
}