package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.location;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.polygons;


/**
 * Class that indicates a plan how to use the app
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class HilfeFragment extends Fragment {


    /**
     * constructor
     */
    public HilfeFragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_hilfe, container, false);
    }

    /**
     * method that initialize the fragment
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //blank page
        TextView view1 = getActivity().findViewById(R.id.view_help);
        view1.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.standard_background));


        for(int i=0;i<polygons.size();i++){
            polygons.get(i).setClickable(false);
        }

        //disable location button in fragment
        if(MainActivity.location.getVisibility() == View.VISIBLE) {
            location.setVisibility(View.INVISIBLE);
        }


        //list view for adding the helping info
        ListView list = getActivity().findViewById(R.id.listviewHelp);
        String[] items = {getString(R.string.help_Start), getString(R.string.help_Menü), getString(R.string.help_Feldererfassen), getString(R.string.help_Feldereditieren),
                getString(R.string.help_Felderlöschen), getString(R.string.help_Feldersubmenü),
                getString(R.string.help_Schadenerfassen), getString(R.string.help_Schadeneditieren),
                getString(R.string.help_Schadenlöschen), getString(R.string.help_Verträgeansicht),
                getString(R.string.help_Accountansicht),getString(R.string.help_Creation), getString(R.string.help_Deletion) ,getString(R.string.help_AppInfo), getString(R.string.help_Logout)};
        //setting the size for each item to 1200px
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.list_item_search, items){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the current item from ListView
                View view = super.getView(position,convertView,parent);

                // Get the Layout Parameters for ListView Current Item View
                ViewGroup.LayoutParams params = view.getLayoutParams();

                // Set the height of the Item View
               // params.height = 1500;
               // view.setLayoutParams(params);

                return view;
            }
        };

        list.setAdapter(adapter);
    }


    /**
     * android resuming function
     */
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.item_help));
    }

}
