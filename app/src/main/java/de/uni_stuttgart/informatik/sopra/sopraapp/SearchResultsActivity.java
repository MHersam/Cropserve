package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.userId;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Configures Searchable, Search View
 * Enables searching and calls a result list for the User to view
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */
public class SearchResultsActivity extends FragmentActivity {

    Bundle extras;
    ArrayList<String> searchResults;
    private String queryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        //handles search input/query
        handleIntent(getIntent());

        //set  list layout
        final ListView sResultLv = findViewById(android.R.id.list);
        sResultLv.setBackgroundColor(Color.WHITE);

        //list adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_search, searchResults);
        sResultLv.setAdapter(adapter);

        //action when clicking on an item in the search results
        sResultLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final String sr = adapterView.getItemAtPosition(i).toString();
                Log.i("item_pos1", sr);
                String str = sr.replaceAll("[0-9#:]+", "").trim();
                Log.i("item_pos", str);

                final AlertDialog.Builder alertD = new AlertDialog.Builder(SearchResultsActivity.this);

                //set dynamic dialog message for multi lang support
                final Resources res = getResources();
                String textF = res.getString(R.string.search_resultF, sr);
                String textS = res.getString(R.string.search_resultS, sr);
                String textC = res.getString(R.string.search_resultC, sr);
                String textDefault = res.getString(R.string.search_resultDefault);

                switch(str) {
                    case "Feld" :
                        alertD.setMessage(textF);
                        break;
                    case "Field" :
                        alertD.setMessage(textF);
                        break;
                    case "Schadensfall" :
                        alertD.setMessage(textS);
                        break;
                    case "Damage Case" :
                        alertD.setMessage(textS);
                        break;
                    default:
                        alertD.setMessage(textDefault);
                }

                //if clicked results is a contract
                String[] vertragString = getResources().getStringArray(R.array.feld_verträge);
                if(queryString != null && Arrays.asList(vertragString).contains(str)) {
                    if (Arrays.asList(vertragString).contains(queryString)) {
                        alertD.setMessage(textC);
                    }
                }

                //simple dismiss
                alertD.setNeutralButton(getString(R.string.button_okay), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                alertD.show();

            }
        });

    }

    /**
     * handles the income of an intent for this activity
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    /**
     * on search input: handle the search event
     * @param intent the search query
     */
    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            doMySearch(query);
        }
    }

    /**
     * handle the search input and generate the output list
     *
     * @param query search input from user
     * @return ArrayList with search result (displayed in a list)
     */
    private ArrayList<String> doMySearch(String query) {

        //list of all search results matching with query
        searchResults = new ArrayList<>();

        /*array list to keep results identifier to make them clickable later on*/
        queryString = query;


        String[] verträgeString = getResources().getStringArray(R.array.feld_verträge);

        //init database
        DatenbankDaten db = new DatenbankDaten(this);
        db.open();

        /*searching for specific matches: */

        //search for contracts
        if (Arrays.asList(verträgeString).contains(query)) {
            for(String s : verträgeString) {
                if(s.equalsIgnoreCase(query)) {
                    searchResults.add(s);
                }
            }
        }

        //search for Gutachter
        for(Gutachter g : db.getGutachter()) {
            if(g.getNachname().equalsIgnoreCase(query)) { //searched by name
                searchResults.add(g.getFullName());
            } else if(g.getVorname().equalsIgnoreCase(query)) { //search by first name
                searchResults.add(g.getFullName());
            } else if (g.getFullName().equalsIgnoreCase(query)) {  //search by full name
                searchResults.add(g.getFullName());
            }
        }

        //search for Felder
        for(Feld f : db.getFeld()) {
            if(f.getArt().equalsIgnoreCase(query)) {  //searched by Art
                if(userId >= 0) {
                    if(f.getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_feld) + " " + f.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_feld) + " " + f.getId());
                }
            } else if (f.getVertrag().toString().equalsIgnoreCase(query)) { //searched by Vertrag
                if(userId >= 0) {
                    if(f.getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_feld) + " " + f.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_feld) + " " + f.getId());
                }
            } else if (f.getRegion().getStadt().equalsIgnoreCase(query)) {  //searched by city
                if(userId >= 0) {
                    if(f.getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_feld) + " " + f.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_feld) + " " + f.getId());
                }
            } else if(f.getVertrag().getPerson().getFullName().equalsIgnoreCase(query)) { //...by user full
                if(userId >= 0) {
                    if(f.getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_feld) + " " + f.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_feld) + " " + f.getId());
                }
            } else if(f.getVertrag().getPerson().getVorname().equalsIgnoreCase(query)) { //...by user fore
                if(userId >= 0) {
                    if(f.getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_feld) + " " + f.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_feld) + " " + f.getId());
                }
            } else if(f.getVertrag().getPerson().getNachname().equalsIgnoreCase(query)) { //...by user sur
                if(userId >= 0) {
                    if(f.getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_feld) + " " + f.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_feld) + " " + f.getId());
                }
            }
        }

        //search for Schadensfall
        for(Schadensfall sf : db.getSchadensfall()) {
            if(sf.getGutachter().getVorname().equalsIgnoreCase(query)) { //...by reviewer fore
                if(userId >= 0) {
                    if(sf.getFeld().getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                }
            } else if( sf.getGutachter().getNachname().equalsIgnoreCase(query)) { //...by reviewer sur
                if(userId >= 0) {
                    if(sf.getFeld().getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                }
            } else if(sf.getGutachter().getFullName().equalsIgnoreCase(query)) { //...by reviewer full
                if(userId >= 0) {
                    if(sf.getFeld().getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                }
            } else if(sf.getStatus().equalsIgnoreCase(query)) {     //...by status
                if(userId >= 0) {
                    if(sf.getFeld().getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                }
            } else if(sf.getSchadensart().equalsIgnoreCase(query)) {  //...by damage type
                if(userId >= 0) {
                    if(sf.getFeld().getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                }
            } else if(sf.getRegion().getStadt().equalsIgnoreCase(query)) {  //...by region
                if(userId >= 0) {
                    if(sf.getFeld().getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                }
            } else if(sf.getFeld().getVertrag().getPerson().getVorname().equalsIgnoreCase(query)){ //...by user fore
                if(userId >= 0) {
                    if(sf.getFeld().getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                }
            } else if(sf.getFeld().getVertrag().getPerson().getNachname().equalsIgnoreCase(query)){ //...by user sur
                if(userId >= 0) {
                    if(sf.getFeld().getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                }
            } else if(sf.getFeld().getVertrag().getPerson().getFullName().equalsIgnoreCase(query)) {  //...by user full
                if(userId >= 0) {
                    if(sf.getFeld().getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_schaden) + " " + sf.getId());
                }
            }
        }

        //Search for User by name
        for(Benutzer p : db.getBenutzer()) {
            if(p.getVorname().equalsIgnoreCase(query)) {
                searchResults.add(p.getFullName());
            } else if (p.getNachname().equalsIgnoreCase(query)) {
                searchResults.add(p.getFullName());
            } else if (p.getFullName().equalsIgnoreCase(query)) {
                searchResults.add(p.getFullName());
            }
        }

        //generic word search
        if(query.equalsIgnoreCase(getString(R.string.search_feld)) || query.equalsIgnoreCase(getString(R.string.item_feld))) {
            for(Feld fm : db.getFeld()) {
                if(userId >= 0) {
                    if(fm.getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_feld) + " " + fm.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_feld) + " " + fm.getId());
                }
            }
        } else if (query.equalsIgnoreCase(getString(R.string.search_vertrag)) || query.equalsIgnoreCase(getString(R.string.item_contract))) {
            for(String s : verträgeString) {
                searchResults.add(s);
            }
        } else if (query.equalsIgnoreCase(getString(R.string.d_gutachter))) {
            for(Gutachter gm : db.getGutachter()) {
                searchResults.add(gm.getFullName());
            }
        } else if (query.equalsIgnoreCase(getString(R.string.d_vnehmer))) {
            for(Benutzer b : db.getBenutzer()) {
                searchResults.add(b.getFullName());
            }
        } else if (query.equalsIgnoreCase(getString(R.string.s_fall)) || query.equalsIgnoreCase(getString(R.string.s_schaden))) {
            for (Schadensfall sm : db.getSchadensfall()) {
                if(userId >= 0) {
                    if(sm.getFeld().getVertrag().getPerson().getId() == userId) {
                        searchResults.add(getString(R.string.sr_schaden) + " " + sm.getId());
                    }
                } else {
                    searchResults.add(getString(R.string.sr_schaden) + " " + sm.getId());
                }
            }
        } else if(searchResults.isEmpty()){
            searchResults.add(getString(R.string.search_resultno));

        }

        db.close();
        //list with all matching search results
        return searchResults;
    }



}
