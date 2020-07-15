package com.saugat.bagpacker;

import androidx.test.rule.ActivityTestRule;

import com.saugat.bagpacker.Activity.user.Register;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class signupTest {
    @Rule
    public ActivityTestRule<Register>
            testRule = new ActivityTestRule<>(Register.class);

    @Test
    public void signup() {
        onView(withId(R.id.etFullName)).perform(typeText("saugat kc"), closeSoftKeyboard());
        onView(withId(R.id.etPhone)).perform(typeText("9849037243"), closeSoftKeyboard());
        onView(withId(R.id.etEmail)).perform(typeText("saugat@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etSignUpUsername)).perform(typeText("rockinz123"), closeSoftKeyboard());
        onView(withId(R.id.etSignUpPassword)).perform(typeText("rockinz123"), closeSoftKeyboard());
        onView(withId(R.id.etConfirmPassword)).perform(typeText("rockinz123"), closeSoftKeyboard());

        onView(withId(R.id.btnSignup))
                .check(matches(isDisplayed()));
    }
}