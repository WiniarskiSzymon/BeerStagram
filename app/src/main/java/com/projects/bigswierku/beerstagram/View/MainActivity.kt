package com.projects.bigswierku.beerstagram.View


import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.projects.bigswierku.beerstagram.R
import kotlinx.android.synthetic.main.activity_main.*


import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import okhttp3.HttpUrl
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun  supportFragmentInjector()  = dispatchingAndroidInjector


    private val mOnNavigationItemSelectedListener = BottomNavigationView.
            OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.bottombaritem_checkins -> {
                        val checkInFragment = CheckInFragment.newInstance()
                        openFragment( checkInFragment,"CHECK_IN" )
                        return@OnNavigationItemSelectedListener true
                    }

                    R.id.bottombaritem_images -> {
                        val beerImageFragment = BeerImageFragment.newInstance()
                        openFragment( beerImageFragment,"BEER" )
                        return@OnNavigationItemSelectedListener true
                    }

                    R.id.bottombaritem_ratebeer -> {
                    return@OnNavigationItemSelectedListener true
                    }
                }
        false
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

         getCode()



    }
    private fun openFragment(fragment: Fragment, name : String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment,name)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    fun getCode(){

        val authorizeUrl = HttpUrl.parse("https://untappd.com/oauth/authenticate") //
                ?.newBuilder() //
                ?.addQueryParameter("client_id", "7BA7E574D1C0CEFCEB7FDAB198D5A68F402FC9A8")
                ?.addQueryParameter("redirect_uri", "com.projects.bigswierku.beerstagram")
                ?.addQueryParameter("response_type", "code")
                ?.build()
        val intent =  Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(authorizeUrl.toString()))
        startActivity(intent)

    }


}
