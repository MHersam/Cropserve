package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Class takes care of creating a new user
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */
public class UserCreationFragment extends Fragment {

    //UI references
    private EditText newUserName;
    private EditText newUserLastName;
    private EditText newUserAddress;
    private EditText newUserPW;
    private EditText newUserPwRp;

    private String newEnteredName;
    private String newEnteredLastName;
    private String newEnteredAddress;
    private String newEnteredPW;
    private String newEnteredConfirmPW;

    private ProgressBar progressBar;
    private DatenbankDaten db;
    private boolean authNoEmpty;
    private boolean authPwMatch;

    //constructor
    public UserCreationFragment() {
        // Required empty public constructor

    }
    /**
     * Creates the fragment instance
     * method is called when fragment is created
     *
     * @param savedInstanceState previous instance
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * Inflates the layout view for this fragment
     * Called when fragment is created
     *
     * @param inflater inflates the layout
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_creation, container, false);

    }


    /**
     * method is called when after the Fragment is created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //set layout background view
        TextView string = getActivity().findViewById(R.id.createUserString);
        SpannableString stringContent = new SpannableString(getString(R.string.new_user));
        stringContent.setSpan(new UnderlineSpan(), 0, stringContent.length(), 0);
        string.setText(stringContent);

        db = new DatenbankDaten(getActivity());

        //set up form
        newUserName = getActivity().findViewById(R.id.newUserName);
        newUserLastName = getActivity().findViewById(R.id.newUserSurName);
        newUserAddress = getActivity().findViewById(R.id.newUserAdress);
        newUserPW = getActivity().findViewById(R.id.newUserPW);
        newUserPwRp = getActivity().findViewById(R.id.newUserPwRp);

        //buttons
        Button cancelCreation = getActivity().findViewById(R.id.cancCreateBtn);
        Button createUser = getActivity().findViewById(R.id.crUserBtn);

        //get progress bar
        progressBar = getActivity().findViewById(R.id.creationProgress);

        //cancel registration and go back
        cancelCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        //create user button, actual registration process
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newEnteredName = newUserName.getText().toString();
                newEnteredLastName = newUserLastName.getText().toString();
                newEnteredAddress = newUserAddress.getText().toString();
                newEnteredPW = newUserPW.getText().toString();
                newEnteredConfirmPW = newUserPwRp.getText().toString();

                //to delete:
                Log.i("confirm1",newEnteredPW);
                Log.i("confirm2",newEnteredConfirmPW);

                if(!newEnteredName.isEmpty() && !newEnteredLastName.isEmpty() && !newEnteredAddress.isEmpty() && !newEnteredPW.isEmpty() && !newEnteredConfirmPW.isEmpty()) {
                    authNoEmpty = true;
                } else {
                    authNoEmpty = false;
                }

                if(!newEnteredConfirmPW.equalsIgnoreCase(newEnteredPW)) {
                    authPwMatch = false;
                } else {
                    authPwMatch = true;
                }

                attemptUserCreation();

                try {
                    Boolean createIsValid = isUserUnique();
                    if(!createIsValid){
                        //user entered wrong credentials
                        newUserName.requestFocus();
                        if(!newEnteredConfirmPW.equalsIgnoreCase(newEnteredPW)) {
                            Toast.makeText(getContext(), R.string.no_match_pw, Toast.LENGTH_SHORT).show();
                            return;
                        }  else if(!authNoEmpty) {
                            Toast.makeText(getContext(), R.string.required_form, Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Toast.makeText(getContext(), R.string.match_user, Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                } catch (Exception e){
                    return;
                }



                //hide keyboard
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                progressBar.setVisibility(View.VISIBLE);

                //unique user data: create user in db
                //id doesn't matter, it will be incrementally increased automatically
                Benutzer newUser = new Benutzer(newEnteredName, newEnteredLastName, newEnteredAddress, 3, newEnteredConfirmPW);
                db.addBenutzer(newUser);
                Toast.makeText(getContext(), R.string.success_createUser, Toast.LENGTH_SHORT).show();

                db.close();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                AccountFragment acc = new AccountFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.flContent, acc).addToBackStack("creation_success_tag").commit();
            }
        });
    }

    /**
     * Checks whether all text fields are filled in
     * Displays error if fields are empty
     * Displays error if the two entered passwords don't match
     *
     * Proceeds to create a new user if everything was correct
     */
    private void attemptUserCreation() {

        // Reset errors
        newUserName.setError(null);
        newUserLastName.setError(null);
        newUserAddress.setError(null);
        newUserPwRp.setError(null);
        newUserPW.setError(null);

        boolean cancel = false;
        View focusView = newUserName;

        // Check if the user entered a name
        if (newEnteredName.isEmpty()) {
            newUserName.setError(getString(R.string.error_field_required));
            focusView = newUserName;
            cancel = true;
            Log.i("confirm3", "isEmpty");
        }

        // Check if the user entered a last name
        if (newEnteredLastName.isEmpty()) {
            newUserLastName.setError(getString(R.string.error_field_required));
            focusView = newUserLastName;
            cancel = true;
            Log.i("confirm3", "isEmpty");
        }

        // Check if the user entered an address
        if (newEnteredAddress.isEmpty()) {
            newUserAddress.setError(getString(R.string.error_field_required));
            focusView = newUserAddress;
            cancel = true;
            Log.i("confirm3", "isEmpty");
        }

        // Check if the user entered a password.
        if (newEnteredPW.isEmpty()) {
            newUserPW.setError(getString(R.string.error_field_required));
            focusView = newUserPW;
            cancel = true;
            Log.i("confirm3", "isEmpty");
        }

        //check if the user confirmed/repeated the password
        if (newEnteredConfirmPW.isEmpty()) {
            newUserPwRp.setError(getString(R.string.error_field_required));
            focusView = newUserPwRp;
            cancel = true;
            Log.i("confirm3", "isEmpty");
        }

        /*try the creation process*/

        if (cancel) {
            // There was an error; don't attempt user creation and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
    }

    /**
     * look up in the database if user credentials are unique
     *
     * Name and/or Address can't be the same as an already existing user
     * @return true if user is unique, false if a user with those credentials already exists
     */
    private boolean isUserUnique() {
        // start creating a user for the db
        db.open();

        if (!authNoEmpty) {
            Log.i("confirm6", "authNoEmpty is false, go!");
            return authNoEmpty;

        } else if (!authPwMatch) {
            return authPwMatch;
        } else  {
            //get existing users to check if user already exists
            Benutzer[] usersEx = db.getBenutzer();
            Boolean isUnique = false;

            //if there are any users:
            if(usersEx.length!=0){
                for (Benutzer b : usersEx) {

                    if (b.getAdresse().equalsIgnoreCase(newEnteredAddress) || b.getFullName().equalsIgnoreCase(newEnteredName + " " + newEnteredLastName)) {
                        isUnique = false;
                        Log.i("confirm5", "set as false, user exists");
                        return isUnique;
                    } else {
                        isUnique = true;
                        Log.i("confirm4", "set as true, user is unique");
                    }
                }
                return isUnique;
            }
            return true;

        }
    }
}
