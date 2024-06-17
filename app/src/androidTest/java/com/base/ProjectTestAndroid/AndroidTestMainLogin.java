package com.base.ProjectTestAndroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class AndroidTestMainLogin {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testLoginButton() {
        onView(withId(R.id.editTextText)).perform(typeText("rajat.agrawal2022@vitstudent.ac.in")
                ,closeSoftKeyboard());
        onView(withId(R.id.buttonGetOtp)).perform(click());

        onView(withId(R.id.edit1)).check(matches(isDisplayed()));
    }
}
