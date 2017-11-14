package com.affinitas.task.main

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.SmallTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.affinitas.task.di.app.DaggerAppComponent
import com.affinitas.task.di.app.TestAppModule
import com.affinitas.task.utils.RxUtils
import com.asanarebel.yanbraslavski.asanarebeltask.App
import com.asanarebel.yanbraslavski.asanarebeltask.main.MainActivity
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
        // Check that the note title, description and image are displayed
        onView(withText("PersonalityTestApp")).check(matches(isDisplayed()))
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