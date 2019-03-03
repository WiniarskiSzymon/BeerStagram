package com.projects.bigswierku.beerstagram.View

import android.content.Context
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projects.bigswierku.beerstagram.Adapters.UserFeedAdapter
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.ViewModel.UserFeedViewModel
import com.projects.bigswierku.beerstagram.ViewModel.UserFeedViewModelFactory
import com.projects.bigswierku.beerstagram.model.untapped.CheckInPost
import com.projects.bigswierku.beerstagram.model.untapped.ImagePost
import com.projects.bigswierku.beerstagram.model.untapped.TokenStatus
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
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
        observeUserFeedLiveData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(checkIfLogedIn()) {
            val  token =  getSavedToken()
            token?.let{
                userFeedViewModel.getUserFeed(token)
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.user_feed_list,container,false)
        viewManager = LinearLayoutManager(this.context)
        viewAdapter = UserFeedAdapter(userFeedList)
        recyclerView = view.findViewById<RecyclerView>(R.id.user_feed_recycler_view).apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return view
    }

    private fun updateAdapterWithData(postList :List <CheckInPost>){
        userFeedList.clear()
        userFeedList.addAll(postList)
        viewAdapter.notifyDataSetChanged()
    }

    private fun observeUserFeedLiveData(){
        userFeedViewModel.userFeedLiveData.observe(
            this, Observer<List<CheckInPost>>{
                it?.let{
                    updateAdapterWithData(it)
                }
            }
        )
    }

    private fun checkIfLogedIn(): Boolean {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return false
        val tokenStatus = sharedPref.getString(getString(R.string.token_status), TokenStatus.NONAUTHORIZED.toString())
        return tokenStatus == TokenStatus.AUTHORIZED.toString()
    }

    private fun getSavedToken(): String?{
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        return sharedPref?.getString(getString(R.string.token), "")
    }

    companion object {
        fun newInstance(): UserFeedFragment = UserFeedFragment()
    }

}