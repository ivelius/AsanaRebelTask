package com.asanarebel.yanbraslavski.asanarebeltask.main

import com.affinitas.task.mvp.BaseContract

/**
 * Created by yan.braslavski on 11/13/17.
 */
object MainContract {
    /**
     * Here we define the communication :
     * Presenter -> View
     */
    interface MainView : BaseContract.BaseView {
        fun showMessage(message: String)
        fun showLoading()
    }

    /**
     * Here we define the communication :
     * View -> Presenter
     */
    interface MainPresenter : BaseContract.BasePresenter<MainView> {
        fun onFabClicked()
    }
}