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
import com.projects.bigswierku.beerstagram.ViewModel.ResponseStatus
import com.projects.bigswierku.beerstagram.ViewModel.Status
import com.projects.bigswierku.beerstagram.model.untapped.Token
import com.projects.bigswierku.beerstagram.model.untapped.TokenStatus
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.log_in.*
import javax.inject.Inject


class LogInFragment :Fragment(){

    @Inject
    lateinit var logInViewModelFavtory: LogInViewModelFactory
    private  var  code : String? =null
    private val clientID = "7BA7E574D1C0CEFCEB7FDAB198D5A68F402FC9A8"
    private val redirectURL = "open.my.app"
    private val logInViewModel by lazy {
        ViewModelProviders.of(this, logInViewModelFavtory).get(LogInViewModel::class.java)
    }
    private val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
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
        code = this.arguments?.getString("code")
        //initLiveData(
        code?.let {
            logInViewModel.getAuthorizationToken(it)
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
            saveToken(it.token)
        })
    }

    private fun saveToken(token : String){
        sharedPref?.edit().let{
            it?.putString(getString(R.string.token), token)
            it?.commit()
        }
    }

    private fun observeResponseStatus(){
        logInViewModel.responseLiveData.observe(this, Observer<ResponseStatus> {
            when (it.status) {
                Status.SUCCESS -> setTokenStatus(TokenStatus.AUTHORIZED)
                Status.ERROR -> setTokenStatus(TokenStatus.NONAUTHORIZED)
                Status.LOADING -> setTokenStatus(TokenStatus.NONAUTHORIZED)
            }
        })
    }


    private fun setTokenStatus(status: TokenStatus){
            sharedPref?.edit().let{
                it?.putString(getString(R.string.token_status), status.toString())
                it?.commit()
            }
        }

    companion object {
        fun newInstance(): LogInFragment = LogInFragment()
    }

}