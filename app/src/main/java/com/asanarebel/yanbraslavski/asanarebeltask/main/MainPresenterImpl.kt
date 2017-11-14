package com.asanarebel.yanbraslavski.asanarebeltask.main

import com.affinitas.task.api.GitHubService
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.GithubRepoResponseModel
import com.asanarebel.yanbraslavski.asanarebeltask.mvp.BasePresenterImpl
import com.asanarebel.yanbraslavski.asanarebeltask.persistence.PresenterStateRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by yan.braslavski on 11/13/17.
 */
class MainPresenterImpl @Inject constructor(private val mApiService: GitHubService,
                                            private val mPersistenceRepository: PresenterStateRepository)
    : BasePresenterImpl<MainContract.MainView>(), MainContract.MainPresenter {

    private var mData: List<GithubRepoResponseModel>? = null

    override fun bind(view: MainContract.MainView) {
        super.bind(view)

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

    override fun saveState() {
        //we persist a copy of the data in repository to restore later
        mPersistenceRepository.persist(ArrayList(mData), javaClass.simpleName)
    }

    override fun restoreState() {
        //we retrieve the stored data
        mData = mPersistenceRepository.retrieve<ArrayList<GithubRepoResponseModel>>(javaClass.simpleName)
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