package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.drawPolygons;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.location;

/**
 * this fragment is just a blank one to show the map of the main Activity
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class Maps extends Fragment  {
    /**
     * constructor
     */
    public Maps() {
        // Required empty public constructor
    }

    /**
     * method create the view from the layout
     * @param inflater inflater for layout
     * @param container the container
     * @param savedInstanceState saved Instance
     * @return the inflated map layout
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_maps, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawPolygons(getActivity().getApplicationContext());

        if(MainActivity.location.getVisibility() == View.INVISIBLE) {
            location.setVisibility(View.VISIBLE);
        }
    }

    /**
     * android resuming function
     */
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.item_start));

        //set gps button visible
        if (MainActivity.location.getVisibility() == View.INVISIBLE) {
            location.setVisibility(View.VISIBLE);
        }
    }
}
