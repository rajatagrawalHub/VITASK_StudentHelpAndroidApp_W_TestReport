package com.base.ProjectTestAndroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.SystemClock;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class TestProfileAndroid {


    @Rule
    public ActivityScenarioRule<ActivityProfile> activityRule =
            new ActivityScenarioRule<>(ActivityProfile.class);

    @Test
    public void testMyEventsButton() {
        onView(withId(R.id.buttonmyevent)).perform(click());
        onView(withId(R.id.linearLayout2)).check(matches(isDisplayed()));
    }

    @Test
    public void testSignOutButton(){
        onView(withId(R.id.textView50)).perform(click());
        onView(withId(R.id.editTextText)).check(matches(isDisplayed()));
    }

    @Test
    public void testUserName(){
        SystemClock.sleep(5000);
        String Fetched_Name = getmailandregno.getName();
        onView(withId(R.id.textView39)).check(matches(withText(Fetched_Name)));
    }

    @Test
    public void testRegisterNumber(){
        SystemClock.sleep(5000);
        String Fetched_Reg_No = getmailandregno.getRegisternumber();
        onView(withId(R.id.textView41)).check(matches(withText(Fetched_Reg_No)));
    }

}
