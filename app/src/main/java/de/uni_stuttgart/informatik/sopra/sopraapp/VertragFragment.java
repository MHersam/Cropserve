package de.uni_stuttgart.informatik.sopra.sopraapp;

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

import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.location;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.polygons;


/**
 * Handles the view for the contracts
 * Contracts are supplied by a string array (>values)
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class VertragFragment extends Fragment {

    static String itemID;

    /**
     * constructor
     */
    public VertragFragment() {
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
        return inflater.inflate(R.layout.fragment_vertrag, container, false);
    }


    /**
     * method initializes the fragment
     * and fills the view
     * with pre-assigned layout elements
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //set background
        TextView contractBg = getActivity().findViewById(R.id.contractBg);
        contractBg.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.standard_background));

        //set polygon non-clickable
        for(int i=0;i<polygons.size();i++){
            polygons.get(i).setClickable(false);
        }

        //list that holds all contracts
        final ListView contract_list = getActivity().findViewById(R.id.contractList);

        //disable location button in fragment
        if(MainActivity.location.getVisibility() == View.VISIBLE) {
            location.setVisibility(View.INVISIBLE);
        }


        /*
         * fill list and set view
         * list_item_contracts must be an empty text view!
         */
        ArrayAdapter<CharSequence> contractAdapter = ArrayAdapter.createFromResource(getContext(),  R.array.feld_verträge, R.layout.list_item_contracts);
        contract_list.setAdapter(contractAdapter);

        //set list item click event
        contract_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //get clicked item
                itemID = adapterView.getItemAtPosition(i).toString();

                FragmentManager fm = getActivity().getSupportFragmentManager();
                VertragDetail contractNam = new VertragDetail();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.flContent, contractNam).addToBackStack("vertrag_tag").commit();

            }
        });

    }


    /**
     * android resuming function
     */
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.item_contract));
    }



}
