package com.asanarebel.yanbraslavski.asanarebeltask.main

import com.affinitas.task.mvp.BaseContract
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.GithubRepoResponseModel

/**
 * Created by yan.braslavski on 11/13/17.
 */
object MainContract {
    /**
     * Here we define the communication :
     * Presenter -> View
     */
    interface MainView : BaseContract.BaseView {

        fun showRepositories(repos: List<GithubRepoResponseModel>)
        fun showDetailsView()
        fun hideKeyboard()
    }

    /**
     * Here we define the communication :
     * View -> Presenter
     */
    interface MainPresenter : BaseContract.BasePresenter<MainView> {
        fun onSearchClicked()
        fun onItemClicked(it: GithubRepoResponseModel)
        fun onSearchQueryUpdate(searchQuery: String)
    }
}