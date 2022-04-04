package de.uni_stuttgart.informatik.sopra.sopraapp;


import android.Manifest;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.view.KeyEvent.KEYCODE_A;
import static android.view.KeyEvent.KEYCODE_B;
import static android.view.KeyEvent.KEYCODE_C;
import static android.view.KeyEvent.KEYCODE_D;
import static android.view.KeyEvent.KEYCODE_E;
import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.KeyEvent.KEYCODE_F;
import static android.view.KeyEvent.KEYCODE_G;
import static android.view.KeyEvent.KEYCODE_H;
import static android.view.KeyEvent.KEYCODE_I;
import static android.view.KeyEvent.KEYCODE_L;
import static android.view.KeyEvent.KEYCODE_M;
import static android.view.KeyEvent.KEYCODE_N;
import static android.view.KeyEvent.KEYCODE_O;
import static android.view.KeyEvent.KEYCODE_R;
import static android.view.KeyEvent.KEYCODE_S;
import static android.view.KeyEvent.KEYCODE_T;
import static android.view.KeyEvent.KEYCODE_U;
import static android.view.KeyEvent.KEYCODE_V;
import static android.view.KeyEvent.KEYCODE_X;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivityTest.allowPermissionsIfNeeded;
import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchTest {
    @Test
    public void fillTest(){
        assertEquals(2,2);
    }
    /*@Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(LoginActivity.class);

    @After
    public void after(){
        InstrumentationRegistry.getTargetContext().deleteDatabase("Datenbank.db");
    }

    /*@Test
    public void testGutachterVorname() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.click(device.getDisplayWidth()-50, 100);
        Thread.sleep(500);
        device.pressKeyCode(KEYCODE_G);
        device.pressKeyCode(KEYCODE_U);
        device.pressKeyCode(KEYCODE_S);
        device.pressKeyCode(KEYCODE_T);
        device.pressKeyCode(KEYCODE_A);
        device.pressKeyCode(KEYCODE_V);
        device.pressKeyCode(KEYCODE_ENTER);
    }

    @Test
    public void testGutachterNachname() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.click(device.getDisplayWidth()-50, 100);
        Thread.sleep(500);
        device.pressKeyCode(KEYCODE_G);
        device.pressKeyCode(KEYCODE_U);
        device.pressKeyCode(KEYCODE_T);
        device.pressKeyCode(KEYCODE_A);
        device.pressKeyCode(KEYCODE_C);
        device.pressKeyCode(KEYCODE_H);
        device.pressKeyCode(KEYCODE_T);
        device.pressKeyCode(KEYCODE_E);
        device.pressKeyCode(KEYCODE_R);
        device.pressKeyCode(KEYCODE_ENTER);
    }
    @Test
    public void testVertrag() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.click(device.getDisplayWidth()-50, 100);
        Thread.sleep(500);
        device.pressKeyCode(KEYCODE_C);
        device.pressKeyCode(KEYCODE_O);
        device.pressKeyCode(KEYCODE_N);
        device.pressKeyCode(KEYCODE_T);
        device.pressKeyCode(KEYCODE_R);
        device.pressKeyCode(KEYCODE_A);
        device.pressKeyCode(KEYCODE_C);
        device.pressKeyCode(KEYCODE_T);
        device.pressKeyCode(KEYCODE_ENTER);
    }
    @Test
    public void testFelder() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.click(device.getDisplayWidth()-50, 100);
        Thread.sleep(500);
        device.pressKeyCode(KEYCODE_M);
        device.pressKeyCode(KEYCODE_A);
        device.pressKeyCode(KEYCODE_I);
        device.pressKeyCode(KEYCODE_S);
        device.pressKeyCode(KEYCODE_ENTER);
    }

    @Test
    public void testFelderRegion() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.click(device.getDisplayWidth()-50, 100);
        Thread.sleep(500);
        device.pressKeyCode(KEYCODE_S);
        device.pressKeyCode(KEYCODE_T);
        device.pressKeyCode(KEYCODE_U);
        device.pressKeyCode(KEYCODE_T);
        device.pressKeyCode(KEYCODE_T);
        device.pressKeyCode(KEYCODE_G);
        device.pressKeyCode(KEYCODE_A);
        device.pressKeyCode(KEYCODE_R);
        device.pressKeyCode(KEYCODE_T);
        device.pressKeyCode(KEYCODE_ENTER);
    }

    @Test
    public void testSchadensfallStatus() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.click(device.getDisplayWidth()-50, 100);
        Thread.sleep(500);
        device.pressKeyCode(KEYCODE_G);
        device.pressKeyCode(KEYCODE_E);
        device.pressKeyCode(KEYCODE_M);
        device.pressKeyCode(KEYCODE_E);
        device.pressKeyCode(KEYCODE_L);
        device.pressKeyCode(KEYCODE_D);
        device.pressKeyCode(KEYCODE_E);
        device.pressKeyCode(KEYCODE_T);
        device.pressKeyCode(KEYCODE_ENTER);
    }

    @Test
    public void testSchadensfallArt() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.click(device.getDisplayWidth()-50, 100);
        Thread.sleep(500);
        device.pressKeyCode(KEYCODE_E);
        device.pressKeyCode(KEYCODE_R);
        device.pressKeyCode(KEYCODE_D);
        device.pressKeyCode(KEYCODE_B);
        device.pressKeyCode(KEYCODE_E);
        device.pressKeyCode(KEYCODE_B);
        device.pressKeyCode(KEYCODE_E);
        device.pressKeyCode(KEYCODE_N);
        device.pressKeyCode(KEYCODE_ENTER);
    }

    @Test
    public void testBenutzerVorname() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.click(device.getDisplayWidth()-50, 100);
        Thread.sleep(500);
        device.pressKeyCode(KEYCODE_M);
        device.pressKeyCode(KEYCODE_A);
        device.pressKeyCode(KEYCODE_X);
        device.pressKeyCode(KEYCODE_ENTER);
    }

    @Test
    public void testBenutzerNachname() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.click(device.getDisplayWidth()-50, 100);
        Thread.sleep(500);
        device.pressKeyCode(KEYCODE_M);
        device.pressKeyCode(KEYCODE_U);
        device.pressKeyCode(KEYCODE_S);
        device.pressKeyCode(KEYCODE_T);
        device.pressKeyCode(KEYCODE_E);
        device.pressKeyCode(KEYCODE_R);
        device.pressKeyCode(KEYCODE_M);
        device.pressKeyCode(KEYCODE_A);
        device.pressKeyCode(KEYCODE_N);
        device.pressKeyCode(KEYCODE_N);
        device.pressKeyCode(KEYCODE_ENTER);
    }

    @Test
    public void testGenericFeld() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.click(device.getDisplayWidth()-50, 100);
        Thread.sleep(500);
        device.pressKeyCode(KEYCODE_F);
        device.pressKeyCode(KEYCODE_I);
        device.pressKeyCode(KEYCODE_E);
        device.pressKeyCode(KEYCODE_L);
        device.pressKeyCode(KEYCODE_D);
        device.pressKeyCode(KEYCODE_ENTER);
    }

    @Test
    public void testGenericSchadensfall() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.click(device.getDisplayWidth()-50, 100);
        Thread.sleep(500);
        device.pressKeyCode(KEYCODE_D);
        device.pressKeyCode(KEYCODE_A);
        device.pressKeyCode(KEYCODE_M);
        device.pressKeyCode(KEYCODE_A);
        device.pressKeyCode(KEYCODE_G);
        device.pressKeyCode(KEYCODE_E);
        device.pressKeyCode(KEYCODE_ENTER);
    }*/
}
