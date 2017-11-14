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

    /**
     * By default we going to present repos of legendary Jake Worthon
     * https://github.com/jakewharton
     */
    private var mSearchQuery: String = "jakewharton"

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

    override fun onSearchClicked() {
        mBoundView?.hideKeyboard()
        fetchData(mSearchQuery)
    }

    override fun onItemClicked(it: GithubRepoResponseModel) {
        //We pass data to a new presenter through persistence layer (which is a part of our data model)
        //This approach is advocated by many notorious Android Engineers , which I also like a lot.
        val repoName = it.name
        mData?.first()?.let {
            mPersistenceRepository.persist(it.owner.login, PresenterStateRepository.USERNAME_KEY)
            mPersistenceRepository.persist(repoName, PresenterStateRepository.REPONAME_KEY)
            mBoundView?.showDetailsView()
        }
    }

    override fun onSearchQueryUpdate(searchQuery: String) {
        mSearchQuery = searchQuery
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