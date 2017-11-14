package com.asanarebel.yanbraslavski.asanarebeltask.main

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.asanarebel.yanbraslavski.asanarebeltask.App
import com.asanarebel.yanbraslavski.asanarebeltask.R
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.GithubRepoResponseModel
import com.asanarebel.yanbraslavski.asanarebeltask.base.BaseActivity
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
        fab_btn.setOnClickListener { mPresenter.onFabClicked() }
        empty_view.visibility = View.VISIBLE
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

    override fun showDetailsView(it: GithubRepoResponseModel) {
        showMessage("Clicked on ${it.name}")
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
}