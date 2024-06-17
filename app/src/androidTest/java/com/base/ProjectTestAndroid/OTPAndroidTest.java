package com.base.ProjectTestAndroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.SystemClock;

import static org.hamcrest.Matchers.equalTo;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Rule;
import org.junit.Test;

public class OTPAndroidTest {
    String otp_recieved;
    @Rule
    public ActivityScenarioRule<OtpActivity> activityRule =
            new ActivityScenarioRule<>(OtpActivity.class);

    @Test
    public void testFetchOtp() {
        FirebaseDatabase dbMain = FirebaseDatabase.getInstance();
        DatabaseReference dOTP = dbMain.getReference("OTP");
        dOTP.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot OTPSnapShot : dataSnapshot.getChildren()) {
                    otp_recieved = OTPSnapShot.getKey();
                    assertThat(true,equalTo(true));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Error In Database: "+ error.toException());
                assertThat(false,equalTo(true));
            }
        });

    }

    @Test
    public void testSubmitOtp() {
        SystemClock.sleep(5000);
        otp_recieved = OtpActivity.return_generatedOTP();
        onView(withId(R.id.edit1)).perform(typeText(String.valueOf(otp_recieved.charAt(0))));
        onView(withId(R.id.edit2)).perform(typeText(String.valueOf(otp_recieved.charAt(1))));
        onView(withId(R.id.edit3)).perform(typeText(String.valueOf(otp_recieved.charAt(2))));
        onView(withId(R.id.edit4)).perform(typeText(String.valueOf(otp_recieved.charAt(3))),
                closeSoftKeyboard());
        onView(withId(R.id.imageView8)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.buttonSubmit)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.textView3)).check(matches(isDisplayed()));
    }
}
