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
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.location;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.polygons;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.userId;

/*
 * Class that handles the different accounts (view and settings)
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class AccountFragment extends Fragment {
    ArrayList<String> listItems;
    DatenbankDaten db;
    Person p;
    Bundle extras;

    /**
     * constructor
     */
    public AccountFragment() {
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
        extras = getActivity().getIntent().getExtras();
        return inflater.inflate(R.layout.fragment_account, container, false);

    }


    /**
     * method is called when after the Fragment is created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //set polygons non-clickable
        for(int i=0;i<polygons.size();i++){
            polygons.get(i).setClickable(false);
        }

        //disable location button in fragment
        if(MainActivity.location.getVisibility() == View.VISIBLE) {
            location.setVisibility(View.INVISIBLE);
        }

        //setup database connection
        db = new DatenbankDaten(getActivity().getApplicationContext());
        db.open();

        //make sure database is not empty
        /*if (db.getBenutzer().length == 0 || db.getGutachter().length == 0){
            db.data();
        };*/

        //background
        TextView backG = getActivity().findViewById(R.id.bgAccount);
        backG.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.standard_background));
        TextView text = getActivity().findViewById(R.id.accText);
        text.setVisibility(View.VISIBLE);

        //if(Versicherungsnehmer) else gutachter == true
        if(userId>0){
            p = (Person) db.getBenutzerById((int) extras.getInt("userID"));
            listItems = insuredData();
        } else {
            p = (Person) db.getGutachterById((int) extras.getInt("userID"));
            listItems = expertData();
            listItems.add(getString(R.string.user_list));
            listItems.add(getString(R.string.create_user));
            listItems.add(getString(R.string.delete_user));

        }
        db.close();

        listItems.add(getString(R.string.change_password_button_text));


        ArrayAdapter <String> adapter = new ArrayAdapter<>(getContext(), R.layout.list_item_personal_data, listItems);
        ListView personListView = getActivity().findViewById(R.id.listViewAccount);
        personListView.setAdapter(adapter);

        //personal data
        personListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //this is expecting the "change password button" to be the last list item,
                // not the best solution but works for the moment
                if (position == listItems.size() - 1) {
                    try {
                        Fragment fragment = ChangePasswordFragment.class.newInstance();
                        //open the new Fragment
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.flContent, fragment).addToBackStack("tag").commit();
                        getActivity().setTitle(getString(R.string.change_password_button_text));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (position == 0){
                    new AlertDialog.Builder(getActivity())
                            //.setIcon(R.drawable.[...])
                            .setTitle(getString(R.string.hint))
                            .setPositiveButton(getString(R.string.ok),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                        }
                                    }
                            ).setMessage(getString(R.string.hint_message_text))
                            .show();
                } else if(parent.getItemAtPosition(position).toString().contains("löschen") || parent.getItemAtPosition(position).toString().contains("Delete")) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    BenutzerList insH = new BenutzerList();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.flContent, insH).addToBackStack("listIH_tag").commit();

                } else if(parent.getItemAtPosition(position).toString().contains("erstellen") || parent.getItemAtPosition(position).toString().contains("Create")) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    UserCreationFragment fam = new UserCreationFragment();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.flContent, fam).addToBackStack("creation_tag").commit();

                } else if(parent.getItemAtPosition(position).toString().contains("Versicherungsnehmer-Übersicht") || parent.getItemAtPosition(position).toString().contains("Insurance"))  {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    BenutzerList insH = new BenutzerList();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.flContent, insH).addToBackStack("listIH_tag").commit();
                }

            }
        });

    }

    /**
     * helping function to fill field data
     * @return
     */
    private ArrayList<String> insuredData(){
        ArrayList<String> list = new ArrayList<>();
        list.add(getPersonalData());
        //set dynamic string assignment for #ofFields (2nd string...), overall field area (3rd string in list item)
        final Resources res = getResources();
        String totalNumberFields = res.getString(R.string.num_fields, totalFields());
        String totalFieldArea = res.getString(R.string.allfield_area, totalArea());
        list.add(getString(R.string.acc_field) + totalNumberFields + totalFieldArea);
        return list;
    }

    /**
     * helping function for the reviewer
     * @return
     */
    private ArrayList<String> expertData(){
        ArrayList<String> list = new ArrayList<>();
        list.add(getPersonalData());
        return list;
    }

    /**
     * helping function for adding personal data
     * @return
     */
    private String getPersonalData(){
        StringBuilder s = new StringBuilder();
        s.append(getString(R.string.name)+ " "  + p.getFullName()+ "\n");
        s.append(getString(R.string.adress) + " "  + p.getAdresse() + "\n");
        s.append(getString(R.string.role)+ " " + extras.getString("role") + "\n");
        s.append(getString(R.string.id) + " " + p.getId());
        return s.toString();
    }

    /**
     * android resuming function
     */
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.item_acc));
    }


    /**
     * Searches for all fields a user has
     * and marks down the total number
     *
     * @return number of fields for this user
     */
    public Integer totalFields() {
        int fieldNumber = 0;

        //open db
        ArrayList<Feld> allFields = new ArrayList<>();
        DatenbankDaten pullTotalFields = new DatenbankDaten(getActivity().getApplicationContext());
        pullTotalFields.open();

        //add all user fields to list
        for(Feld f : pullTotalFields.getFeld()) {
            if(f.getVertrag().getPerson().getId() == p.getId()) {
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
     * Pulls field area data from database and adds it up
     * to find out overall covered area by this user's fields
     *
     * @return number of total field area
     */
    public String totalArea() {
        double fieldAreas = 0.0;

        //open db
        DatenbankDaten pullFields = new DatenbankDaten(getActivity().getApplicationContext());
        pullFields.open();

        //get all fields and sum up field area
        for(Feld f : pullFields.getFeld()) {
            if(f.getVertrag().getPerson().getId() == userId) {
                fieldAreas = fieldAreas + f.getFlaeche();
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        fieldAreas = new BigDecimal(fieldAreas).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return df.format(fieldAreas);
    }
}
