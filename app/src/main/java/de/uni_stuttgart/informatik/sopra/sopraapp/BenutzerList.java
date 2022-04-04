package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.location;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.polygons;


/**
 * Fragment to hold a list view for current users of this app
 *
 * Only called and thus accessible for reviewers in the app's context
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class BenutzerList extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    public static List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    Benutzer deleteP;

    //constructor
    public BenutzerList() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_benutzer_liste, container, false);
    }


    /**
     * method is called when after the Fragment is created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //set background
        TextView view1 = getActivity().findViewById(R.id.userListTV);
        view1.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.standard_background));


        //disable polygons in background
        for(int i=0;i<polygons.size();i++){
            polygons.get(i).setClickable(false);
        }

        //disable location button in fragment
        if(MainActivity.location.getVisibility() == View.VISIBLE) {
            location.setVisibility(View.INVISIBLE);
        }


        //initialize list and set adapter
        expListView = getActivity().findViewById(R.id.userOverviewList);
        expListView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        // preparing list data
        prepareListData();

        listAdapter = new UserExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        //info.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.standard_background));


        expListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                final int listPos = position;
                int itemType = ExpandableListView.getPackedPositionType(id);

                //don't work for child views
                if (itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    //no long click on child
                    return false;
                //long click only for group views
                } else if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                    position = ExpandableListView.getPackedPositionGroup(id);
                    Log.i("user_name2", Integer.toString(position));

                    //string contained in first (0) child of clicked head
                    String child = listAdapter.getChild(position, 0).toString();
                    String trimmedChild = child.replaceAll("[^?0-9]+", " ").trim();
                    //actual user ID trimmed from string
                    final int childUserId = Integer.parseInt(trimmedChild);


                    final String clickedItem = getListDataHeader().get(position).toString();

                    //try to get userId for the clicked user in the expandable list
                    HashMap<String, List<String>> list = getListDataChild();

                    //do your per-group callback here
                    AlertDialog.Builder alertDelete = new AlertDialog.Builder(getActivity());
                    alertDelete.setTitle(getString(R.string.delete_user));

                    final Resources res = getResources();
                    String text = res.getString(R.string.delete_user_d1, clickedItem);
                    alertDelete.setMessage(text);

                    //cancel deleting
                    alertDelete.setNegativeButton(R.string.button_no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int btn) {
                            getActivity().getFragmentManager().popBackStack(); //go back
                        }
                    });
                    //on the way to delete the user
                    alertDelete.setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int btn2) {
                            AlertDialog.Builder alertAssure = new AlertDialog.Builder(getActivity());
                            alertAssure.setTitle(getString(R.string.delete_user));
                            //string formatting for language support
                            String text1 = res.getString(R.string.delete_user_dConfirm, clickedItem);
                            alertAssure.setMessage(text1);
                            /*ask for confirmation*/
                            //no, abort
                            alertAssure.setNegativeButton(R.string.button_no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int btn3) {
                                    getActivity().getFragmentManager().popBackStack(); //go back
                                }
                            });
                            //yes, continue
                            alertAssure.setPositiveButton(getString(R.string.button_del), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String theUsersName = listDataHeader.get(listPos);

                                    //open database for deletion
                                    DatenbankDaten db = new DatenbankDaten(getActivity().getApplicationContext());
                                    db.open();

                                    Log.i("user_name",theUsersName);
                                    //remove the selected person from the database
                                    for(Benutzer b : db.getBenutzer()) {
                                        if(b.getFullName().equalsIgnoreCase(theUsersName)) {
                                            if(b.getId() == childUserId) {
                                                deleteP = b;
                                            }
                                        }
                                    }
                                    Log.i("user_name2", deleteP.getFullName());
                                    //remove user + fields + damage cases and other references from database
                                    db.removeBenutzer(deleteP);

                                    getFragmentManager().popBackStack();

									/*
                                    FragmentManager fm = getActivity().getSupportFragmentManager();
                                    BenutzerList insH = new BenutzerList();
                                    FragmentTransaction ft = fm.beginTransaction();
                                    ft.replace(R.id.flContent, insH).addToBackStack("listIH_tag").commit(); */

                                    db.close();
                                }
                            });
                            alertAssure.show();
                        }
                    });
                    //show dialogue
                    alertDelete.show();
                    return true; //true if we consumed the click, false if not
                } else {
                    // null item; we don't consume the click
                    return false;
                }
            }
        });
    }


    /**
     * prepares the list with all user
     * (excl. reviewers) from the database
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding Header Data - User Names
        DatenbankDaten pullUserNames = new DatenbankDaten(getActivity().getApplicationContext());
        ArrayList<String> namesFull = new ArrayList<>();
        ArrayList<Integer> corresUserId = new ArrayList<>();
        pullUserNames.open();


        //get all existing users with their names
        for (Benutzer benutzer : pullUserNames.getBenutzer()) {
            namesFull.add(benutzer.getFullName());
            corresUserId.add(benutzer.getId());

        }

        //add all names to listDataHeader
        for (int x = 0; x < namesFull.size(); x++) {
            listDataHeader.add(namesFull.get(x).toString());
        }

        /*children*/
        List<String> sublist;
        //fill ListView with additional information about the user
        for (int i = 0; i < namesFull.size(); i++) {
            // List for holding child data (user id, address, number of fields, number of damage cases)
            sublist = new ArrayList<>();

            //dynamic string resource for total fields
            final Resources res = getResources();
            String totalNumberFields = res.getString(R.string.num_fields, totalFields(corresUserId.get(i)));
            String totalDamageCases = res.getString(R.string.num_damages, totalDamages(corresUserId.get(i)));

            //fill  sublist for children
            sublist.add(getString(R.string.id) + " " + corresUserId.get(i) + "\n");
            sublist.add(getString(R.string.adress) + " " + pullUserNames.getBenutzerById(corresUserId.get(i)).getAdresse() + "\n");
            sublist.add(totalNumberFields);
            sublist.add(totalDamageCases + "\n");


            listDataChild.put(listDataHeader.get(i), sublist);
        }
        pullUserNames.close();
    }


    /**
     * Searches for all fields a user has
     * and marks down the total number
     *
     * @param id is equivalent to the user from our for-loop
     * @return number of fields for this user
     */
    public Integer totalFields(Integer id) {
        int fieldNumber = 0;

        //open db
        ArrayList<Feld> allFields = new ArrayList<>();
        DatenbankDaten pullTotalFields = new DatenbankDaten(getActivity());
        pullTotalFields.open();

        //add all user fields to list
        for(Feld f : pullTotalFields.getFeld()) {
            if(f.getVertrag().getPerson().getId() == id) {
                allFields.add(f);
            }
        }

        pullTotalFields.close();

        //get # of fields by checking the list size
        if(!allFields.isEmpty()) {
            fieldNumber = allFields.size();
        } else{
            fieldNumber = 0;
        }

        return fieldNumber;
    }

    /**
     * Searches for all damage cases a user has
     * and marks down the total number
     *
     * @param id is equivalent to the user from our for-loop
     * @return number of damage cases for this user
     */
    public Integer totalDamages(Integer id) {
        int damagesNumber = 0;

        //open db
        ArrayList<Schadensfall> allDamages = new ArrayList<>();
        DatenbankDaten pullTotalDamages = new DatenbankDaten(getActivity());
        pullTotalDamages.open();

        //add all user fields to list
        for(Schadensfall s : pullTotalDamages.getSchadensfall()) {
            if(s.getFeld().getVertrag().getPerson().getId() == id) {
                allDamages.add(s);
            }
        }

        pullTotalDamages.close();

        //get # of damage cases by checking the list size
        if(!allDamages.isEmpty()) {
            damagesNumber = allDamages.size();
        } else{
            damagesNumber = 0;
        }

        return damagesNumber;
    }


    /**
     * get the list of list headers for the expandable list view
     * @return listDataHeader list
     */
    public static List<String> getListDataHeader() {
        return listDataHeader;
    }

    /**
     * get the HashMap of child data for the expandable list view
     * @return listDataChild HashMap
     */
    public  HashMap<String,List<String>> getListDataChild() { return listDataChild; }
}
