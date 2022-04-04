package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Integer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static de.uni_stuttgart.informatik.sopra.sopraapp.FeldErfInfoScreen.marker;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.drawPolygons;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.location;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.polygons;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.userId;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MyExpandableListAdapter.feldID;


/**
 * Class takes care of the fields
 * Handles editing and adding new fields
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */
public class FeldFragment extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    public static List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    public static int feldCounter;

    /**
     * constructor
     */
    public FeldFragment() {
        // Required empty public constructor
    }

    /**
     * Creates the fragment instance
     * method is called when fragment is created
     *
     * @param savedInstanceState previous instance
     */
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
        return inflater.inflate(R.layout.fragment_feld, container, false);
    }


    /**
     * method sets up the view after it is created
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        feldID = -1;

        //disable polygons in background
        for(int i=0;i<polygons.size();i++){
            polygons.get(i).setClickable(false);
        }

        EditFeld.marker.removeAll(EditFeld.marker);
        FeldErfInfoScreen.marker.removeAll(FeldErfInfoScreen.marker);
        drawPolygons(getActivity().getApplicationContext());

        //set layout background view
        TextView view1 = getActivity().findViewById(R.id.field_view);
        view1.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.standard_background));


        //set gps button invisible
        if (MainActivity.location.getVisibility() == View.VISIBLE) {
            location.setVisibility(View.INVISIBLE);
        }

        // get the ListView for displaying fields
        expListView = getActivity().findViewById(R.id.lvExp);
        expListView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        // preparing list data
        prepareListData();

        listAdapter = new MyExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //long-click for deleting an item
        expListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int listPos = position;
                int itemType = ExpandableListView.getPackedPositionType(id);

                if (itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    //no long click on child
                    return false;

                } else if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                    position = ExpandableListView.getPackedPositionGroup(id);

                    final String clickedItem = getListDataHeader().get(position).toString();

                    //do your per-group callback here
                    AlertDialog.Builder alertDelete = new AlertDialog.Builder(getActivity());
                    alertDelete.setTitle(R.string.del_feld);
                    //string formatting for language support
                    final Resources res = getResources();
                    String text = res.getString(R.string.del_confirm1, clickedItem);
                    alertDelete.setMessage(text);

                    //cancel deleting
                    alertDelete.setNegativeButton(R.string.button_no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int btn) {
                            getActivity().getFragmentManager().popBackStack(); //go back
                        }
                    });

                    //on the way to delete case
                    alertDelete.setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int btn2) {
                            AlertDialog.Builder alertAssure = new AlertDialog.Builder(getActivity());
                            alertAssure.setTitle(getString(R.string.del_feld));
                            //string formatting for language support
                            String text1 = res.getString(R.string.del_confirm2, clickedItem);
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
                                public void onClick(DialogInterface dialogInterface, int btn4) {
                                    String str = listDataHeader.get(listPos);
                                    feldID = Integer.parseInt(str.split("#")[1]);
                                    Log.e("test", String.valueOf(feldID));
                                    Log.e("test", str);
                                    boolean mitVertrag = true;
                                    if (str.equals(getString(R.string.feldOhneVertrag) + str.split("#")[1]))
                                        mitVertrag = false;
                                    DatenbankDaten db = new DatenbankDaten(getActivity().getApplicationContext());
                                    db.open();

                                    /*removing the selected item from database*/
                                    Feld feld = null;
                                    if (mitVertrag) feld = db.getFeldById(feldID);
                                    if (mitVertrag) db.removeFeld(feld);
                                    feldID = -1;
                                    listDataHeader.remove(listPos);
                                    FragmentManager fm = getActivity().getSupportFragmentManager();
                                    FeldFragment nam = new FeldFragment();
                                    FragmentTransaction ft = fm.beginTransaction();
                                    ft.replace(R.id.flContent, nam).addToBackStack("feld_tag").commit();
                                    //display confirmation toast
                                    String textToast = res.getString(R.string.delete_toast, clickedItem);
                                    Toast.makeText(getContext(), textToast, Toast.LENGTH_SHORT).show();
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

        /*Creating a new field*/
        Button feldReg = getView().findViewById(R.id.freg_button);
        //Button for opening the fragment for putting in new fields
        feldReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FeldErfInfoScreen nam = new FeldErfInfoScreen();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.flContent, nam).addToBackStack("feld_tag").commit();
            }
        });

        marker.removeAll(marker);
    }


    /**
     * android resuming function
     */
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.item_feld));
    }

    /**
     * Gets field data from database
     *
     * Fills list with all available fields
     * and their information
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        Integer myUser = userId;

        // Adding Header Data - Field Names
        DatenbankDaten pullFieldData = new DatenbankDaten(getActivity().getApplicationContext());
        ArrayList<Integer> fieldIDs = new ArrayList<>();
        pullFieldData.open();

        //get all existing IDs
        for (Feld feld : pullFieldData.getFeld()) {
            if (myUser >= 0) {
                if (myUser.equals(feld.getVertrag().getPerson().getId())) {
                    fieldIDs.add(feld.getId());
                }
            } else {
                fieldIDs.add(feld.getId());
            }
        }

        //add all IDs to listDataHeader
        for (int x = 0; x < fieldIDs.size(); x++) {
            listDataHeader.add(getString(R.string.list_data_header) + fieldIDs.get(x).toString());
        }


        List<String> sublist;
        //fill ListView with additional information of the field
        for (int i = 0; i < fieldIDs.size(); i++) {
            // Adding child data (art, flaeche, region ,vertrag)
            sublist = new ArrayList<>();
            sublist.add(getString(R.string.l_art)+ " " + pullFieldData.getFeldById(fieldIDs.get(i)).getArt() + "\n");
            sublist.add(getString(R.string.l_fläche) + " "+ pullFieldData.getFeldById(fieldIDs.get(i)).getFlaeche() + " " + getString(R.string.l_maß) + "\n");
            sublist.add(getString(R.string.l_stadt) + " "+ pullFieldData.getFeldById(fieldIDs.get(i)).getRegion().getStadt() + "\n");
            sublist.add(getString(R.string.l_landkreis)+ " " + pullFieldData.getFeldById(fieldIDs.get(i)).getRegion().getLandkreis() + "\n");
            sublist.add(getString(R.string.l_bland) + " "+ pullFieldData.getFeldById(fieldIDs.get(i)).getRegion().getBundesland() + "\n");
            sublist.add(getString(R.string.l_land) + " "+ pullFieldData.getFeldById(fieldIDs.get(i)).getRegion().getLand() + "\n");
            sublist.add(getString(R.string.l_vertrag)+ " " + pullFieldData.getFeldById(fieldIDs.get(i)).getVertrag().getArt() + "\n");
            sublist.add(getString(R.string.chosen_user) + " " + pullFieldData.getFeldById(fieldIDs.get(i)).getVertrag().getPerson().getFullName() + "\n");
            listDataChild.put(listDataHeader.get(i), sublist);
        }
        pullFieldData.close();
    }


    /**
     * get the list of list headers for the expandable list view
     * @return listDataHeader list
     */
    public static List<String> getListDataHeader() {
        return listDataHeader;
    }


}