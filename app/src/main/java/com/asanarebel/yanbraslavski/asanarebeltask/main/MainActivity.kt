package com.asanarebel.yanbraslavski.asanarebeltask.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.asanarebel.yanbraslavski.asanarebeltask.App
import com.asanarebel.yanbraslavski.asanarebeltask.R
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.GithubRepoResponseModel
import com.asanarebel.yanbraslavski.asanarebeltask.base.BaseActivity
import com.asanarebel.yanbraslavski.asanarebeltask.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainContract.MainView {

    @Inject lateinit var mPresenter: MainContract.MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.appComponent.inject(this)
        initView()
        mPresenter.restoreState()
        mPresenter.bind(this)
    }


    private fun initView() {
        initActionBar()
        initRecyclerView()
        fab_btn.setOnClickListener { mPresenter.onSearchClicked() }
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
        mPresenter.saveState()
        super.onSaveInstanceState(outState)
    }

    override fun showRepositories(repos: List<GithubRepoResponseModel>) {
        recycler_view?.adapter = ReposAdapter(repos, {
            mPresenter.onItemClicked(it)
        })

        if (repos.isEmpty()) {
            empty_view.text = getString(R.string.no_repos)
            empty_view.visibility = View.VISIBLE
        } else {
            empty_view.visibility = View.GONE
        }
    }

    override fun hideKeyboard() {
        //This is a ugly way that Android framework provides to hide the keyboard , ideally I would wrap it in a utils class or
        //make an extension function...
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(findViewById<View>(android.R.id.content).windowToken, 0)
    }

    override fun showDetailsView() {
        startActivity(Intent(this, DetailsActivity::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        initSearchView(menu)
        return true
    }

    private fun initSearchView(menu: Menu) {
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(q: String): Boolean {
                mPresenter.onSearchQueryUpdate(q)
                return false
            }

            override fun onQueryTextSubmit(q: String): Boolean {
                mPresenter.onSearchClicked()
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "No settings for this app", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}