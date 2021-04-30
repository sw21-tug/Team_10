package com.example.teachomatic3000


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.teachomatic3000.database.DataBaseHelper
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SaveCurrentDateTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun saveCurrentDateTest() {
        val appCompatImageButton = onView(
            allOf(withContentDescription("Open navigation drawer"),
                childAtPosition(
                    allOf(withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                            0)),
                    1),
                isDisplayed()))
        appCompatImageButton.perform(click())

        val navigationMenuItemView = onView(
            allOf(withId(R.id.nav_einstellungen),
                childAtPosition(
                    allOf(withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0)),
                    3),
                isDisplayed()))
        navigationMenuItemView.perform(click())

        val switch_ = onView(
            allOf(withId(R.id.date_regulator), withText("Datum manuell einstellen"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0),
                    0),
                isDisplayed()))
        switch_.perform(click())
        //onView(withId(R.id.start_date_picker)).perform(PickerActions.setDate(2017, 6, 30));
        val materialButton = onView(
            allOf(withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0),
                    3)))
        materialButton.perform(scrollTo(), click())
        val DataBase = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        DataBase.updateDatum("2017-06-30")

        val appCompatImageButton2 = onView(
            allOf(withContentDescription("Open navigation drawer"),
                childAtPosition(
                    allOf(withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                            0)),
                    1),
                isDisplayed()))
        appCompatImageButton2.perform(click())

        val navigationMenuItemView2 = onView(
            allOf(withId(R.id.nav_home),
                childAtPosition(
                    allOf(withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0)),
                    1),
                isDisplayed()))
        navigationMenuItemView2.perform(click())

        val appCompatImageButton3 = onView(
            allOf(withContentDescription("Open navigation drawer"),
                childAtPosition(
                    allOf(withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                            0)),
                    1),
                isDisplayed()))
        appCompatImageButton3.perform(click())

        val navigationMenuItemView3 = onView(
            allOf(withId(R.id.nav_einstellungen),
                childAtPosition(
                    allOf(withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0)),
                    3),
                isDisplayed()))
        navigationMenuItemView3.perform(click())
        // Toast.makeText(InstrumentationRegistry.getInstrumentation().targetContext,"Database time", Toast.LENGTH_SHORT).show()
        //Toast.makeText(InstrumentationRegistry.getInstrumentation().targetContext,DataBase.getDatum(), Toast.LENGTH_SHORT).show()

        println(DataBase.getDatum())
        Assert.assertEquals("2017-06-30",DataBase.getDatum())
/*
        val textView = onView(
                allOf(withId(R.id.helper), withText("23.04.2021"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()))
        textView.check(matches(withText("13.04.2021")))*/

        val switch_3 = onView(
            allOf(
                withId(R.id.date_regulator), withText("Datum manuell einstellen"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        switch_3.perform(click())

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}