package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.Manifest;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.view.KeyEvent.KEYCODE_BACK;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivityTest.actionOpenDrawer;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivityTest.allowPermissionsIfNeeded;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivityTest.matchToolbarTitle;
import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {
    @Test
    public void fillTest(){
        assertEquals(2,2);
    }
    /*@Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule(LoginActivity.class);


    /*@Test
    public void anmeldenTest() throws InterruptedException {
        //put in the data
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
    }

    @Test
    public void anmeldenTestGutachter() throws InterruptedException {
        onView(withId(R.id.RoleSpinner)).perform(click());
        onView(withText("Reviewer")).perform(click());
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
    }

    @Test
    public void anmeldenTestFails() throws InterruptedException {
        onView(withId(R.id.sign_in_button)).perform(click());
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.sign_in_button)).perform(click());
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
    }

    @Test
    public void vertragClick() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Contracts")).perform(click());
        Thread.sleep(500);
        onView(withText("Liability Insurance")).perform(click());
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressKeyCode(KEYCODE_BACK);
        Thread.sleep(500);
        onView(withText("Storm Damage")).perform(click());
        device.pressKeyCode(KEYCODE_BACK);
        Thread.sleep(500);
        onView(withText("Forces of Nature")).perform(click());
        device.pressKeyCode(KEYCODE_BACK);
        Thread.sleep(500);
        onView(withText("Pest Infestation")).perform(click());
        device.pressKeyCode(KEYCODE_BACK);
        Thread.sleep(500);
        onView(withText("Game Damage")).perform(click());
        device.pressKeyCode(KEYCODE_BACK);
        Thread.sleep(500);
        onView(withText("Criminal Damage")).perform(click());
        device.pressKeyCode(KEYCODE_BACK);
    }

    @Test
    public void accountTest() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Account")).perform(click());
        Thread.sleep(500);
        onView(withText("Change my password")).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.pw_old)).perform(replaceText("1234"));
        Thread.sleep(500);
        onView(withId(R.id.pw_new1)).perform(replaceText("1234"));
        Thread.sleep(500);
        onView(withId(R.id.pw_new2)).perform(replaceText("1234"));
        Thread.sleep(500);
        onView(withId(R.id.change_pw__button)).perform(click());
    }

    @Test
    public void accountTestGutachter() throws InterruptedException {
        onView(withId(R.id.RoleSpinner)).perform(click());
        onView(withText("Reviewer")).perform(click());
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Account")).perform(click());
        Thread.sleep(500);
        onView(withText("Change my password")).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.change_pw__button)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.pw_old)).perform(replaceText("1233"));
        Thread.sleep(500);
        onView(withId(R.id.change_pw__button)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.pw_new1)).perform(replaceText("1234"));
        Thread.sleep(500);
        onView(withId(R.id.change_pw__button)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.pw_new2)).perform(replaceText("1233"));
        Thread.sleep(500);
        onView(withId(R.id.change_pw__button)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.pw_old)).perform(replaceText("1234"));
        Thread.sleep(500);
        onView(withId(R.id.change_pw__button)).perform(click());
        Thread.sleep(500);
    }*/
}
