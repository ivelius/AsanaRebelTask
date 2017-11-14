package com.asanarebel.yanbraslavski.asanarebeltask.main

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.asanarebel.yanbraslavski.asanarebeltask.App
import com.asanarebel.yanbraslavski.asanarebeltask.R
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.GithubRepoResponseModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainContract.MainView {

    companion object {
        private val BUNDLE_KEY_PRESENTER = "presenter"
    }

    @Inject lateinit var mMainPresenter: MainContract.MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.appComponent.inject(this)

        val presenter = savedInstanceState?.getSerializable(BUNDLE_KEY_PRESENTER) as? MainContract.MainPresenter
        presenter?.let {
            mMainPresenter = it
        }

        initView()
        mMainPresenter.bind(this)
    }

    private fun initView() {
        initActionBar()
        initNavigationDrawer()
        initRecyclerView()
        fab_btn.setOnClickListener { mMainPresenter.onFabClicked() }
        empty_view.visibility = View.VISIBLE
    }


    private fun initActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
    }

    private fun initRecyclerView() {
        recycler_view?.let {
            val linearLayoutManager = LinearLayoutManager(it.context)
            it.layoutManager = linearLayoutManager
            val dividerItemDecoration = DividerItemDecoration(it.context,
                    linearLayoutManager.orientation)
            it.addItemDecoration(dividerItemDecoration)
        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putSerializable(BUNDLE_KEY_PRESENTER, mMainPresenter)
        super.onSaveInstanceState(outState)
    }

    private fun initNavigationDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun showMessage(message: String) {
        Snackbar.make(fab_btn, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun showLoading() {
        loading_overlay?.let {
            it.visibility = View.VISIBLE
            it.animate().alpha(1f)
        }
    }

    override fun stopLoading() {
        loading_overlay?.let {
            it.visibility = View.VISIBLE
            it.animate().alpha(0f).withEndAction({
                it.visibility = View.GONE
            })
        }
    }

    override fun showRepositories(repos: List<GithubRepoResponseModel>) {
        recycler_view?.adapter = ReposAdapter(repos, {
            mMainPresenter?.onItemClicked(it)
        })

        if (repos.isEmpty()) {
            empty_view.text = getString(R.string.no_repos)
            empty_view.visibility = View.VISIBLE
        } else {
            empty_view.visibility = View.GONE
        }
    }

    override fun changeTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun showDetailsView(it: GithubRepoResponseModel) {
        showMessage("Clicked on ${it.name}")
    }

    override fun showError(errorMessage: String) {
        val snack = Snackbar.make(toolbar, errorMessage, Snackbar.LENGTH_LONG)
        val tv = snack.view.findViewById<TextView>(android.support.design.R.id.snackbar_text) as TextView
        tv.setTextColor(Color.RED)
        snack.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
