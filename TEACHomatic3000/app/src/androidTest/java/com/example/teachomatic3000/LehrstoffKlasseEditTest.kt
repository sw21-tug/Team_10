package com.example.teachomatic3000


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
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

@LargeTest
@RunWith(AndroidJUnit4::class)
class LehrstoffKlasseEditTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun lehrstoffKlasseEditTest() {
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
                withId(R.id.nav_classes),
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.text_class_name),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("1b"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.button_add_class), withText("Hinzuf??gen"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val materialTextView = onData(anything())
            .inAdapterView(
                allOf(
                    withId(R.id.class_list),
                    childAtPosition(
                        withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                        4
                    )
                )
            )
            .atPosition(0)
        materialTextView.perform(click())

        val materialButton2 = onView(
            allOf(
                withId(R.id.create_lehrstoff_button), withText("Lehrstoff erstellen"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    5
                )
            )
        )
        materialButton2.perform(scrollTo(), click())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.lehrstoff_title),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("test"), closeSoftKeyboard())

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
        appCompatEditText3.perform(replaceText("lalala"), closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.lehrstoff_date_button), withText("Lehrstoffdatum"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val materialButton4 = onView(
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
        materialButton4.perform(scrollTo(), click())

        val materialButton5 = onView(
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
        materialButton5.perform(click())

        onView(withId(R.id.lehrstoff_klassen_liste)).perform(scrollTo())

        val textView = onData(anything())
            .inAdapterView(
                allOf(
                    withId(R.id.lehrstoff_klassen_liste),
                    childAtPosition(
                        withId(R.id.linearLayout),
                        2
                    )
                )
            )
            .atPosition(0)
        textView.perform(scrollTo(), click())

        val materialButton6 = onView(
            allOf(
                withId(R.id.lehrstoff_date_button), withText("Lehrstoffdatum"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())

        val materialButton7 = onView(
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
        materialButton7.perform(scrollTo(), click())

        val materialButton8 = onView(
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
        materialButton8.perform(click())
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
