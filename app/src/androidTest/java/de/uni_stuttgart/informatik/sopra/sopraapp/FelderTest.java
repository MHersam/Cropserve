package de.uni_stuttgart.informatik.sopra.sopraapp;


import android.Manifest;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivityTest.actionOpenDrawer;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivityTest.allowPermissionsIfNeeded;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivityTest.matchToolbarTitle;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FelderTest {
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
    public void testCreateNewField() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        //first navigate to Felder
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Fields")).perform(click());
        Thread.sleep(500);
        //click create new field
        onView(withId(R.id.freg_button)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.map)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.setMarker)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.setMarker)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.setMarker)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.setMarker)).perform(click());
        Thread.sleep(500);
        //test drag option of the marker
        onView(withId(R.id.map)).perform(longClick());
        Thread.sleep(500);
        //test remove option of the marker
        onView(withId(R.id.map)).perform(click());
        Thread.sleep(500);
        //test add info cancel button
        onView(withText("Info")).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.cancelBtn)).perform(click());
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
        onView(withId(R.id.dismissbutton)).perform(click());
        Thread.sleep(500);
        //test gps location, note that the location of the decice should be set to the first coordinate of the field
        onView(withId(R.id.gps)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.save)).perform(click());
        Thread.sleep(500);
    }

    @Test
    public void testCreateNewFieldGutachter() throws InterruptedException {
        onView(withId(R.id.RoleSpinner)).perform(click());
        onView(withText("Reviewer")).perform(click());
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        //first navigate to Felder
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Fields")).perform(click());
        Thread.sleep(500);
        //click create new field
        onView(withId(R.id.freg_button)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.map)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.setMarker)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.setMarker)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.setMarker)).perform(click());
        Thread.sleep(500);
        //test drag option of the marker
        onView(withId(R.id.map)).perform(longClick());
        Thread.sleep(500);
        //test remove option of the marker
        onView(withId(R.id.map)).perform(click());
        Thread.sleep(500);
        //test add info cancel button
        onView(withText("Info")).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.cancelBtn)).perform(click());
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
        onView(withId(R.id.dismissbutton)).perform(click());
        Thread.sleep(500);
        //test gps location, note that the location of the decice should be set to the first coordinate of the field
        onView(withId(R.id.gps)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.save)).perform(click());
        Thread.sleep(500);
    }

    @Test
    public void testEditing() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        //first navigate to Felder
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Fields")).perform(click());
        Thread.sleep(500);
        //click edit from field #1 with contract, testing editing from fields without a contract is not necessary because
        //they use the same methods so no need
        //onView(allOf(withText("edit"), hasSibling(withText("Feld mit Vertrag: ID#1")))).perform(click());
        onView(withId(R.id.editbutton)).perform(click());
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
        onView(withId(R.id.setMarker2)).perform(click());
        Thread.sleep(500);
        //test add info cancel button
        onView(withText("Info")).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.cancelBtn)).perform(click());
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
        onView(withId(R.id.dismissbutton)).perform(click());
        Thread.sleep(500);
        //test gps location, note that the location of the decice should be set to the first coordinate of the field
        onView(withId(R.id.gps2)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.save2)).perform(click());
    }

    @Test
    public void testEditingGutachter() throws InterruptedException {
        onView(withId(R.id.RoleSpinner)).perform(click());
        onView(withText("Reviewer")).perform(click());
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        //first navigate to Felder
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Fields")).perform(click());
        Thread.sleep(500);
        //click edit from field #1 with contract, testing editing from fields without a contract is not necessary because
        //they use the same methods so no need
        onView(allOf(withId(R.id.editbutton), hasSibling(withText("Contracted Field: ID#1")))).perform(click());
        //onView(withId(R.id.editbutton)).perform(click());
        Thread.sleep(500);
        //test drag option of the marker
        onView(withId(R.id.map)).perform(longClick());
        Thread.sleep(500);
        //test remove option of the marker
        onView(withId(R.id.map)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.map)).perform(click());
        Thread.sleep(500);
        //test add info cancel button
        onView(withText("Info")).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.cancelBtn)).perform(click());
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
        onView(withId(R.id.dismissbutton)).perform(click());
        Thread.sleep(500);
        //test gps location, note that the location of the decice should be set to the first coordinate of the field
        onView(withId(R.id.gps2)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.save2)).perform(click());
    }

    @Test
    public void testSpinnerOpen() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        //first navigate to Felder
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Fields")).perform(click());
        Thread.sleep(500);
        onView(withText("Contracted Field: ID#1")).perform(click());
    }

    @Test
    public void testDeleteFunction() throws InterruptedException {
        onView(withId(R.id.userid)).perform(replaceText("1"));
        onView(withId(R.id.password)).perform(replaceText("1234"));
        onView(withId(R.id.sign_in_button)).perform(click());
        Thread.sleep(500);
        allowPermissionsIfNeeded(Manifest.permission.ACCESS_FINE_LOCATION);
        Thread.sleep(500);
        //first navigate to Felder
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        Thread.sleep(500);
        onView(withText("Fields")).perform(click());
        Thread.sleep(500);
        onView(withText("Contracted Field: ID#1")).perform(longClick());
        Thread.sleep(500);
        onView(withText("No")).perform(click());
        onView(withText("Contracted Field: ID#1")).perform(longClick());
        Thread.sleep(500);
        onView(withText("Yes")).perform(click());
        Thread.sleep(500);
        onView(withText("No")).perform(click());
        onView(withText("Contracted Field: ID#1")).perform(longClick());
        Thread.sleep(500);
        onView(withText("Yes")).perform(click());
        Thread.sleep(500);
        onView(withText("Delete")).perform(click());
    }

    */
}
