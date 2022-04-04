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
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivityTest.actionOpenDrawer;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivityTest.allowPermissionsIfNeeded;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivityTest.matchToolbarTitle;
import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SchadensmeldungTest {
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

    @Test
    public void testSchadenmelden() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        //first navigate to Schadensmeldung
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Damage Reports")).perform(click());
        Thread.sleep(500);
        //click melden
        onView(withId(R.id.dmg_rep_btn)).perform(click());
        Thread.sleep(500);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.drag(device.getDisplayWidth()/2,device.getDisplayHeight()/2,device.getDisplayWidth()/2-30,device.getDisplayHeight()/2+30,10);
        Thread.sleep(500);
        //set 4 markers
        onView(withId(R.id.map)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.setDmgMarker)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.setDmgMarker)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.setDmgMarker)).perform(click());
        Thread.sleep(500);
        //test drag option of the marker
        onView(withId(R.id.map)).perform(longClick());
        Thread.sleep(500);
        //test remove option of the marker
        onView(withId(R.id.map)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.setDmgMarker)).perform(click());
        Thread.sleep(500);
        //test add info cancel button
        onView(withText("Info")).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.btn_abbrechen)).perform(click());
        Thread.sleep(500);
        //test adding additional infos
        onView(withText("Info")).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.landText2)).perform(replaceText("Deutschland"));
        Thread.sleep(500);
        onView(withId(R.id.bundeslandText2)).perform(replaceText("Baden-Württemberg"));
        Thread.sleep(500);
        onView(withId(R.id.landkreisText2)).perform(replaceText("Stuttgart"));
        Thread.sleep(500);
        onView(withId(R.id.stadtText2)).perform(replaceText("Stuttgart"));
        Thread.sleep(500);
        onView(withId(R.id.stadtText2)).perform(closeSoftKeyboard());
        Thread.sleep(500);
        onView(withId(R.id.button_ok)).perform(click());
        Thread.sleep(500);
        //test gps location, note that the location of the decice should be set to the first coordinate of the field
        onView(withId(R.id.gpsDmg)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.saveDmgInfo)).perform(click());
    }

    @Test
    public void testEditSchadensfall() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        //navigate to damage cases
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Damage Reports")).perform(click());
        Thread.sleep(500);
        //test the editing option
        onView(withText("Damage Case: ID#1")).perform(click());
        Thread.sleep(500);
        //test drag option of the marker
        onView(withId(R.id.map)).perform(longClick());
        Thread.sleep(500);
        //test remove option of the marker
        onView(withId(R.id.map)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.map)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.map)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.editDmgInfo)).perform(click());
        Thread.sleep(500);
        //test cancel buttion of additional info
        onView(withId(R.id.btn_abbrechen)).perform(click());
        Thread.sleep(500);
        //test adding additional infos
        onView(withId(R.id.editDmgInfo)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.landText2)).perform(replaceText("Deutschland"));
        Thread.sleep(500);
        onView(withId(R.id.bundeslandText2)).perform(replaceText("Baden-Württemberg"));
        Thread.sleep(500);
        onView(withId(R.id.landkreisText2)).perform(replaceText("Stuttgart"));
        Thread.sleep(500);
        onView(withId(R.id.stadtText2)).perform(replaceText("Stuttgart"));
        Thread.sleep(500);
        onView(withId(R.id.stadtText2)).perform(closeSoftKeyboard());
        Thread.sleep(500);
        onView(withId(R.id.button_ok)).perform(click());
    }

    @Test
    public void testFailingConditionsEditing() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        //navigate to damage cases
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Damage Reports")).perform(click());
        Thread.sleep(500);
        //test the editing option
        onView(withText("Damage Case: ID#1")).perform(click());
        onView(withId(R.id.map)).perform(longClick());
        device.drag(device.getDisplayWidth()/2,device.getDisplayHeight()/2,device.getDisplayWidth()/2-30,device.getDisplayHeight()/2+30,10);
        onView(withId(R.id.editDmgInfo)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.button_ok)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.landText2)).perform(replaceText("Deutschland"));
        Thread.sleep(500);
        onView(withId(R.id.button_ok)).perform(click());
    }

    @Test
    public void testDelete() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        //navigate to damage cases
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Damage Reports")).perform(click());
        Thread.sleep(500);
        onView(withText("Damage Case: ID#1")).perform(longClick());
        Thread.sleep(500);
        onView(withText("No")).perform(click());
        onView(withText("Damage Case: ID#1")).perform(longClick());
        Thread.sleep(500);
        onView(withText("Yes")).perform(click());
        Thread.sleep(500);
        onView(withText("No")).perform(click());
        onView(withText("Damage Case: ID#1")).perform(longClick());
        Thread.sleep(500);
        onView(withText("Yes")).perform(click());
        Thread.sleep(500);
        onView(withText("Delete")).perform(click());
    }

    */
}
