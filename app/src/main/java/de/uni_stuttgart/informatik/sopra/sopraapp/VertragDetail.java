package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.location;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.polygons;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.userId;


/*
 * Holds and sets information to be displayed in the detailed contract view
 * Fills and generates corresponding lists
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class VertragDetail extends Fragment {

    ArrayList<String> fieldList;


    /**
     * constructor
     */
    public VertragDetail() {
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
        if (container != null) {
            container.removeAllViews();
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.contract_detail, container, false);
    }


    /**
     * Initialises the fragment and fills the view
     * with pre-assigned layout elements
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //background
        TextView background = getActivity().findViewById(R.id.tvBG);
        background.setBackgroundColor(Color.WHITE);

        //set polygons to be non-clickable
        for(int i=0;i<polygons.size();i++){
            polygons.get(i).setClickable(false);
        }

        //set gps button invisible
        if (MainActivity.location.getVisibility() == View.VISIBLE) {
            location.setVisibility(View.INVISIBLE);
        }

        //header text
        TextView coDescrHeader = getActivity().findViewById(R.id.coDescrHeader);

        //dynamic string assignment for contract headers
        final Resources res = getResources();
        String text = res.getString(R.string.contract_header, VertragFragment.itemID);
        coDescrHeader.setText(text);

        //description text
        TextView coDescription = getActivity().findViewById(R.id.coDescr);

        //selected item from VertragFragment
        String clickedItem = VertragFragment.itemID;

        //contract information texts
        if (clickedItem.equalsIgnoreCase(getResources().getStringArray(R.array.feld_verträge)[0])) {
            coDescription.setText(R.string.contract1);
        } else if (clickedItem.equalsIgnoreCase(getResources().getStringArray(R.array.feld_verträge)[1])) {
            coDescription.setText(R.string.contract2);
        } else if (clickedItem.equalsIgnoreCase(getResources().getStringArray(R.array.feld_verträge)[2])) {
            coDescription.setText(R.string.contract3);
        } else if (clickedItem.equalsIgnoreCase(getResources().getStringArray(R.array.feld_verträge)[3])) {
            coDescription.setText(R.string.contract4);
        } else if (clickedItem.equalsIgnoreCase(getResources().getStringArray(R.array.feld_verträge)[4])) {
            coDescription.setText(R.string.contract5);
        } else if (clickedItem.equalsIgnoreCase(getResources().getStringArray(R.array.feld_verträge)[5])) {
            coDescription.setText(R.string.contract6);
        } else if (clickedItem.equalsIgnoreCase(getResources().getStringArray(R.array.feld_verträge)[6])) {
            coDescription.setText(R.string.contract7);
        }


        //list that hold all dmg cases
        final ListView list = getActivity().findViewById(R.id.coFields);


        //prepare list data
        getFieldListItems();

        /*
         * fill list with data and set view
         * list_item_dmg_attribute must be an empty text view!
         */
        ArrayAdapter<String> dmgAdapter = new ArrayAdapter<String>(getContext(), R.layout.list_item_dmg_attribute, fieldList);
        list.setAdapter(dmgAdapter);
    }




    /*
     * pulls data from database to fill ListView with every Schadensfall existing
     *
     */
    public void getFieldListItems() {
        fieldList = new ArrayList<String>();

        ArrayList<Integer> fields = new ArrayList<>();
        DatenbankDaten pullFields = new DatenbankDaten(getActivity().getApplicationContext());
        pullFields.open();

        //get all existing IDs
        for (Feld feld : pullFields.getFeld()) {
            //check if there are fields with that insurance type
            if(feld.getVertrag().getArt().equalsIgnoreCase(VertragFragment.itemID)) {
                Log.i("hello", "here");
                if (userId >= 0) {
                    if (userId == feld.getVertrag().getPerson().getId()) {
                        Log.i("hello", "here-why");
                        fields.add(feld.getId());
                    }
                } else {
                    Log.i("hello", "here-why2");
                    fields.add(feld.getId());
                }
            }
        }

        if(fields.isEmpty() && fieldList.isEmpty()) {
            Log.i("hello","here3");
            fieldList.add(getString(R.string.no_fields));
        }


        for (int i = 0; i < fields.size(); i++) {
                fieldList.add(getString(R.string.list_data_header) + fields.get(i).toString());
        }
        pullFields.close();

    }
}
