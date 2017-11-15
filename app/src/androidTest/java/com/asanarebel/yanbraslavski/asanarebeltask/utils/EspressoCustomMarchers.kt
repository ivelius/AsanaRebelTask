package com.asanarebel.yanbraslavski.asanarebeltask.utils

import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v7.widget.Toolbar
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher


/**
 * Created by yan.braslavski on 11/15/17.
 */
class EspressoCustomMarchers {
    companion object {
        fun withToolbarTitle(title: CharSequence): Matcher<Any> {
            return withToolbarTitle(`is`(title))
        }

        private fun withToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<Any> {
            return object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {
                public override fun matchesSafely(toolbar: Toolbar): Boolean {
                    return textMatcher.matches(toolbar.title)
                }

                override fun describeTo(description: Description) {
                    description.appendText("with toolbar title: ")
                    textMatcher.describeTo(description)
                }
            }
        }

        fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
            return RecyclerViewMatcher(recyclerViewId)
        }
    }
}