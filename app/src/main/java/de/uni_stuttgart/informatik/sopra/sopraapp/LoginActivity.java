package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * This Activity lets the user log in to his user account
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */
public class LoginActivity extends AppCompatActivity  {

    //Keep track of the login task to ensure we can cancel it if requested.
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mUserIdView;
    private EditText mPasswordView;
    private Button signInButton;
    private ProgressBar progressBar;
    private Spinner spinner;
    private DatenbankDaten db;
    private int enteredID;
    private String enteredPassword;
    private String selectedRole;
    private String userFullName;

    //TODO: debug variable, set it on false or delete everything related to it before release
    private Boolean debugMode = false;

    /**
     * method is called when activity is created, it initialize the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //read from the preferences and get the correct language for display
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration config = getBaseContext().getResources().getConfiguration();

        String lang = settings.getString("LANG", "");
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }

        // Set up the login form.
        mUserIdView = findViewById(R.id.userid);
        progressBar = findViewById(R.id.progress_bar);
        mPasswordView = findViewById(R.id.password);
        spinner = findViewById(R.id.RoleSpinner);
        enteredPassword = "";
        //setup database connection
        db = new DatenbankDaten(getApplicationContext());
        db.open();
		
		//mUserIdView.setText("1");
        //mPasswordView.setText("1234");


        //make sure database is not empty
        if (db.getGutachter().length == 0){
            db.data();
        }


        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    //attemptLogin();
                    signInButton.performClick();
                    return true;
                }
                return false;
            }
        });
        final Intent mainActivityIntent = new Intent(this, MainActivity.class);

        //TODO: delete this clause or set debugMode to false before release.
        //skips the login procedure and starts the mainActivity right away
        if (debugMode){
            mainActivityIntent.putExtra("userID", (int) 1);
            mainActivityIntent.putExtra("role", (String) spinner.getItemAtPosition(1).toString());
            mainActivityIntent.putExtra("userFullName",(String) db.getGutachterById(1).getFullName());
            db.close();
            startActivity(mainActivityIntent);
            finish();
        }

        //sign-in button event
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuthTask = null;
                selectedRole = spinner.getSelectedItem().toString();
                //check TextView not empty and valid IDs should not be higher than 2^32 otherwise use long instead of int
                if(mUserIdView.getText().toString().length()>=1 &&
                        mUserIdView.getText().toString().length()<= 18 &&
                        Long.parseLong(mUserIdView.getText().toString()) <= Integer.MAX_VALUE){
                    enteredID = Integer.parseInt(mUserIdView.getText().toString());
                }else{
                    // the user entered an invalid ID, set enteredID to some unassigned ID to trigger the exception in isUserIDValid()
                    enteredID = -1;
                }
                enteredPassword = mPasswordView.getText().toString();
                attemptLogin();
                try {
                    Boolean loginIsValid = mAuthTask.get();
                    if(!loginIsValid){
                        //user entered wrong credentials
                        mUserIdView.requestFocus();
                        Toast.makeText(getApplicationContext(),
                                R.string.login_failed, Toast.LENGTH_LONG).show();
                        return;
                    }
                } catch (Exception e){
                    return;
                }

                //put extras for main activity and start it
                mainActivityIntent.putExtra("userID", enteredID);
                mainActivityIntent.putExtra("role", spinner.getSelectedItem().toString());
                mainActivityIntent.putExtra("userFullName", userFullName);
                startActivity(mainActivityIntent);

                //hide keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                progressBar.setVisibility(View.VISIBLE);
                db.close();
                finish();
            }
        });
    }


    /*
     * Attempts to sign in or register the account specified by the login form.
     *
     * If there are form errors (invalid mUserIdView, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mUserIdView.setError(null);
        mPasswordView.setError(null);

        boolean cancel = false;
        View focusView = mUserIdView;

        // Check if the user entered a password.
        if (enteredPassword.isEmpty()) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check if the user entered an ID.
        if (mUserIdView.getText().toString().isEmpty()) {
            mUserIdView.setError(getString(R.string.error_field_required));
            focusView = mUserIdView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // kick off a background task to perform the user login attempt.
            mAuthTask = new UserLoginTask();
            mAuthTask.execute((Void) null);
        }
    }

    /**
     * method check if the user id is in the database
     *
     * @param id user id
     * @return boolean
     */
    private boolean isUserIDValid(int id) {
        String[] options = getResources().getStringArray(R.array.user_role_selection);
        String selected = String.valueOf(spinner.getSelectedItem());
        //selected equals "Versicherungsnehmer"
        if (selected.equals(options[0])){
            try {
                db.getBenutzerById(enteredID);
            } catch (Exception e){
                return false;
            }
            return true;

        }else{
            try {
                db.getGutachterById(enteredID);
            } catch (Exception e){
                return false;
            }
            return true;
        }
    }

    /*
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            Boolean isGutachter = selectedRole.equals(getResources().getStringArray(R.array.user_role_selection)[1]);
            Person p;

            if (!isUserIDValid(enteredID)){
                return false;
            }

            if (!isGutachter) {
                p = db.getBenutzerById(enteredID);
            } else {
                p = db.getGutachterById(enteredID);
            }
            userFullName = p.getFullName();
            return p.getPasswort().equals(enteredPassword);
        }

    }


}

