package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;


/**
 * This Activity lets the user log out from the session/his account
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class LogOutFragment extends DialogFragment {

    //constructor
    public static LogOutFragment newInstance() {
        LogOutFragment frag = new LogOutFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    /**
     * calls a dialogue pop up when activity is called
     *
     * @param savedInstanceState prior (if existing) state
     * @return dialogue window
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                //.setIcon(R.drawable.[...])
                .setTitle(R.string.logout_dialog_title)
                .setPositiveButton(R.string.positive_logout_dialog_option,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((MainActivity)getActivity()).logoutUser();
                            }
                        }
                )
                .setNegativeButton(R.string.negative_logout_dialog_option,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((MainActivity)getActivity()).negativeClickLogoutDialog();
                            }
                        }
                )
                .create();
    }
}