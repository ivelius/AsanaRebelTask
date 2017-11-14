package com.asanarebel.yanbraslavski.asanarebeltask.details

import com.affinitas.task.api.GitHubService
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.SubscribersResponseModel
import com.asanarebel.yanbraslavski.asanarebeltask.mvp.BasePresenterImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by yan.braslavski on 11/13/17.
 */
class DetailsPresenterImpl @Inject constructor(private val mApiService: GitHubService)
    : BasePresenterImpl<DetailsContract.DetailsView>(), DetailsContract.DetailsPresenter {

    private var mData: List<SubscribersResponseModel>? = null

    override fun bind(view: DetailsContract.DetailsView) {
        super.bind(view)

        //if data already exists we show it without fetching
        mData?.let {
            showData(it)
            return
        }

        //TODO : pass those parameters via injection
        fetchData("JakeWharton","apibuilder")
    }

    private fun showData(data: List<SubscribersResponseModel>) {
        mBoundView?.changeTitle("apibuilder")
        mBoundView?.showSubscribers(data)
    }

    private fun fetchData(userName: String, repoName: String) {
        mBoundView?.showLoading()
        mDisposablesBag.add(
                mApiService.getRepoSubscribers(userName,repoName)
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