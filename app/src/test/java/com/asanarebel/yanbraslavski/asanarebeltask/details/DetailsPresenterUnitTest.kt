package com.asanarebel.yanbraslavski.asanarebeltask.details

import com.affinitas.task.api.GitHubService
import com.affinitas.task.utils.RxUtils
import com.asanarebel.yanbraslavski.asanarebeltask.BaseUnitTest
import com.asanarebel.yanbraslavski.asanarebeltask.main.MainContract
import com.nhaarman.mockito_kotlin.mock
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Test Suit for DetailsPresenter
 */
class DetailsPresenterUnitTest : BaseUnitTest()  {
    private val mBoundView: DetailsContract.DetailsPresenter = mock()
    private val apiService: GitHubService = mock()
    private lateinit var mPresenterUnderTest: MainContract.MainPresenter

    @Before
    fun setUp() {
        RxUtils.makeRxSchedulersImmediate()
//        val personalityTestResponseModel = PersonalityTestResponseModel(emptyList(), emptyList())
//        Mockito.`when`(apiService.fetchData()).thenReturn(Observable.just(personalityTestResponseModel))
//        mPresenterUnderTest = MainContract.MainPresenter(apiService)
    }

    @After
    fun tearDown() {
        RxUtils.resetRxSchedulers()
    }

    /**
     * Test the correctness of save state functionality.
     *
     * Steps :
     * 1 - Call save state on first presenter instance.
     * 2 - Call restore state on second presenter instance.
     * 3 - Call restore state on third instance.
     * Expected :
     * The second presenter instance should have the same state as the first instance.
     * The third presenter instance should should have now  default state (reset)
     *
     */
    @Test
    fun testSaveAndRestoreState() {

    }

    /**
     * Whenever presenter has stored data ,
     * it should be immediately shown to the view
     */
    @Test
    fun testShowCachedDataOnBind() {

    }

    /**
     * Whenever presenter has no stored data ,
     * it should load the data from server and provide it to the view
     */
    @Test
    fun testDataLoadOnBind() {

    }

    /**
     * When data could not be fetched , error should be presented on the view
     */
    @Test
    fun testShowError() {

    }


}