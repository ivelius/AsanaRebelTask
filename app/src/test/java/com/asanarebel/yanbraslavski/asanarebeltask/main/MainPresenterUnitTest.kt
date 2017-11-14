package com.asanarebel.yanbraslavski.asanarebeltask.main

import com.affinitas.task.api.GitHubService
import com.affinitas.task.utils.RxUtils
import com.nhaarman.mockito_kotlin.mock
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 */
class MainPresenterUnitTest {
    private val mBoundView: MainContract.MainView = mock()
    private val apiService: GitHubService = mock()
    private lateinit var mPresenterUnderTest: MainContract.MainPresenter

    @Before
    fun setUp() {
        RxUtils.makeRxSchedulersImmidiate()

//        val personalityTestResponseModel = PersonalityTestResponseModel(emptyList(), emptyList())
//        Mockito.`when`(apiService.fetchData()).thenReturn(Observable.just(personalityTestResponseModel))
//        mPresenterUnderTest = MainContract.MainPresenter(apiService)
    }

    @After
    fun tearDown() {
        RxUtils.resetRxSchedulers()
    }

    @Test
    fun verifyShowDataCalled() {
//        mPresenterUnderTest.bind(mBoundView)
//        verify(mBoundView, times(1)).showRepositories(any())
    }
}
