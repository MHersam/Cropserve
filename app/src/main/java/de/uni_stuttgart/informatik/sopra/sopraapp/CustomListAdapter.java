package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * Custom Adapter class
 *
 * Custom Adapter for About Cropserve list
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class CustomListAdapter extends BaseAdapter {

    //attributes
    private ArrayList<String> listData;
    private LayoutInflater layoutInflater;

    //constructor
    public CustomListAdapter(Context context, ArrayList<String> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Gets the total number of row counts for the list view
     * Typically the size of the list passed as input.
     *
     * @return total number of row counts for list view
     */
    @Override
    public int getCount() {
        return listData.size();
    }

    /**
     * Gets the object (item) from the passed row position
     *
     * @param position row position in list
     * @return object representing data for each row.
     */
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    /**
     * Returns the unique integer position id that represents each list row item

     *
     * @param position of list row
     * @return integer that represent the list position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Assembles the list view for each row with the specified texts (or the like)
     *
     * Get a View that displays the data at the specified position in the data set
     *
     * Inflate list layout and update values on list row
     *
     * @param position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible; check for non-null recommended
     * @param parent The parent that this view will eventually be attached to
     *
     * @return view instance that represents a single row in ListView item
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_layout_custom, null);
            holder = new ViewHolder();
            holder.headerView = convertView.findViewById(R.id.info_title);
            holder.detailedInfoView = convertView.findViewById(R.id.info_detail);
            convertView.setBackgroundColor(ContextCompat.getColor(parent.getContext(), R.color.standard_background));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //add up int to move positions; always two items in one row
        if(position != 0 && position != 2 && position != 3) {
            position = position +1;
        } else if (position == 2) {
            position += 2;
        }


        //set list item content depended on listData and list position
        if(position < listData.size()-1) {
            if(position != 3 && position <= 4) {
                holder.headerView.setText(listData.get(position));
                holder.detailedInfoView.setText(listData.get(position + 1));
            }
        }
        // Return the completed view to render on screen
        return convertView;
    }


    /**
     *
     * @param ev
     * @return
     */
    public boolean dispatchTouchEvent(MotionEvent ev){
        if(ev.getAction()==MotionEvent.ACTION_MOVE) {
            return true;
        }

        return dispatchTouchEvent(ev);
    }

    //ViewHolder inner class
    static class ViewHolder {
        TextView headerView;
        TextView detailedInfoView;
    }

}
