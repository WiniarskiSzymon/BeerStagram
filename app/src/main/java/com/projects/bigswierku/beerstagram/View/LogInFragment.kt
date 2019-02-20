package com.projects.bigswierku.beerstagram.View


import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.ViewModel.LogInViewModel
import com.projects.bigswierku.beerstagram.ViewModel.LogInViewModelFavtory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.log_in.*
import javax.inject.Inject


class LogInFragment :Fragment(){

    @Inject
    lateinit var logInViewModelFavtory: LogInViewModelFavtory
    private  var  code : String? =null
    private val clientID = "7BA7E574D1C0CEFCEB7FDAB198D5A68F402FC9A8"
    private val redirectURL = "open.my.app"
    private val logInViewModel by lazy {
        ViewModelProviders.of(this, logInViewModelFavtory).get(LogInViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        code = this.arguments?.getString("code")
        //initLiveData(
        code?.let {
            logInViewModel.getAuthorizationToken(it)
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        val view =  inflater.inflate(R.layout.log_in, container, false)
        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        log_in_button.setOnClickListener {
            logIn(clientID,redirectURL)
        }

    }

    private fun logIn(clientID: String, redirectURL : String) {
        val uri = with(Uri.Builder()) {
            scheme("https")
            authority("untappd.com")
            appendPath("oauth")
            appendPath("authenticate")
            appendPath("")
            appendQueryParameter("client_id", clientID)
            appendQueryParameter("response_type", "code")
            appendQueryParameter("redirect_url", redirectURL)
            build()
        }

        val intent  = Intent(ACTION_VIEW, uri )
        activity?.startActivity(intent)
    }

    companion object {
        fun newInstance(): LogInFragment = LogInFragment()
    }

}