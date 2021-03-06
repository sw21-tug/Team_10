package com.example.teachomatic3000


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.StudentModel
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
class StudentViewExists {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    //T017
    //-----------
    @Test fun readStringFromContext_LocalizedString() {
        // Given a Context object retrieved from Robolectric...
        val myObjectUnderTest = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val Student = StudentModel(0, "MUstermann", "Max")
        val result =myObjectUnderTest.addStudent(Student)
        Assert.assertEquals(result, true)
        // ...when the string is returned from the object under test...
        //val result: String = myObjectUnderTest.getHelloWorldString()


        // ...then the result should be the expected one.
        // assertThat(result).isEqualTo(FAKE_STRING)
    }

    // Funktion schaltet Toggle ein, deshalb failen die Tests im 2. Durchlauf.
    /*@Test fun DatumUpdateTest() {
        // Given a Context object retrieved from Robolectric...
        val myObjectUnderTest = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val datum = "1"
        val safe = myObjectUnderTest.updateDatum(datum)
        Assert.assertEquals(safe, true)
        val inhalt = myObjectUnderTest.getDatum()
        Assert.assertEquals(datum, inhalt)


    }*/

    @Test
    fun studentViewTest() {
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
                withId(R.id.nav_students),
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        val button = onView(
            allOf(
                withId(R.id.btnSaveStudent), withText("SPEICHERN"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val eTFirstName = onView(
            allOf(
                withId(R.id.eTStudentFirstName),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        eTFirstName.check(matches(isDisplayed()))

        val eTLastName = onView(
            allOf(
                withId(R.id.eTStudentLastName),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        eTLastName.check(matches(isDisplayed()))
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
