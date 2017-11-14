package com.asanarebel.yanbraslavski.asanarebeltask.mvp

import com.affinitas.task.mvp.BaseContract
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by yan.braslavski on 11/14/17.
 */
abstract class BasePresenterImpl<T : BaseContract.BaseView> : BaseContract.BasePresenter<T> {

    protected val mDisposablesBag: CompositeDisposable = CompositeDisposable()
    protected var mBoundView: T? = null

    override fun bind(view: T) {
        mBoundView = view
    }

    override fun unbind() {
        mBoundView = null
        mDisposablesBag.clear()
    }


}