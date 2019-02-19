package com.projects.bigswierku.beerstagram.View


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.projects.bigswierku.beerstagram.R
import kotlinx.android.synthetic.main.activity_main.*


import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject




class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>
    override fun  supportFragmentInjector()  = dispatchingAndroidInjector


    private val mOnNavigationItemSelectedListener = com.google.android.material.bottomnavigation.BottomNavigationView.
            OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.bottombaritem_checkins -> {
                        val checkInFragment = CheckInsFragment.newInstance()
                        openFragment( checkInFragment,"CHECK_IN" )
                        return@OnNavigationItemSelectedListener true
                    }

                    R.id.bottombaritem_images -> {
                        val beerImageFragment = BeerImageFragment.newInstance()
                        openFragment( beerImageFragment,"BEER" )
                        return@OnNavigationItemSelectedListener true
                    }

                    R.id.bottombaritem_ratebeer -> {
                        val logInFragment = LogInFragment.newInstance()
                        openFragment( logInFragment,"LOG_IN" )
                        return@OnNavigationItemSelectedListener true
                    }
                }
        false
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(com.projects.bigswierku.beerstagram.R.layout.activity_main)
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val code = intent?.let{
            it.data?.getQueryParameter("code")
        }
        val bundle = Bundle()
        bundle.putString("code", code)
        val logInFragment = supportFragmentManager.findFragmentByTag("LOG_IN")?:LogInFragment.newInstance()
        logInFragment.arguments = bundle
        openFragment( logInFragment,"LOG_IN" )
    }


    private fun openFragment(fragment: androidx.fragment.app.Fragment, name : String) {

            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.container, fragment, name)
            transaction.commit()

    }


}
