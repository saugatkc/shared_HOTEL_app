package com.saugat.bagpacker;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.saugat.bagpacker.Activity.user.Login;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class loginTest {
    @Rule
    public ActivityTestRule<Login>
            testRule = new ActivityTestRule<>(Login.class);

    @Test
    public void login() {
        onView(withId(R.id.etUsername)).perform(typeText("saugat"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("saugat"), closeSoftKeyboard());

        onView(withId(R.id.btnLogin))
                .check(matches(isDisplayed()));
    }
}