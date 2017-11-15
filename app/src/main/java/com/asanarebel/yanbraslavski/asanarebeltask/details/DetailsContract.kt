package com.asanarebel.yanbraslavski.asanarebeltask.details

import com.affinitas.task.mvp.BaseContract
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.SubscribersResponseModel

/**
 * Created by yan.braslavski on 11/13/17.
 */
object DetailsContract {
    /**
     * Here we define the communication :
     * Presenter -> View
     */
    interface DetailsView : BaseContract.BaseView {
        fun showSubscribers(subscribersList: List<SubscribersResponseModel>)
        fun showSubscribersCount(subscribersCount: Int)
    }

    /**
     * Here we define the communication :
     * View -> Presenter
     */
    interface DetailsPresenter : BaseContract.BasePresenter<DetailsView> {
        fun onItemClicked(it: SubscribersResponseModel)
    }
}