package com.projects.bigswierku.beerstagram.View


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.model.untapped.TokenStatus
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
                        openFragment( FragmentTag.LOCAL )
                        return@OnNavigationItemSelectedListener true
                    }

                    R.id.bottombaritem_images -> {
                        openFragment( FragmentTag.BEER )
                        return@OnNavigationItemSelectedListener true
                    }

                    R.id.bottombaritem_ratebeer -> {
                        if(checkIfLogedIn()){
                            openFragment( FragmentTag.FEED)
                        }
                        else{
                            openFragment(FragmentTag.LOGIN)
                        }
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
        openFragment( FragmentTag.LOGIN, bundle)
    }



    private fun openFragment( tag : FragmentTag, bundle :Bundle? = null  ) {
        var fragment = supportFragmentManager.findFragmentByTag(tag.toString())
        if(fragment == null) {
            when (tag) {
                FragmentTag.BEER-> fragment = BeerImageFragment.newInstance()
                FragmentTag.LOCAL ->fragment = CheckInsFragment.newInstance()
                FragmentTag.LOGIN -> fragment = LogInFragment.newInstance()
                FragmentTag.FEED ->fragment = UserFeedFragment.newInstance()
            }
        }
        val transaction = supportFragmentManager.beginTransaction()
        fragment.arguments = bundle
        transaction.replace(R.id.container, fragment, tag.toString())
        transaction.addToBackStack(null)
        transaction.commit()

    }

    private fun checkIfLogedIn(): Boolean {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return false
        val tokenStatus = sharedPref.getString(getString(R.string.token_status), TokenStatus.NONAUTHORIZED.toString())
        return tokenStatus == TokenStatus.AUTHORIZED.toString()

    }

}
