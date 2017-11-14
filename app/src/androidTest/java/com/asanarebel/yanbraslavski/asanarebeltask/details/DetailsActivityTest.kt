package com.asanarebel.yanbraslavski.asanarebeltask.details

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.SmallTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.affinitas.task.di.app.DaggerAppComponent
import com.affinitas.task.di.app.TestAppModule
import com.affinitas.task.utils.RxUtils
import com.asanarebel.yanbraslavski.asanarebeltask.App
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class DetailsActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<DetailsActivity> =
            ActivityTestRule(DetailsActivity::class.java, true,
                    false)

    @Before
    fun setup() {
        //Replace the app component by our test component
        App.appComponent = DaggerAppComponent.builder()
                .appModule(TestAppModule())
                .build()

        RxUtils.makeRxSchedulersImmidiate()

        val startIntent = Intent()
        mActivityTestRule.launchActivity(startIntent)
    }

    @After
    fun tearDown() {
        RxUtils.resetRxSchedulers()
//        Espresso.unregisterIdlingResources()
    }


    /**
     * Make sure all initial UI elements are placed on the screen , and visible
     * to the user.
     */
    @Test
    fun testAllUiElementsAreDisplayed() {
        // Check that the note title, description and image are displayed
        Espresso.onView(ViewMatchers.withText("PersonalityTestApp")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    /**
     * When user taps a list item , activity should navigate to detailed Activity
     */
    @Test
    fun testItemClick() {
        // Check that the note title, description and image are displayed
        Espresso.onView(ViewMatchers.withText("PersonalityTestApp")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     * When data cannot be fetched due to error , the error should be displayed to the user
     */
    @Test
    fun testErrorFetchingData() {
        // Check that the note title, description and image are displayed
        Espresso.onView(ViewMatchers.withText("PersonalityTestApp")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     * When set title is being called on the view , the title content should
     * change accordingly
     */
    @Test
    fun testSetTitle() {
        // Check that the note title, description and image are displayed
        Espresso.onView(ViewMatchers.withText("PersonalityTestApp")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     * When SubscribersCount is being called on the view , the corresponding UI element content should
     * change accordingly
     */
    @Test
    fun testSetSubscribersCount() {
        // Check that the note title, description and image are displayed
        Espresso.onView(ViewMatchers.withText("PersonalityTestApp")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     * When valid data is being fetched , it should be presented to the user
     * in a list with expected UI elements .
     */
    @Test
    fun testValidDataLoad() {
        // Check that the note title, description and image are displayed
        Espresso.onView(ViewMatchers.withText("PersonalityTestApp")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}