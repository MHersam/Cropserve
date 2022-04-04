package de.uni_stuttgart.informatik.sopra.sopraapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.polygons;

/*
 * Class that handles the changing password progress for the account fragment
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class ChangePasswordFragment extends Fragment {
    private TextView pwCurrent;
    private TextView pwNew;
    private TextView pwNewConfirm;
    private Button btnChange;
    private Bundle extras;
    private DatenbankDaten db;

    /**
     * constructor
     */
    public ChangePasswordFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_change_password, container, false);

        //set polygon non-clickable
        for(int i=0;i<polygons.size();i++){
            polygons.get(i).setClickable(false);
        }

        //get views
        pwCurrent = (TextView) rootView.findViewById(R.id.pw_old);
        pwNew = (TextView) rootView.findViewById(R.id.pw_new1);
        pwNewConfirm = (TextView) rootView.findViewById(R.id.pw_new2);
        btnChange = (Button) rootView.findViewById(R.id.change_pw__button);
        extras = getActivity().getIntent().getExtras();

        pwNewConfirm.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int id, KeyEvent event) {
                if (event == null) {
                    btnChange.performClick();
                    return true;
                }
                return false;
            }
        });

        //change pw button
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean condition1=true;
                Boolean condition2=true;
                Boolean condition3=true;
                //check if the old pw is the login pw
                if (!validateOldPW(extras.getInt("userID"), pwCurrent.getText().toString(), extras.getString("role"))) {
                    pwCurrent.setError(getString(R.string.wrong_password));
                    condition1 = false;
                    pwCurrent.requestFocus();
                }
                //check if new pw equals the confirming pw
                if(!pwNew.getText().toString().equals(pwNewConfirm.getText().toString())){
                    pwNew.setError(getString(R.string.passwords_unequal));
                    pwNewConfirm.setError(getString(R.string.passwords_unequal));
                    condition2=false;
                    if(condition1) {
                        pwNew.requestFocus();
                    }
                }
                //check if the pw is at least 4 chars long
                if(pwNew.getText().toString().length()<4 && condition2){
                    pwNew.requestFocus();
                    pwNew.setError(getString(R.string.password_min_length));
                    pwNewConfirm.setError(getString(R.string.password_min_length));
                    condition3=false;
                }
                //when everything is alright then do a confirming toast
                if(condition1&&condition2&&condition3){
                    changePW(extras.getInt("userID"), pwNew.getText().toString(), extras.getString("role"));
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    Toast.makeText(getActivity().getApplicationContext(), R.string.password_change_successful, Toast.LENGTH_LONG).show();
                    getFragmentManager().popBackStackImmediate();
                }
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    /**
     * helping method to check if the old pw is equal to the login pw
     * @param id user id
     * @param pw password
     * @param role  current user role
     * @return boolean for whether pw is correct or not
     */
    private Boolean validateOldPW(int id, String pw, String role){
        //setup database connection
        db = new DatenbankDaten(getActivity().getApplicationContext());
        db.open();
        boolean b;
        if(extras.getString("role").equals(getResources().getStringArray(R.array.user_role_selection)[0])){
            b = db.getBenutzerById(id).getPasswort().equals(pw);
            db.close();
            return b;
        } else {
            b = db.getGutachterById(id).getPasswort().equals(pw);
            db.close();
            return b;
        }
    }

    /**
     * helping method that change the pw in the database
     * @param id user id
     * @param pw new password
     * @param role user role
     */
    private void changePW(int id, String pw, String role){
        db = new DatenbankDaten(getActivity().getApplicationContext());
        db.open();
        if(role.equals(getResources().getStringArray(R.array.user_role_selection)[0])){
            Benutzer p = db.getBenutzerById(id);
            Benutzer pNew = new Benutzer(p.getVorname(), p.getNachname(), p.getAdresse(), p.getId(), pw);
            db.updateBenutzer(pNew);
        }else{
            Gutachter p = db.getGutachterById(id);
            Gutachter pNew = new Gutachter(p.getVorname(), p.getNachname(), p.getAdresse(), p.getId(), pw);
            db.updateGutachter(pNew);
        }
    }

}
