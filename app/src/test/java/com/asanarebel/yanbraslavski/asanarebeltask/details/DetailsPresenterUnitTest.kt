package com.asanarebel.yanbraslavski.asanarebeltask.details

import com.asanarebel.yanbraslavski.asanarebeltask.BaseUnitTest
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Test Suit for DetailsPresenter
 */
class DetailsPresenterUnitTest : BaseUnitTest() {

    @Before
    override fun setUp() {
        super.setUp()
    }

    @After
    override fun tearDown() {
        super.tearDown()
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
        //TODO :
    }

    /**
     * Whenever presenter has stored data ,
     * it should be immediately shown to the view
     */
    @Test
    fun testShowCachedDataOnBind() {
        //TODO :
    }

    /**
     * Whenever presenter has no stored data ,
     * it should load the data from server and provide it to the view
     */
    @Test
    fun testDataLoadOnBind() {
        //TODO :
    }

    /**
     * When data could not be fetched , error should be presented on the view
     */
    @Test
    fun testShowError() {
        //TODO :
    }


}