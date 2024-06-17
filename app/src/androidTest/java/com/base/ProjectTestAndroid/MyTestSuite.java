package com.base.ProjectTestAndroid;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AndroidTestMain.class,
        AndroidTestMainLogin.class,
        OTPAndroidTest.class,
        HomeAndroidTest.class,
        ChapterAndroidTest.class,
        ClubAndroidTest.class,
        ReminderButtonAndroidTest.class,
        TestProfileAndroid.class
})
public class MyTestSuite {}
