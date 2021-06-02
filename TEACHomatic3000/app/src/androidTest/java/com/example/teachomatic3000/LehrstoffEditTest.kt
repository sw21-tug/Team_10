package com.example.teachomatic3000


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@LargeTest
@RunWith(AndroidJUnit4::class)
class LehrstoffEditTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun lehrstoffEditTest() {
        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Open navigation drawer"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val navigationMenuItemView = onView(
            allOf(
                withId(R.id.nav_lehrstoff),
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.lehrstoff_title),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.lehrstoff),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("kerzen"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.lehrstoff_date_button), withText("Lehrstoffdatum"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.lehrstoff),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton2.perform(scrollTo(), click())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.lehrstoff_description),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.lehrstoff),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("ziehen"), closeSoftKeyboard())

        sleep(1000)

        val materialButton3 = onView(
            allOf(
                withId(R.id.lehrstoff_save_button), withText("Speichern"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.lehrstoff),
                        0
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val appCompatImageButton2 = onView(
            allOf(
                withContentDescription("Open navigation drawer"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())

        val navigationMenuItemView2 = onView(
            allOf(
                withId(R.id.nav_lehrstoff_list),
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView2.perform(click())

        val materialTextView = onData(anything())
            .inAdapterView(
                allOf(
                    withId(R.id.lehrstoff_list_database),
                    childAtPosition(
                        withId(R.id.lehrstoffuebersicht),
                        1
                    )
                )
            )
            .atPosition(0)
        materialTextView.perform(click())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.lehrstoff_description),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("ziehen und schnitzen"))

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.lehrstoff_description), withText("ziehen und schnitzen"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(closeSoftKeyboard())
        sleep(1000)

        val materialButton4 = onView(
            allOf(
                withId(R.id.lehrstoff_save_button), withText("Speichern"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())

        val materialTextView2 = onData(anything())
            .inAdapterView(
                allOf(
                    withId(R.id.lehrstoff_list_database),
                    childAtPosition(
                        withId(R.id.lehrstoffuebersicht),
                        1
                    )
                )
            )
            .atPosition(0)
        materialTextView2.perform(click())

        val editText = onView(
            allOf(
                withId(R.id.lehrstoff_description), withText("ziehen und schnitzen"),
                withParent(withParent(withId(R.id.fragment))),
                isDisplayed()
            )
        )
        editText.check(matches(withText("ziehen und schnitzen")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

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
