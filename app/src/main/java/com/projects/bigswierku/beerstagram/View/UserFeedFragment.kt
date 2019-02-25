package com.projects.bigswierku.beerstagram.View

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.projects.bigswierku.beerstagram.ViewModel.UserFeedViewModel
import com.projects.bigswierku.beerstagram.ViewModel.UserFeedViewModelFactory
import com.projects.bigswierku.beerstagram.model.untapped.CheckInPost
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class UserFeedFragment : Fragment(){


    @Inject
    lateinit var userFeedViewModelFactory : UserFeedViewModelFactory
    private lateinit var recyclerView : RecyclerView
    private lateinit var viewAdapter : RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager
    private var userFeedList : MutableList<CheckInPost> = mutableListOf()
    private val userFeedViewModel by lazy {
        ViewModelProviders.of(this,userFeedViewModelFactory).get(UserFeedViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }


    companion object {
        fun newInstance(): UserFeedFragment = UserFeedFragment()
    }

}