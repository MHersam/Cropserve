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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.drawPolygons;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.location;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.mMap;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.polygons;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.userId;


/**
 * Class that capsules attributes for a damage case
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class SchadensfallFragment extends Fragment {

    //attributes
    private static String itemID;
    ArrayList<String> reportList;
    public static int schadenID = -1;

    //constructor
    public SchadensfallFragment() {
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
        return inflater.inflate(R.layout.fragment_schadensfall, container, false);
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

        //schadenID=-1;
        ListItemDetail.marker.removeAll(ListItemDetail.marker);
        SchadenErfassen.marker.removeAll(SchadenErfassen.marker);
        drawPolygons(getActivity().getApplicationContext());
        for(int i=0;i<polygons.size();i++){
            polygons.get(i).setClickable(false);
        }

        if(MainActivity.location.getVisibility() == View.VISIBLE) {
            location.setVisibility(View.INVISIBLE);
        }

        //background view
        TextView bgView = getActivity().findViewById(R.id.damage_view);
        bgView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.standard_background));

        //list that hold all dmg cases
        final ListView dmgCases_list = getActivity().findViewById(R.id.listView1);
        Button report_btn = getActivity().findViewById(R.id.dmg_rep_btn);

        //prepare list data
        getDmgListItems();


        /*
         * fill list and set view
         * list_item_dmg must be an empty text view!
         */
        ArrayAdapter<String> dmgAdapter = new ArrayAdapter<>(getContext(), R.layout.list_item_dmg, reportList);
        dmgCases_list.setAdapter(dmgAdapter);

        //listener for list items, switch view to show Details (ListItemDetail)
        dmgCases_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //get selected item
                itemID = adapterView.getItemAtPosition(i).toString();
                //schadenID = Integer.parseInt(reportList.get(Integer.parseInt(itemID.split("#")[1])));
                String itemIDMash = getItemID();
                String str = itemIDMash.replaceAll("[^-?0-9]+", " ").trim();
                schadenID = Integer.parseInt(str);
                DatenbankDaten db = new DatenbankDaten(getActivity().getApplicationContext());
                db.open();
                Schadensfall schadensfall = db.getSchadensfallById(schadenID);
                for (int j = 0; j < schadensfall.getSchadensposition().length - 1; j++) {
                    ListItemDetail.marker.add(mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(schadensfall.getSchadensposition()[j].latitude, schadensfall.getSchadensposition()[j].longitude))
                            .title(String.valueOf(EditFeld.marker.size() + 1))
                            .draggable(true)));
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(schadensfall.getSchadensposition()[0].latitude, schadensfall.getSchadensposition()[0].longitude), 15));
                db.close();
                //switch to ListItemDetail view
                FragmentManager fm = getActivity().getSupportFragmentManager();
                ListItemDetail listNam = new ListItemDetail();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.flContent, listNam).addToBackStack("tag").commit();
            }
        });

        /*Delete option for list items on long click*/
        dmgCases_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String clickedItem = adapterView.getItemAtPosition(i).toString();
                AlertDialog.Builder alertDel = new AlertDialog.Builder(getActivity());
                alertDel.setTitle(getString(R.string.del_dmg));

                //set dynamic dialog message for multi lang support
                final Resources res = getResources();
                String text = res.getString(R.string.del_confirm1, clickedItem);
                alertDel.setMessage(text);
                //cancel deleting
                alertDel.setNegativeButton(R.string.button_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int btn) {
                        getActivity().getFragmentManager().popBackStack(); //go back
                    }
                });
                //on the way to delete case
                alertDel.setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int btn2) {
                        AlertDialog.Builder alertAssure = new AlertDialog.Builder(getActivity());
                        alertAssure.setTitle(getString(R.string.del_dmg));
                        String text2 = res.getString(R.string.del_confirm2, clickedItem);
                        alertAssure.setMessage(text2);
                        /*ask for confirmation*/
                        //no, abort
                        alertAssure.setNegativeButton(R.string.button_no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int btn3) {
                                getActivity().getFragmentManager().popBackStack(); //go back
                            }
                        });
                        //yes, continue
                        alertAssure.setPositiveButton(R.string.button_del, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int btn4) {
                                String str = clickedItem.replaceAll("[^-?0-9]+", " ").trim();
                                schadenID = Integer.parseInt(str);
                                DatenbankDaten db = new DatenbankDaten(getActivity().getApplicationContext());
                                db.open();
                                /*removing the selected item from database*/
                                Schadensfall schadensfall = db.getSchadensfallById(schadenID);
                                db.removeSchadensfall(schadensfall);
                                schadenID=-1;
                                reportList.remove(clickedItem);
                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                SchadensfallFragment nam = new SchadensfallFragment();
                                FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.flContent, nam).addToBackStack("feld_tag").commit();
                                //display confirmation toast
                                String textToast = res.getString(R.string.delete_toast, clickedItem);
                                Toast.makeText(getContext(), textToast, Toast.LENGTH_SHORT).show();
                            }
                        });
                        //show 2nd dialogue
                        alertAssure.show();
                    }
                });
                //show 1st dialogue
                alertDel.show();
                return true;
            }
        });


        //report dmg cases button listener
        report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                SchadenErfassen dmgNam = new SchadenErfassen();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.flContent, dmgNam).addToBackStack("schadensfall_tag").commit();
            }
        });

    }

    /**
     * android resuming function
     */
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.item_dmg));
    }


    /**
     * pulls data from database to fill ListView with every Schadensfall existing
     *
     */
    public void getDmgListItems() {
        reportList = new ArrayList<String>();
        Integer myUser = userId;

        ArrayList<Integer> dmgCases = new ArrayList<>();
        DatenbankDaten pullDmgCases = new DatenbankDaten(getActivity().getApplicationContext());
        pullDmgCases.open();

        //get all existing IDs
        for (Schadensfall schFall : pullDmgCases.getSchadensfall()) {
            if(userId>=0){
                if (myUser.equals(schFall.getFeld().getVertrag().getPerson().getId())) {
                    dmgCases.add(schFall.getId());
                }
            }
            else{
                dmgCases.add(schFall.getId());
            }
        }

        for (int i = 0; i < dmgCases.size(); i++) {
            reportList.add(getString(R.string.s_list_data_header) + dmgCases.get(i).toString());
        }

        pullDmgCases.close();

    }

    /**
     * getter for itemID for class use
     *
     * itemID is always set as the position of an item in the damage list
     * @return String with item position ID
     */
    public String getItemID() {
        return itemID;
    }


}
