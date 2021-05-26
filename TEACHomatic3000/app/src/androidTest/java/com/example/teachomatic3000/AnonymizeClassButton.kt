package com.example.teachomatic3000


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.teachomatic3000.database.DataBaseHelper
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AnonymizeClassButton {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun classViewDetailsText() {
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
        appCompatEditText.perform(replaceText("Kunstklasse"), closeSoftKeyboard())

        val materialButton = onView(
                allOf(
                        withId(R.id.button_add_class), withText("Hinzufügen"),
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

        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val classes = db.getClasses()
        if (classes.isEmpty()) {
            materialButton.perform(click())
        }

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

        onView(withId(R.id.class_info)).check(matches(withText(db.getClasses().get(0))));

        val materialButton3 = onView(
                allOf(
                        withId(R.id.anonymize_class_button), withText("Anonymisiere Klasse"),
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
