package com.asanarebel.yanbraslavski.asanarebeltask.main

import android.util.Log
import com.affinitas.task.api.ApiService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by yan.braslavski on 11/13/17.
 */
class MainPresenter @Inject constructor(private val mApiService: ApiService)
    : MainContract.MainPresenter {

    private val mDisposablesBag: CompositeDisposable = CompositeDisposable()
    private var mBoundView: MainContract.MainView? = null
    private var mTimesClicked: Int = 0
    private var mData: String? = null

    override fun bind(view: MainContract.MainView) {
        mBoundView = view

        Log.d("tag", "Injected service is $mApiService")

//        if data already exists we show it without fetching
        mData?.let {
            showData("storedData")
            return
        }

        //Data fetching
        fetchData()
    }

    fun showData(data: String) {
        mBoundView?.showMessage(data)
    }

    override fun onFabClicked() {
        mTimesClicked++
        mBoundView?.showMessage("You clicked the button $mTimesClicked times")
    }

//    private fun showData(it: Map<String, Array<QuestionModel>>) {
////        mBoundView?.showData(it)
////        mBoundView?.setStep(mCurrentStep)
//    }

    override fun unbind() {
        mBoundView = null
        mDisposablesBag.clear()
    }

    private fun fetchData() {
        mData = "Data"
        mData?.let {
            showData(it)
        }
//        mBoundView?.showLoading()
//        mDisposablesBag.add(
//                mApiService.fetchData()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .map { result ->
//                            result.questions.groupBy({ it.category },
//                                    { convertToQuestionBody(it) })
//                                    .mapValues { it.value.toTypedArray() }
//                        }
//                        .subscribe(
//                                { result ->
//
//                                    //TODO : Uncomment this if you want all questions to be answered
////                                    prepopulateAllAnswers(result)
//
//                                    mBoundView?.let {
//                                        it.stopLoading()
//                                        mData = result
//                                        showData(result)
//                                    }
//                                },
//                                { error ->
//                                    mBoundView?.let {
//                                        it.stopLoading()
//                                        it.showError(wrapErrorMessage(error))
//                                    }
//                                }
//                        )
//        )
    }
}