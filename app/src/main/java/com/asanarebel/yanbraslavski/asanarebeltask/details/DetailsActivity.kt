package com.asanarebel.yanbraslavski.asanarebeltask.details

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.asanarebel.yanbraslavski.asanarebeltask.App
import com.asanarebel.yanbraslavski.asanarebeltask.R
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.SubscribersResponseModel
import com.asanarebel.yanbraslavski.asanarebeltask.base.BaseActivity
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : BaseActivity(), DetailsContract.DetailsView {

    @Inject lateinit var mPresenter: DetailsContract.DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        App.appComponent.inject(this)
        initView()
        mPresenter.restoreState()
        mPresenter.bind(this)
    }

    private fun initView() {
        supportActionBar?.title = ""
        initRecyclerView()
        empty_view_text_view.visibility = View.VISIBLE
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

    override fun showSubscribersCount(subscribersCount: Int) {
        subscribers_count.text = "$subscribersCount"
    }

    override fun showSubscribers(subscribersList: List<SubscribersResponseModel>) {
        recycler_view?.adapter = SubscribersAdapter(subscribersList, {
            mPresenter.onItemClicked(it)
        })

        if (subscribersList.isEmpty()) {
            empty_view_text_view.text = getString(R.string.no_repos)
            empty_view_text_view.visibility = View.VISIBLE
        } else {
            empty_view_text_view.visibility = View.GONE
        }
    }

}