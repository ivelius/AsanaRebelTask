package com.asanarebel.yanbraslavski.asanarebeltask.details

import com.affinitas.task.api.GitHubService
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.SubscribersResponseModel
import com.asanarebel.yanbraslavski.asanarebeltask.mvp.BasePresenterImpl
import com.asanarebel.yanbraslavski.asanarebeltask.persistence.PresenterStateRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by yan.braslavski on 11/13/17.
 */
open class DetailsPresenterImpl @Inject constructor(private val mApiService: GitHubService,
                                               private val mPersistenceRepository: PresenterStateRepository)
    : BasePresenterImpl<DetailsContract.DetailsView>(), DetailsContract.DetailsPresenter {


    private var mData: List<SubscribersResponseModel>? = null
    private var mUserName: String? = null
    private var mRepoName: String? = null

    override fun bind(view: DetailsContract.DetailsView) {
        super.bind(view)

        //if data already exists we show it without fetching
        if (mData != null && mRepoName != null) {
            showData(mData!!, mRepoName!!)
            return
        }

        if (mUserName != null && mRepoName != null) {
            fetchData(mUserName!!, mRepoName!!)
        } else {
            mBoundView?.showError("Requested Subscribers cannot be found")
        }
    }

    private fun showData(data: List<SubscribersResponseModel>, repoName: String) {
        mBoundView?.changeTitle(repoName)
        mBoundView?.showSubscribers(data)
        mBoundView?.showSubscribersCount(data.size)
    }

    private fun fetchData(userName: String, repoName: String) {
        mBoundView?.showLoading()
        mDisposablesBag.add(
                mApiService.getRepoSubscribers(userName, repoName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally({ mBoundView?.stopLoading() })
                        .subscribe(
                                { result ->
                                    mBoundView?.let {
                                        mData = result
                                        showData(result, repoName)
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

    override fun onItemClicked(it: SubscribersResponseModel) {
        mBoundView?.showMessage("Clicked on ${it.login}")
    }

    override fun saveState() {
        //we persist a copy of the data in repository to restore later
        mPersistenceRepository.persist(ArrayList(mData), javaClass.simpleName)

        if (mUserName != null && mRepoName != null) {
            mPersistenceRepository.persist(mUserName!!, PresenterStateRepository.USERNAME_KEY)
            mPersistenceRepository.persist(mRepoName!!, PresenterStateRepository.REPONAME_KEY)
        }
    }

    override fun restoreState() {
        //we retrieve the stored data
        mData = mPersistenceRepository.retrieve<ArrayList<SubscribersResponseModel>>(javaClass.simpleName)
        mUserName = mPersistenceRepository.retrieve(PresenterStateRepository.USERNAME_KEY)
        mRepoName = mPersistenceRepository.retrieve(PresenterStateRepository.REPONAME_KEY)
    }

    private fun wrapErrorMessage(error: Throwable) = error.message ?:
            "Something is wrong :("
}