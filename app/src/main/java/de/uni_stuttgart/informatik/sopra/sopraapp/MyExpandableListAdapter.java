package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;

import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.mMap;

/**
 * Class takes care of the ListView which holds the Feld-information
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;

    // header titles
    private List<String> _listDataHeader;
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public static int feldID=-1;

    /**
     * constructor
     *
     * @param context
     * @param listDataHeader
     * @param listChildData
     */
    public MyExpandableListAdapter(Context context, List<String> listDataHeader,
                                   HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }


    /**
     * method returns a child of the expandable ListView
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    /**
     * method returns the childID of the expandable ListView
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * method returns the child view of the expandable ListView
     *
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return the view of the children of the ListView
     */
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exlist_item, null);
        }

        TextView txtListChild = convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    /**
     * gets the number of children of a group
     *
     * @param groupPosition
     * @return the sum of all children
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    /**
     * method to get groups from the list
     *
     * @param groupPosition
     * @return an Object with all children
     */
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    /**
     * method returns the sum of all items
     *
     * @return int with listDataHeader size
     */
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    /**
     * get the group item ID
     *
     * @param groupPosition
     * @return id of a group item
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * get the group header view
     *
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return view for the list group header
     */
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exlist_group, null);
        }

        //edit button for every head item
        ImageButton editButton = convertView.findViewById(R.id.editbutton);
        editButton.setFocusable(false);


        final View finalConvertView = convertView;
        //the edit button opens the fragment where you can put in new fields
        // but before it set Markers on the edges of the selected field
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_context instanceof MainActivity) {
                    FragmentManager fm;
                    fm = ((MainActivity) _context).getSupportFragmentManager();
                    List<String> data = FeldFragment.getListDataHeader();
                    DatenbankDaten db = new DatenbankDaten(finalConvertView.getContext());
                    db.open();
                    Feld feld = db.getFeldById(Integer.parseInt(data.get(groupPosition).split("#")[1]));
                    //put in the field id in the static variable feldID because you need it for fragments to come
                    feldID = feld.getId();
                    //put in the vertices as markers of the follow-up fragment to model the vertices/coordinates of the selected field
                    for (int i = 0; i < feld.getKoordinaten().length - 1; i++) {
                        EditFeld.marker.add(mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(feld.getKoordinaten()[i].latitude, feld.getKoordinaten()[i].longitude))
                                .title(String.valueOf(EditFeld.marker.size() + 1))
                                .draggable(true)));
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(feld.getKoordinaten()[0].latitude, feld.getKoordinaten()[0].longitude), 15));
                    //open the editing fragment without a go back button
                    EditFeld edScreen = new EditFeld();
                    FragmentTransaction ft = fm.beginTransaction();
                    //fm.beginTransaction().replace(R.id.flContent, edScreen).commit();
                    ft.replace(R.id.flContent, edScreen).addToBackStack("tag").commit();
                    db.close();
                }
            }
        });


        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    /**
     * checks whether there are stable IDs or not
     * @return boolean
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * checks whether a child is selectable or not
     * @param groupPosition
     * @param childPosition
     * @return boolean
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}