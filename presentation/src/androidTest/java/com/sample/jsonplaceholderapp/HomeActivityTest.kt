package com.sample.jsonplaceholderapp

import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions
import androidx.recyclerview.widget.RecyclerView
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.junit.*

/**
 * test flow home activity
 *
 * @tester khoyron
 */
class HomeActivityTest{

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun test_recyclerViewTest(){

        /**
         * get recyclerview activity for check total item data in adapter
         * @tester khoyron
         */
        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.rv_list_post)

        /**
         * sleeo thread for get data network
         * @tester khoyron
         */
        Thread.sleep(6000)
        if (recyclerView.adapter?.itemCount!=0){
            /**
             * test click item recyclerview at first position
             * @tester khoyron
             */
            Espresso.onView(withId(R.id.rv_list_post)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                    ViewActions.click()
                ))

            /**
             * test click name post for check open page profile
             * @tester khoyron
             */
            Espresso.onView(withId(R.id.tv_name_detail)).perform(ViewActions.click())
            Espresso.onView(withId(R.id.tv_user_name)).check(matches(isDisplayed()))
        }
    }

}