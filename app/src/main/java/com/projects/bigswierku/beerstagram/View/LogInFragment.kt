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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.ViewModel.LogInViewModel
import com.projects.bigswierku.beerstagram.ViewModel.LogInViewModelFactory
import com.projects.bigswierku.beerstagram.model.untapped.ResponseStatus
import com.projects.bigswierku.beerstagram.model.untapped.Status
import com.projects.bigswierku.beerstagram.model.untapped.Token
import com.projects.bigswierku.beerstagram.model.untapped.TokenStatus
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.log_in.*
import javax.inject.Inject


class LogInFragment :Fragment(){

    @Inject
    lateinit var logInViewModelFavtory: LogInViewModelFactory
    private  lateinit var  code : String
    private val clientID = "7BA7E574D1C0CEFCEB7FDAB198D5A68F402FC9A8"
    private val redirectURL = "open.my.app"
    private val logInViewModel by lazy {
        ViewModelProviders.of(this, logInViewModelFavtory).get(LogInViewModel::class.java)
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
        observeTokenLiveData()
        observeResponseStatus()
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

    override fun onResume() {
        super.onResume()
        if(!checkIfLogedIn()) {
            code = readFromShredPreferences("code") ?:""
        }
        if(!code.isEmpty()){
            logInViewModel.getAuthorizationToken(code)
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

    private fun observeTokenLiveData(){
        logInViewModel.tokenLiveData.observe(this, Observer<Token>{
            saveToSharedPreferences(getString(R.string.token), it.token)
            val intent  = Intent(activity,MainActivity::class.java )
            activity?.startActivity(intent)
        })
    }

    private fun observeResponseStatus(){
        logInViewModel.responseLiveData.observe(this, Observer<ResponseStatus> {
            when (it.status) {
                Status.SUCCESS -> saveToSharedPreferences(getString(R.string.token_status),TokenStatus.AUTHORIZED.toString())
                Status.ERROR -> saveToSharedPreferences(getString(R.string.token_status),TokenStatus.NONAUTHORIZED.toString())
                Status.LOADING -> saveToSharedPreferences(getString(R.string.token_status),TokenStatus.NONAUTHORIZED.toString())
            }
        })
    }


    private fun checkIfLogedIn(): Boolean {
        val tokenStatus = readFromShredPreferences(getString(R.string.token_status))
        return tokenStatus == TokenStatus.AUTHORIZED.toString()
    }



    companion object {
        fun newInstance(): LogInFragment = LogInFragment()
    }

    private fun saveToSharedPreferences(name:String, value :String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref !== null) {
            sharedPref.edit().apply {
                putString(name, value)
                apply()
            }
        }
    }

    private fun readFromShredPreferences(name:String):String?{
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val value = sharedPref?.getString(name,null)
        return value
    }

}