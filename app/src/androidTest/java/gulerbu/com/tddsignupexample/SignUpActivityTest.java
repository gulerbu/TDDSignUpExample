/*
 * Copyright 2003-2017 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.tddsignupexample;

import android.content.ComponentName;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {

    private static final String VALID_USERNAME = "burcu";
    private static final String VALID_PASSWORD = "bR332rr";
    private static final String INVALID_PASSWORD = "bR3";
    @Rule
    public IntentsTestRule<SignUpActivity> intentsTestRule = new IntentsTestRule<>(SignUpActivity.class);

    @Mock
    SignUpService signUpService;

    @Captor
    ArgumentCaptor<SignUpService.Callback> captor;


    /**
     * If @Rule does not stated setUp must be called. These lines are commencted out because @Rule is used
     * for object declaration
     */
//    @Before
//    public void setUp() {
//        intentsTestRule.launchActivity(new Intent());
//    }

    @Test
    public void checkIfErrorMessageIsShownForInvalidUsername() {
        onView(withId(R.id.sign_up_edittext_password)).perform(typeText(VALID_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button_continue)).check(matches(isDisplayed())).perform(click());
        onView(withText("Invalid username or password")).check(matches(isDisplayed()));

    }

    @Test
    public void checkIfErrorMessageIsShownForInvalidPassword() {
        onView(withId(R.id.sign_up_edittext_username)).perform(typeText(VALID_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.sign_up_edittext_password)).perform(typeText(INVALID_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button_continue)).check(matches(isDisplayed())).perform(click());
        onView(withText("Invalid username or password")).check(matches(isDisplayed()));

    }

    @Test
    public void checkIfWelcomeActivityIsStartedForSuccessfulSignUp() {
        onView(withId(R.id.sign_up_edittext_username)).perform(typeText(VALID_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.sign_up_edittext_password)).perform(typeText(VALID_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button_continue)).check(matches(isDisplayed())).perform(click());

        intended(hasComponent(new ComponentName(InstrumentationRegistry.getTargetContext(), WelcomeActivity.class)));

    }

}
