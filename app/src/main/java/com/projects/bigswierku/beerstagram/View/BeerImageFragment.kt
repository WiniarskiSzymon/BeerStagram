package com.projects.bigswierku.beerstagram.View

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.recyclerview.R.attr.layoutManager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.bigswierku.beerstagram.Adapters.BeerImageAdapter
import com.projects.bigswierku.beerstagram.Adapters.CheckInsAdapter
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.ViewModel.BeerImageViewModel
import com.projects.bigswierku.beerstagram.ViewModel.CheckInsViewModel
import com.projects.bigswierku.beerstagram.model.untapped.CheckInPost
import com.projects.bigswierku.beerstagram.model.untapped.Photo
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BeerImageFragment: Fragment() {



    @Inject
    lateinit var beerImageViewModel: BeerImageViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var photoList : MutableList<Photo> =  mutableListOf()
    private lateinit var compositeDisposable : CompositeDisposable

//    override fun onAttach(context: Context?) {
//        AndroidSupportInjection.inject(this)
//
//        super.onAttach(context)
//        compositeDisposable = CompositeDisposable()
//        compositeDisposable.add(
//                beerImageViewModel.getBeerImages()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .doOnNext {
//                            photoList.add(it.response.beer.medias.items[0].photo)
//                        }
//                        .subscribe()
//        )
//
//    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.images_list, container, false)
        viewManager = GridLayoutManager(this.context,2)
        viewAdapter = BeerImageAdapter(photoList)

        recyclerView = view.findViewById<RecyclerView>(R.id.images_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
                beerImageViewModel.getBeerImages()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext {
                            photoList.add(it)
                            viewAdapter.notifyDataSetChanged()}
                        .subscribe()
        )

        return  view
    }

    companion object {
        fun newInstance(): BeerImageFragment = BeerImageFragment()
    }
}