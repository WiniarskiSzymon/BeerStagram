package com.projects.bigswierku.beerstagram.View


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.transition.TransitionManager
import com.projects.bigswierku.beerstagram.R
import com.projects.bigswierku.beerstagram.model.untapped.TokenStatus
import kotlinx.android.synthetic.main.activity_main.*


import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.bottom_bar.*
import javax.inject.Inject




class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>
    override fun  supportFragmentInjector()  = dispatchingAndroidInjector


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(com.projects.bigswierku.beerstagram.R.layout.activity_main)
        openFragment( FragmentTag.LOCAL )
        local_beers_action.setOnClickListener {
            openFragment( FragmentTag.LOCAL )
            select(R.id.local_beers_action)
        }
        search_beer_action.setOnClickListener {
            openFragment( FragmentTag.SEARCH )
            select(R.id.search_beer_action)
        }
        user_feed_action.setOnClickListener {
            if(checkIfLogedIn()){
                openFragment( FragmentTag.FEED)
            }
            else{
                openFragment(FragmentTag.LOGIN)
            }
            select(R.id.user_feed_action)
        }
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



    fun openFragment( tag : FragmentTag, bundle :Bundle? = null  ) {
        var fragment = supportFragmentManager.findFragmentByTag(tag.toString())
        if(fragment == null) {
            when (tag) {
                FragmentTag.BEER-> fragment = BeerImageFragment.newInstance()
                FragmentTag.LOCAL ->fragment = CheckInsFragment.newInstance()
                FragmentTag.LOGIN -> fragment = LogInFragment.newInstance()
                FragmentTag.FEED ->fragment = UserFeedFragment.newInstance()
                FragmentTag.SEARCH ->fragment = BeerSearchFragment.newInstance()
            }
        }
        val transaction = supportFragmentManager.beginTransaction()
        fragment.arguments = bundle
        transaction.replace(R.id.fragment_container, fragment, tag.toString())
        transaction.addToBackStack(null)
        transaction.commit()

    }

    private fun checkIfLogedIn(): Boolean {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return false
        val tokenStatus = sharedPref.getString(getString(R.string.token_status), TokenStatus.NONAUTHORIZED.toString())
        return tokenStatus == TokenStatus.AUTHORIZED.toString()

    }



    fun select(id: Int) {
        TransitionManager.beginDelayedTransition(bottom_bar)
        val cs = ConstraintSet()
        cs.clone(local_beers_action)
        if (id == R.id.local_beers_action) {
            DrawableCompat.setTint(local_beers_action.background, ContextCompat.getColor(this, R.color.local_color))
            cs.setVisibility(local_icon_text.id, ConstraintSet.VISIBLE)
        } else {
            DrawableCompat.setTint(local_beers_action.background, ContextCompat.getColor(this, android.R.color.transparent))
            cs.setVisibility(local_icon_text.id, ConstraintSet.GONE)
        }
        cs.applyTo(local_beers_action)

        cs.clone(search_beer_action)
        if (id == R.id.search_beer_action) {
            DrawableCompat.setTint(search_beer_action.background, ContextCompat.getColor(this, R.color.search_color))
            cs.setVisibility(search_icon_text.id, ConstraintSet.VISIBLE)
        } else {
            DrawableCompat.setTint(search_beer_action.background, ContextCompat.getColor(this!!, android.R.color.transparent))
            cs.setVisibility(search_icon_text.id, ConstraintSet.GONE)
        }
        cs.applyTo(search_beer_action)

        cs.clone(user_feed_action)
        if (id == R.id.user_feed_action) {
            DrawableCompat.setTint(user_feed_action.background, ContextCompat.getColor(this, R.color.user_feed_color))
            cs.setVisibility(user_feed_icon_text.id, ConstraintSet.VISIBLE)
        } else {
            DrawableCompat.setTint(user_feed_action.background, ContextCompat.getColor(this, android.R.color.transparent))
            cs.setVisibility(user_feed_icon_text.id, ConstraintSet.GONE)
        }
        cs.applyTo(user_feed_action)

    }


}
