package com.asanarebel.yanbraslavski.asanarebeltask.main

import android.util.Log
import com.affinitas.task.api.GitHubService
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.GithubRepoResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by yan.braslavski on 11/13/17.
 */
class MainPresenter @Inject constructor(private val mApiService: GitHubService)
    : MainContract.MainPresenter {


    private val mDisposablesBag: CompositeDisposable = CompositeDisposable()
    private var mBoundView: MainContract.MainView? = null
    private var mData: List<GithubRepoResponseModel>? = null

    override fun bind(view: MainContract.MainView) {
        mBoundView = view

        Log.d("tag", "Injected service is $mApiService")

        //if data already exists we show it without fetching
        mData?.let {
            showData(it)
            return
        }
    }

    private fun showData(data: List<GithubRepoResponseModel>) {
        data.firstOrNull()?.owner?.login?.let {
            mBoundView?.changeTitle(it)
        }
        mBoundView?.showRepositories(data)
    }

    override fun onFabClicked() {
        //Data fetching
        fetchData("JakeWharton")
    }

    override fun onItemClicked(it: GithubRepoResponseModel) {
        mBoundView?.showDetailsView(it)
    }

    override fun unbind() {
        mBoundView = null
        mDisposablesBag.clear()
    }

    private fun fetchData(userName: String) {
        mBoundView?.showLoading()
        mDisposablesBag.add(
                mApiService.getRepositories(userName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally({ mBoundView?.stopLoading() })
                        .subscribe(
                                { result ->
                                    mBoundView?.let {
                                        mData = result
                                        showData(result)
                                    }
                                },
                                { error ->
                                    mBoundView?.let {
                                        it.showError(wrapErrorMessage(error))
                                    }
                                }
                        )
        )
    }

    private fun wrapErrorMessage(error: Throwable) = error.message ?:
            "Something is wrong :("
}