package com.projects.bigswierku.beerstagram.View

import android.content.Context

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionManager
import com.jakewharton.rxbinding.widget.RxSearchView
import com.projects.bigswierku.beerstagram.Adapters.BeerSearchAdapter
import com.projects.bigswierku.beerstagram.Adapters.EndlessOnScrollListener
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.ViewModel.BeerSearchViewModel
import com.projects.bigswierku.beerstagram.ViewModel.BeerSearchViewModelFactory
import com.projects.bigswierku.beerstagram.model.untapped.BeerSearchResult
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.bottom_bar.*
import kotlinx.android.synthetic.main.search_list.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import rx.android.schedulers.AndroidSchedulers
import java.text.FieldPosition


class BeerSearchFragment: Fragment() {



    @Inject
    lateinit var beerSearchViewModelFactory: BeerSearchViewModelFactory
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private var resultList : MutableList<BeerSearchResult> =  mutableListOf()
    private val beerSearchViewModel by lazy{
        ViewModelProviders.of(this, beerSearchViewModelFactory).get(BeerSearchViewModel::class.java)
    }

    companion object {
        fun newInstance(): BeerSearchFragment = BeerSearchFragment()
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        observeBeerSearchData()
    }

    private fun observeBeerSearchData() {
        beerSearchViewModel.beerSearchData.observe(this, Observer<List<BeerSearchResult>>{
            it?.let {
                updateAdapterWithData(it)
                viewAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun updateAdapterWithData(postList :List <BeerSearchResult>){
        resultList.clear()
        resultList.addAll(postList)
        viewAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.search_list, container, false)
        val viewManager = androidx.recyclerview.widget.LinearLayoutManager(this.context)
        viewAdapter = BeerSearchAdapter(resultList){
                beerID : Int ->openBeerImageFragment(beerID )
        }
        recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.search_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        recyclerView.addOnScrollListener(
            EndlessOnScrollListener(getMoreData = {beerSearchViewModel.searchForBeer(search_beer.toString(),offset = resultList.size )},
                onScrollUp = {
                    TransitionManager.beginDelayedTransition(search_beer)
                    search_beer.visibility = View.VISIBLE},
                onScrollDown = {
                    TransitionManager.beginDelayedTransition(search_beer)
                    search_beer.visibility = View.GONE},
                layoutManager = viewManager)
        )
        return  view
    }

    private fun openBeerImageFragment(beerId : Int){
        val act = activity as MainActivity
        val bundle = Bundle()
        bundle.putString("beerID", beerId.toString())
        act.openFragment(FragmentTag.BEER, bundle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchBar()
    }

    private fun initSearchBar() {
        search_beer.setIconifiedByDefault(false)
        RxSearchView.queryTextChanges(search_beer)
            .debounce(400, TimeUnit.MILLISECONDS)
            .skip(1)
            .filter { it.isNotEmpty() }
            .observeOn(AndroidSchedulers.mainThread())
            .distinctUntilChanged()
            .subscribe {
                beerSearchViewModel.searchForBeer(it.toString())
            }
    }
}