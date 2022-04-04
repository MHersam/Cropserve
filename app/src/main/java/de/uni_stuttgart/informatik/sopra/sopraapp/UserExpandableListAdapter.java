package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Class takes care of the ListView which holds the Users
 * under the list found in accounts of reviewers
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class UserExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;

    // header titles
    private List<String> _listDataHeader;
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;


    /**
     * constructor
     *
     * @param context
     * @param listDataHeader
     * @param listChildData
     */
    public UserExpandableListAdapter (Context context, List<String> listDataHeader,
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
            convertView.setBackgroundColor(ContextCompat.getColor(parent.getContext(), R.color.expandable_list_child_color));

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
            convertView = infalInflater.inflate(R.layout.exlistuser_group, null);
            convertView.setBackgroundColor(ContextCompat.getColor(parent.getContext(), R.color.standard_background));
        }

        final View finalConvertView = convertView;

        TextView lblListHeader = convertView.findViewById(R.id.userListHeader);
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