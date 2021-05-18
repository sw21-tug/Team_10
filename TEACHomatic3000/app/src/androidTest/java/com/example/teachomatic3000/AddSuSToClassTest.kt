package com.example.teachomatic3000


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
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
class AddSuSToClassTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun addSuSToClassTest() {
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
allOf(withId(R.id.nav_students),
childAtPosition(
allOf(withId(R.id.design_navigation_view),
childAtPosition(
withId(R.id.nav_view),
0)),
4),
isDisplayed()))
        navigationMenuItemView.perform(click())
        
        val appCompatEditText = onView(
allOf(withId(R.id.eTStudentFirstName),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
0),
isDisplayed()))
        appCompatEditText.perform(replaceText("max"), closeSoftKeyboard())
        
        val appCompatEditText2 = onView(
allOf(withId(R.id.eTStudentLastName),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
1),
isDisplayed()))
        appCompatEditText2.perform(replaceText("mustermann"), closeSoftKeyboard())
        
        val materialButton = onView(
allOf(withId(R.id.btnSaveStudent), withText("Speichern"),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
2),
isDisplayed()))
        materialButton.perform(click())
        
        val appCompatEditText3 = onView(
allOf(withId(R.id.eTStudentFirstName),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
0),
isDisplayed()))
        appCompatEditText3.perform(replaceText("maria"), closeSoftKeyboard())
        
        val appCompatEditText4 = onView(
allOf(withId(R.id.eTStudentLastName),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
1),
isDisplayed()))
        appCompatEditText4.perform(replaceText("musterfrau"), closeSoftKeyboard())
        
        val materialButton2 = onView(
allOf(withId(R.id.btnSaveStudent), withText("Speichern"),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
2),
isDisplayed()))
        materialButton2.perform(click())
        
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
allOf(withId(R.id.nav_classes),
childAtPosition(
allOf(withId(R.id.design_navigation_view),
childAtPosition(
withId(R.id.nav_view),
0)),
2),
isDisplayed()))
        navigationMenuItemView2.perform(click())
        
        val appCompatEditText5 = onView(
allOf(withId(R.id.text_class_name),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
1),
isDisplayed()))
        appCompatEditText5.perform(click())
        
        val appCompatEditText6 = onView(
allOf(withId(R.id.text_class_name),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
1),
isDisplayed()))
        appCompatEditText6.perform(replaceText("1a"), closeSoftKeyboard())
        
        val materialButton3 = onView(
allOf(withId(R.id.button_add_class), withText("Hinzufügen"),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
2),
isDisplayed()))
        materialButton3.perform(click())
        
        val materialTextView = onData(anything())
.inAdapterView(allOf(withId(R.id.class_list),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
4)))
.atPosition(0)
        materialTextView.perform(click())
        
        val materialButton4 = onView(
allOf(withId(R.id.button_add_student_to_class), withText("SuS hinzufügen"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
2),
isDisplayed()))
        materialButton4.perform(click())
        
        val checkedTextView = onData(anything())
.inAdapterView(allOf(withId(R.id.sus_list_of_class),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
1)))
.atPosition(0)
        checkedTextView.perform(click())
        
        val checkedTextView2 = onData(anything())
.inAdapterView(allOf(withId(R.id.sus_list_of_class),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
1)))
.atPosition(1)
        checkedTextView2.perform(click())
        
        val materialButton5 = onView(
allOf(withId(R.id.btnAddSusToClass), withText("Speichern"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()))
        materialButton5.perform(click())
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
