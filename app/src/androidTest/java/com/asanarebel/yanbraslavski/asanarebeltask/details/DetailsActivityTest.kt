package com.asanarebel.yanbraslavski.asanarebeltask.details

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.SmallTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.asanarebel.yanbraslavski.asanarebeltask.BaseActivityTest
import com.asanarebel.yanbraslavski.asanarebeltask.R
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.SubscribersResponseModel
import org.hamcrest.CoreMatchers.allOf
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
class DetailsActivityTest : BaseActivityTest() {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<DetailsActivity> =
            ActivityTestRule(DetailsActivity::class.java, true,
                    false)

    @Before
    override fun setup() {
        super.setup()
    }

    @After
    override fun tearDown() {
        super.tearDown()
    }


    /**
     * Make sure all initial UI elements are placed on the screen , and visible
     * to the user.
     */
    @Test
    fun testAllUiElementsAreDisplayed() {
        mActivityTestRule.launchActivity(Intent())

        // star image is displayed
        Espresso.onView(withId(R.id.star_image_view))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // total subscribers title is displayed
        Espresso.onView(allOf(withId(R.id.total_subscribers_stext_view),
                withText(mActivityTestRule.activity.getString(R.string.total_subscribers))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    /**
     * When data cannot be fetched due to error ,
     * the error should be displayed to the user
     */
    @Test
    fun testErrorFetchingData() {
        //We are taking advantage of the fact that our TestAppModule
        //supplying mocked presenter for this activity using DI
        val errorMessage = "No Luck :("
        val activityPresenter = object : DetailsContract.DetailsPresenter {
            override fun onItemClicked(it: SubscribersResponseModel) {}
            override fun bind(view: DetailsContract.DetailsView) {
                view.showError(errorMessage)
            }
            override fun unbind() {}
            override fun saveState() {}
            override fun restoreState() {}
        }

        mTestAppNodule.mMockedDetailsPresenter = activityPresenter

        //now launch the activity
        mActivityTestRule.launchActivity(Intent())

        //make sure error message displayed in a snack bar
        Espresso.onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(errorMessage)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     * When set title is being called on the view , the title content should
     * change accordingly
     */
    @Test
    fun testSetTitle() {
        //TODO :
    }

    /**
     * When SubscribersCount is being called on the view , the corresponding UI element content should
     * change accordingly
     */
    @Test
    fun testSetSubscribersCount() {
        //TODO :
    }

    /**
     * When valid data is being fetched , it should be presented to the user
     * in a list with expected UI elements .
     */
    @Test
    fun testValidDataLoad() {
        //TODO
    }
}