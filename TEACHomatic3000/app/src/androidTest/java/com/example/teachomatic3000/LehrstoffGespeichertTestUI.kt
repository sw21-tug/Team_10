package com.example.teachomatic3000


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LehrstoffGespeichertTestUI {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun lehrstoffGespeichertTestUI() {
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
                allOf(withId(R.id.nav_lehrstoff),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.nav_view),
                                                0)),
                                5),
                        isDisplayed()))
        navigationMenuItemView.perform(click())

        val appCompatEditText = onView(
                allOf(withId(R.id.lehrstoff_title),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lehrstoff),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText.perform(replaceText("Grammatik"), closeSoftKeyboard())

        val materialButton = onView(
                allOf(withId(R.id.lehrstoff_date_button), withText("Lehrstoffdatum"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lehrstoff),
                                        0),
                                3),
                        isDisplayed()))
        materialButton.perform(click())

        val materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                3)))
        materialButton2.perform(scrollTo(), click())

        val appCompatEditText2 = onView(
                allOf(withId(R.id.lehrstoff_description),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lehrstoff),
                                        0),
                                2),
                        isDisplayed()))
        appCompatEditText2.perform(replaceText("ss und s Schreibung"), closeSoftKeyboard())

        val materialButton3 = onView(
                allOf(withId(R.id.lehrstoff_save_button), withText("Speichern"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lehrstoff),
                                        0),
                                7),
                        isDisplayed()))
        materialButton3.perform(click())

        val editText = onView(
                allOf(withId(R.id.lehrstoff_title), withText(""),
                        withParent(withParent(withId(R.id.lehrstoff))),
                        isDisplayed()))
        editText.check(matches(withText("")))

        val editText2 = onView(
                allOf(withId(R.id.lehrstoff_description), withText(""),
                        withParent(withParent(withId(R.id.lehrstoff))),
                        isDisplayed()))
        editText2.check(matches(withText("")))
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
