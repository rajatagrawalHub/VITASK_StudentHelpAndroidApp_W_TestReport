package com.base.ProjectTestAndroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class ChapterAndroidTest {

    @Rule
    public ActivityScenarioRule<CCSelectionmenu> activityRule =
            new ActivityScenarioRule<>(CCSelectionmenu.class);

    @Test
    public void testChapterButton() {
        onView(withId(R.id.imageView25)).perform(click());
        onView(withId(R.id.linearLayout2)).check(matches(isDisplayed()));
    }
}
