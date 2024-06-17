package com.base.ProjectTestAndroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.SystemClock;
import android.widget.TextView;
import static org.hamcrest.Matchers.equalTo;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

public class HomeAndroidTest {

    @Rule
    public ActivityScenarioRule<HomeActivity> activityRule =
            new ActivityScenarioRule<>(HomeActivity.class);

    //Navigation Tests
    @Test
    public void testNavAddTask() {
        onView(withId(R.id.imageView18)).perform(click());
        onView(withId(R.id.editTextText2)).check(matches(isDisplayed()));
    }

    @Test
    public void testNavAddEvent() {
        onView(withId(R.id.imageView19)).perform(click());
        onView(withId(R.id.linearLayout2)).check(matches(isDisplayed()));
    }

    @Test
    public void testNavCC() {
        onView(withId(R.id.imageView17)).perform(click());
        onView(withId(R.id.textView35)).check(matches(isDisplayed()));
    }

    @Test
    public void testNavProfile() {
        onView(withId(R.id.imageView16)).perform(click());
        onView(withId(R.id.imageView28)).check(matches(isDisplayed()));
    }

    @Test
    public void testNavHome() {
        onView(withId(R.id.imageView15)).perform(click());
        onView(withId(R.id.textView3)).check(matches(isDisplayed()));
    }
}
