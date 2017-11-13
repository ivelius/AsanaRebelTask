package com.affinitas.task.mvp

import java.io.Serializable

/**
 * Created by yan.braslavski on 10/26/17.
 */
object BaseContract {
    /**
     * Here we define the communication :
     * Presenter -> View
     */
    interface BaseView

    /**
     * Here we define the communication :
     * View -> Presenter
     */
    interface BasePresenter<in T : BaseView> : Serializable{
        fun bind(view: T)
        fun unbind()
    }
}