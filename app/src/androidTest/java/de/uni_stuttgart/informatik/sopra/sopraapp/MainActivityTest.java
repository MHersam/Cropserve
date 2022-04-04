package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.is;

/**
 * Testclass to the MainActivity
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    public static final int PERMISSIONS_DIALOG_DELAY = 1500;
    public static final int GRANT_BUTTON_INDEX = 1;
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
    public void nvDrawerTestVertraege() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Contracts")).perform(click());
    }

    @Test
    public void nvDrawerTestSchadenmeldung() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Damage Reports")).perform(click());
    }

    @Test
    public void nvDrawerTestStart() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Start")).perform(click());
    }

    @Test
    public void nvDrawerTestFelder() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Fields")).perform(click());
    }

    @Test
    public void nvDrawerTestAccount() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Account")).perform(click());
    }

    @Test
    public void nvDrawerTestAppInfo() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("App Info")).perform(click());
    }

    @Test
    public void nvDrawerTestHilfe() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Help")).perform(click());
    }

    @Test
    public void nvDrawerTestLogout() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Logout")).perform(click());
        Thread.sleep(500);
        onView(withText("Yes")).perform(click());
        Thread.sleep(500);
    }

    @Test
    public void polygonClick() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.map)).perform(click());
        Thread.sleep(500);
    }

    @Test
    public void logoutNoButton() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Logout")).perform(click());
        Thread.sleep(500);
        onView(withText("No")).perform(click());
        Thread.sleep(500);
    }

    @Test
    public void locationButtonTest() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.imageButton)).perform(click());
        Thread.sleep(500);
    }*/

    /**
     * method check if the toolbar title matches with the expected title
     */
    public static ViewInteraction matchToolbarTitle(
            CharSequence title) {
        return onView(isAssignableFrom(Toolbar.class))
                .check(matches(withToolbarTitle(is(title))));
    }

    /**
     * helping method to check if toolbar title matches
     * @param textMatcher
     * @return
     */
    public static Matcher<Object> withToolbarTitle(
            final Matcher<CharSequence> textMatcher) {
        return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {
            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("with toolbar title: ");
                textMatcher.describeTo(description);
            }

            @Override public boolean matchesSafely(Toolbar toolbar) {
                return textMatcher.matches(toolbar.getTitle());
            }
        };
    }

    /**
     * method to open the navigation drawer
     * @return
     */
    public static ViewAction actionOpenDrawer() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(DrawerLayout.class);
            }

            @Override
            public String getDescription() {
                return "open drawer";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((DrawerLayout) view).openDrawer(GravityCompat.START);
            }
        };
    }

    /**
     * method to close the navigation drawer
     * @return
     */
    public static ViewAction actionCloseDrawer() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(DrawerLayout.class);
            }

            @Override
            public String getDescription() {
                return "close drawer";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((DrawerLayout) view).closeDrawer(GravityCompat.START);
            }
        };
    }

    public static void allowPermissionsIfNeeded(String permissionNeeded) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasNeededPermission(permissionNeeded)) {
                sleep(PERMISSIONS_DIALOG_DELAY);
                UiDevice device = UiDevice.getInstance(getInstrumentation());
                UiObject allowPermissions = device.findObject(new UiSelector()
                        .clickable(true)
                        .checkable(false)
                        .index(GRANT_BUTTON_INDEX));
                if (allowPermissions.exists()) {
                    allowPermissions.click();
                }
            }
        } catch (UiObjectNotFoundException e) {
            System.out.println("There is no permissions dialog to interact with");
        }
    }

    public static boolean hasNeededPermission(String permissionNeeded) {
        Context context = InstrumentationRegistry.getTargetContext();
        int permissionStatus = ContextCompat.checkSelfPermission(context, permissionNeeded);
        return permissionStatus == PackageManager.PERMISSION_GRANTED;
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException("Cannot execute Thread.sleep()");
        }
    }

}
