package com.affinitas.task.main

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.SmallTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.affinitas.task.di.app.DaggerAppComponent
import com.affinitas.task.di.app.TestAppModule
import com.affinitas.task.utils.RxUtils
import com.asanarebel.yanbraslavski.asanarebeltask.App
import com.asanarebel.yanbraslavski.asanarebeltask.R
import com.asanarebel.yanbraslavski.asanarebeltask.main.MainActivity
import com.asanarebel.yanbraslavski.asanarebeltask.utils.EspressoCustomMarchers.Companion.withToolbarTitle
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
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<MainActivity> =
            ActivityTestRule(MainActivity::class.java, true,
                    false)

    @Before
    fun setup() {
        //Replace the app component by our test component
        App.appComponent = DaggerAppComponent
                .builder()
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
        val activityUnderTest = mActivityTestRule.activity

        // Check that empty string and its content are visible and correct
        onView(withId(R.id.empty_view_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.empty_view_text_view))
                .check(matches(withText(activityUnderTest.getString(R.string.click_to_load))))

        //make sure title is empty
        onView(withId(R.id.toolbar)).check(matches(withToolbarTitle("")))

        //make sure search view is visible
        onView(isAssignableFrom(android.support.v7.widget.SearchView::class.java))
                .check(matches(isDisplayed()))

        //make sure fab is visible
        onView(isAssignableFrom(android.support.design.widget.FloatingActionButton::class.java))
                .check(matches(isDisplayed()))

    }

    /**
     * When user clicks search icon on action bar ,
     * input text area should appear with an expected hint.
     * Soft keyboard should also be opened.
     */
    @Test
    fun testSearchBarClick() {
        // Check that the note title, description and image are displayed
        onView(withText("PersonalityTestApp")).check(matches(isDisplayed()))
    }

    /**
     * When user closes the search bar , input area and software keyboard should disappear
     */
    @Test
    fun testSearchBarDismiss() {
        // Check that the note title, description and image are displayed
        onView(withText("PersonalityTestApp")).check(matches(isDisplayed()))
    }

    /**
     * When user inputs nothing into the search area and tries to search
     * sanckbar with error should appear.
     */
    @Test
    fun testEmptySearch() {
        // Check that the note title, description and image are displayed
        onView(withText("PersonalityTestApp")).check(matches(isDisplayed()))
    }

    /**
     * When user inputs valid username , keyboard and search area should be closed
     * and loading view should appear. After a short while , list view with
     * a predefined structure should appear.
     */
    @Test
    fun testValidSearch() {
        // Check that the note title, description and image are displayed
        onView(withText("PersonalityTestApp")).check(matches(isDisplayed()))
    }

    /**
     * When user taps a list item , activity should navigate to detailed Activity
     */
    @Test
    fun testItemClick() {
        // Check that the note title, description and image are displayed
        onView(withText("PersonalityTestApp")).check(matches(isDisplayed()))
    }



}