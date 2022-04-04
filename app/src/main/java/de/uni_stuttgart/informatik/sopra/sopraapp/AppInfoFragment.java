package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.location;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.polygons;

/*
 * Class that handles the view holding the app info
 *
 * Makes new configuration for new locale after language change
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class AppInfoFragment extends Fragment {

    //attributes
    Locale myLocale;

    /**
     * constructor
     */
    public AppInfoFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_info, container, false);
    }


    /**
     * method do the initializing for the Fragment
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //blank page
        TextView view1 = getActivity().findViewById(R.id.view_appinf);
        view1.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.standard_background));

        //disable polygon clicking
        for(int i=0;i<polygons.size();i++){
            polygons.get(i).setClickable(false);
        }

        //set gps button invisible
        if (MainActivity.location.getVisibility() == View.VISIBLE) {
            location.setVisibility(View.INVISIBLE);
        }

        //initialize list and set adapter
        ListView info = getActivity().findViewById(R.id.appIList);
        info.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.standard_background));

        info.setAdapter(new CustomListAdapter(getContext(), getListData()));
        //un-movable list
        info.setEnabled(false);

        //language switch
        Spinner language = getActivity().findViewById(R.id.spinnerLanguage);
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add(getString(R.string.spinner_lang));
        spinnerArray.add(getString(R.string.lang_de));
        spinnerArray.add(getString(R.string.lang_en));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language.setAdapter(adapter);

        //language switch listener
        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1) {  //german
                    PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit().putString("LANG", "de").commit();
                    Toast.makeText(getContext(), getString(R.string.lang_C_De), Toast.LENGTH_LONG).show();
                    setNLocale("de");
                } else if( i == 2) {  //english
                    PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit().putString("LANG", "en").commit();
                    Toast.makeText(getContext(), getString(R.string.lang_C_En), Toast.LENGTH_LONG).show();
                    setNLocale("en");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Auto-generated method stub
            }
        });
    }

    /**
     * android resuming function
     */
    @Override
    public void onResume() {
        super.onResume();
        //set correct toolbar title
        getActivity().setTitle(getString(R.string.item_app));
    }

    /**
     * Sets locale to display text in chosen language
     *
     * closes app to apply changes
     *
     * @param lang language chosen from Spinner
     */
    public void setNLocale(String lang) {

        Configuration conf = getContext().getResources().getConfiguration();
        myLocale = new Locale(lang);
        Resources res = getContext().getResources();

        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();

        Locale.setDefault(myLocale);
        conf.locale = myLocale; // API 17+ only.

        res.updateConfiguration(conf, dm);

        //closes app to process config changes
        getActivity().finish();


    }

    /**
     * Generate a list to fill with app information
     *
     * @return List filled with some app information
     */
    private ArrayList<String> getListData() {
        ArrayList<String> listData = new ArrayList<>();

        String appName = getString(R.string.app_name);
        String appNameDetail = "\u00A9 2017," + " "  + getString(R.string.app_name);

        String appVersion = "App Version";
        String appVersionDetail = "Version v1.6";

        String appPerm = getString(R.string.permissions_info);
        String appPermDetail = getString(R.string.permissions_Detail);

        listData.add(appName);
        listData.add(appNameDetail);
        listData.add(appVersion);
        listData.add(appVersionDetail);
        listData.add(appPerm);
        listData.add(appPermDetail);


        return listData;
    }

}
