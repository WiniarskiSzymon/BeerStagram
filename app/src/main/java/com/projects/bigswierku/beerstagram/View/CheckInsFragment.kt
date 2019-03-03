package com.projects.bigswierku.beerstagram.View


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.projects.bigswierku.beerstagram.Adapters.CheckInsAdapter
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.ViewModel.CheckInsViewModel
import com.projects.bigswierku.beerstagram.ViewModel.CheckInsViewModelFactory
import com.projects.bigswierku.beerstagram.model.untapped.ImagePost
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CheckInsFragment : Fragment() {

    @Inject
    lateinit var checkInViewModelFactory: CheckInsViewModelFactory
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private var imageList : MutableList<ImagePost> =  mutableListOf()
    private val checkInsViewModel by lazy {
        ViewModelProviders.of(this,checkInViewModelFactory).get(CheckInsViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        observeBeerInfoData()
        observeBeerInfoStatus()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkInsViewModel.getCheckIns()

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view =  inflater.inflate(R.layout.checkins_list, container, false)
        viewManager = androidx.recyclerview.widget.LinearLayoutManager(this.context)
        viewAdapter = CheckInsAdapter(imageList)
        recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.checkins_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return view
    }

    companion object {
        fun newInstance(): CheckInsFragment = CheckInsFragment()
    }

    private fun observeBeerInfoStatus(){
    //TODO
    }

    private fun observeBeerInfoData() {
        checkInsViewModel.checkInsData.observe(this, Observer<List<ImagePost>>{
        it?.let { updateAdapterWithData(it)  }
        viewAdapter.notifyDataSetChanged()
        })
    }

    private fun updateAdapterWithData(postList :List <ImagePost>){
        imageList.clear()
        imageList.addAll(postList)
        viewAdapter.notifyDataSetChanged()
    }



    private fun getCheckInInfo(imagePost: ImagePost){
    //TODO

    }
}