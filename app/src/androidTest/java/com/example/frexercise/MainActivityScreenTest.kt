package com.example.frexercise

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * UI tests to test different scenarios inside the main activity
 * depending on the server response and ux behavior.
 */
@HiltAndroidTest
class MainActivityScreenTest {
    private lateinit var scenario: ActivityScenario<*>

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        scenario = launchActivity<MainActivity>()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testUILoad() {
        onView(withId(R.id.main_activity_root_relative_layout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testNetworkErrorView() {
        onView(withId(R.id.main_activity_error_text_view))
            .check(matches(isDisplayed()))
    }
}