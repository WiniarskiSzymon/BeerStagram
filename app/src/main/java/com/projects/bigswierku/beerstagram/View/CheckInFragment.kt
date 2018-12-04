package com.projects.bigswierku.beerstagram.View


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.bigswierku.beerstagram.Adapters.CheckInsAdapter
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.ViewModel.CheckInsViewModel
import com.projects.bigswierku.beerstagram.model.untapped.CheckInPost
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CheckInFragment() : Fragment() {

    @Inject
    lateinit var checkInViewModel: CheckInsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var checkInList : MutableList<CheckInPost> =  mutableListOf()
    private lateinit var compositeDisposable : CompositeDisposable


    override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)

        super.onAttach(context)
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
                checkInViewModel.getCheckIns()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext {
                            checkInList.add(it)
                            viewAdapter.notifyDataSetChanged()
                        }
                        .subscribe()
        )

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view =  inflater.inflate(R.layout.checkins_list, container, false)

        viewManager = LinearLayoutManager(this.context)
        viewAdapter = CheckInsAdapter(checkInList)

        recyclerView = view.findViewById<RecyclerView>(R.id.checkins_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        viewAdapter.notifyDataSetChanged()

        return view
    }

    companion object {
        fun newInstance(): CheckInFragment = CheckInFragment()
    }


    private fun getCheckInInfo(checkInPost: CheckInPost){


    }
}