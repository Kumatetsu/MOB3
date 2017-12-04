package com.etna.mob3.mob3

import android.content.Intent
import android.provider.Telephony
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import java.util.regex.Pattern.matches
import android.support.test.espresso.Espresso.onView
import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.*
import org.hamcrest.Matchers.*


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class EspressoTest {

    @get:Rule val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.etna.mob3.mob3", appContext.packageName)
    }

    @Test
    fun openFileSelector() {
        onView(withText("Download file")).perform(click())
        intended(allOf(hasAction(equalTo(Intent.ACTION_GET_CONTENT)),hasCategories(hasItem(equalTo(Intent.CATEGORY_OPENABLE)))))
    }

}
