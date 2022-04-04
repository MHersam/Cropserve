package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.drawPolygons;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.mMap;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.polygons;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.userId;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MyExpandableListAdapter.feldID;

/**
 * Class to enable editing of a Feld
 * Contains setting view and saving information
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */
public class EditFeld extends Fragment {

    //attributes
    MainActivity main = (MainActivity) getActivity();
    Location location;
    double lat;
    double lng;
    public static ArrayList<Marker> marker = new ArrayList<Marker>();
    public Region reg = new Region("S", "S", "S", "S", 1);
    String art = "";
    Vertrag vertrag;
    TextView land;
    TextView bland;
    TextView landkreis;
    TextView stadt;
    boolean newVertrag = false;
    Polygon polygon = polygon = mMap.addPolygon(new PolygonOptions()
            .add(new LatLng(0,0))
            .fillColor(Color.parseColor("#660000ff"))
            .strokeColor(Color.parseColor("#660000ff"))
            .strokeWidth(5));

    /**
     * constructor
     */
    public EditFeld() {
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
        if (container != null) {
            container.removeAllViews();
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_feld, container, false);
    }

    /**
     * method sets up the view and set listeners
     *
     * @param view               current view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(feldID<0){
            marker.removeAll(marker);
            drawPolygons(getActivity().getApplicationContext());
        }


        //disable polygons
        for(int i=0;i<polygons.size();i++){
            polygons.get(i).setClickable(false);
        }

        //get screen with and height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        //Buttons initialization to set markers(edges from the field), getLocation and save the selected field
        Button save = getActivity().findViewById(R.id.save2);
        Button setMarker = getActivity().findViewById(R.id.setMarker2);
        Button gps = getActivity().findViewById(R.id.gps2);
        Button editInfo = getActivity().findViewById(R.id.editInfo);

        save.setWidth(100);
        save.setHeight(40);
        //save.setX(screenWidth - screenWidth / 4);
        //save.setY(screenHeight - screenHeight / 4);
        save.setText(R.string.save_btn);

        setMarker.setWidth(50);
        setMarker.setHeight(70);
       // setMarker.setX(0);
        //setMarker.setY(screenHeight - screenHeight / 4);
        setMarker.setText(R.string.pos_btn);

        gps.setWidth(50);
        gps.setHeight(40);
        //gps.setX(screenWidth / 2);
        //gps.setY(screenHeight - screenHeight / 4);
        gps.setText(R.string.gps_btn);

        editInfo.setText(R.string.edit_btn);


        //user is asked for permission for location services
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        //gps-button
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker g = new GPSTracker(getActivity().getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    lat = l.getLatitude();
                    lng = l.getLongitude();
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 17));
            }
        });

        //setting the markers-btn
        setMarker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                marker.add(mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude))
                        .title(String.valueOf(marker.size() + 1))
                        .draggable(true)
                ));
                polygon.remove();

                ArrayList<LatLng> triangle = new ArrayList<>();
                for(int i=0; i<marker.size();i++){
                    triangle.add(marker.get(i).getPosition());
                }
                triangle.add(triangle.get(0));
                polygon = mMap.addPolygon(new PolygonOptions()
                        .addAll(triangle)
                        .fillColor(Color.parseColor("#660000ff"))
                        .strokeColor(Color.parseColor("#660000ff"))
                        .strokeWidth(5));
                triangle.removeAll(triangle);
            }
        });

        //the save button must put in the new field into the database
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //an area should have at least 3 verticals
                if (marker.size() >= 3) {
                    //check if the additional information was added
                    if (reg.getLand().equals("S") || reg.getStadt().equals("S") || reg.getBundesland().equals("S") || reg.getLandkreis().equals("S")) {
                        Toast.makeText(getContext(), getString(R.string.info_missing), Toast.LENGTH_LONG).show();
                    } else {
                        //calculate the area of the field
                        double flaeche;
                        flaeche = berechnePolygonFlaeche(marker);

                        //create coordinates for the field, first and last latitude/longitude has to be the same
                        LatLng coordinates[] = new LatLng[marker.size() + 1];
                        for (int i = 0; i < marker.size(); i++) {
                            coordinates[i] = marker.get(i).getPosition();
                        }
                        coordinates[marker.size()] = marker.get(0).getPosition();
                        DatenbankDaten db = new DatenbankDaten(getActivity());
                        db.open();
                        //get the matching region from the database
                        for (int i = 0; i < db.getRegion().length; i++) {
                            if (db.getRegion()[i].getStadt().equals(reg.getStadt()) && db.getRegion()[i].getBundesland().equals(reg.getBundesland())) {
                                db.addVertrag(vertrag);
                                db.updateFeld(new Feld(feldID, art, flaeche, coordinates, db.getRegion()[i], vertrag));
                            }
                        }
                        //reset the static variable to avoid anomalies
                        feldID = -1;
                        drawPolygons(getActivity().getApplicationContext());
                        marker.removeAll(marker);

                        //go back to the previous fragment
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        FeldFragment feldFragment = new FeldFragment();
                        fm.beginTransaction().replace(R.id.flContent, feldFragment).commit();
                    }
                } else
                    //user warning
                    Toast.makeText(getContext(), getString(R.string.marker_missing), Toast.LENGTH_SHORT).show();
            }
        });

        //if you hold the marker you can drag and drop it to new position
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker markers) {
                markers.setPosition(markers.getPosition());
                polygon.remove();
                ArrayList<LatLng> triangle = new ArrayList<>();
                for(int i=0; i<marker.size();i++){
                    triangle.add(marker.get(i).getPosition());
                }
                triangle.add(triangle.get(0));
                polygon = mMap.addPolygon(new PolygonOptions()
                        .addAll(triangle)
                        .fillColor(Color.parseColor("#660000ff"))
                        .strokeColor(Color.parseColor("#660000ff"))
                        .strokeWidth(5));
                triangle.removeAll(triangle);
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker mark) {
                marker.remove(mark);
                mark.remove();
                polygon.remove();
                ArrayList<LatLng> triangle = new ArrayList<>();
                for(int i=0; i<marker.size();i++){
                    triangle.add(marker.get(i).getPosition());
                }
                if(marker.size()!=0){
                    triangle.add(triangle.get(0));
                    polygon = mMap.addPolygon(new PolygonOptions()
                            .addAll(triangle)
                            .fillColor(Color.parseColor("#660000ff"))
                            .strokeColor(Color.parseColor("#660000ff"))
                            .strokeWidth(5));
                    triangle.removeAll(triangle);
                }
                return true;
            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                marker.add(mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(String.valueOf(marker.size() + 1))
                        .draggable(true)
                ));
                polygon.remove();
                ArrayList<LatLng> triangle = new ArrayList<>();
                for (int i = 0; i < marker.size(); i++) {
                    triangle.add(marker.get(i).getPosition());
                }
                triangle.add(triangle.get(0));
                polygon = mMap.addPolygon(new PolygonOptions()
                        .addAll(triangle)
                        .fillColor(Color.parseColor("#660000ff"))
                        .strokeColor(Color.parseColor("#660000ff"))
                        .strokeWidth(5));
                triangle.removeAll(triangle);
            }
        });

        //opens edit-info Dialogue Window
        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialogfragment);
                dialog.setTitle("Title...");

                // Get screen width and height in pixels
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                // The absolute width of the available display size in pixels.
                int displayWidth = displayMetrics.widthPixels;
                // The absolute height of the available display size in pixels.
                int displayHeight = displayMetrics.heightPixels;

                // Initialize a new window manager layout parameters
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

                // Copy the alert dialog window attributes to new layout parameter instance
                layoutParams.copyFrom(dialog.getWindow().getAttributes());

                // Set the alert dialog window width and height
                // Set alert dialog width equal to screen width 90%
                // int dialogWindowWidth = (int) (displayWidth * 0.9f);
                // Set alert dialog height equal to screen height 90%
                // int dialogWindowHeight = (int) (displayHeight * 0.9f);

                // Set alert dialog width equal to screen width 70%
                int dialogWindowWidth = (int) (displayWidth * 1.0f);
                // Set alert dialog height equal to screen height 70%
                int dialogWindowHeight = (int) (displayHeight * 1.0f);

                // Set the width and height for the layout parameters
                // This will bet the width and height of alert dialog
                layoutParams.width = dialogWindowWidth;
                layoutParams.height = dialogWindowHeight;

                // Apply the newly created layout parameters to the alert dialog window
                dialog.getWindow().setAttributes(layoutParams);

                // set the custom dialog components - text, image and button
                /*spinner initialisation*/

                //init spinner #1 Art
                final Spinner spinnerArt = dialog.findViewById(R.id.spinnerArt);
                // Create an ArrayAdapter using the string array from res/values/strings and a default spinner layout
                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.feld_arten, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinnerArt.setAdapter(adapter2);

                //init spinner #2 Vertrag
                final Spinner spinnerVertrag = dialog.findViewById(R.id.spinnerVertrag);
                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.feld_verträge, android.R.layout.simple_spinner_item);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerVertrag.setAdapter(adapter3);

                //init spinner #3 User
                final TextView userInfo = dialog.findViewById(R.id.UserInfo);
                final Spinner spinnerUser = dialog.findViewById(R.id.spinnerUser);
                ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getUserList());
                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerUser.setAdapter(adapter4);


                if(! (userId>=0) ) {
                    //make spinner and text box visible
                    spinnerUser.setVisibility(View.VISIBLE);
                    userInfo.setVisibility(View.VISIBLE);
                }

                /*button init */
                Button cancelBtn = dialog.findViewById(R.id.cancelBtn);
                //simply cancel input of information upon click
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                Button dismissButton = dialog.findViewById(R.id.dismissbutton);
                // if button is clicked, close the custom dialog
                dismissButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //initialise the views for additional information
                        land = dialog.findViewById(R.id.landText2);
                        bland = dialog.findViewById(R.id.bundeslandText2);
                        landkreis = dialog.findViewById(R.id.landkreisText2);
                        stadt = dialog.findViewById(R.id.stadtText2);
                        art = spinnerArt.getSelectedItem().toString();

                        DatenbankDaten db = new DatenbankDaten(getActivity().getApplicationContext());
                        db.open();
                        //check if the information is filled in
                        if (!land.getText().toString().isEmpty() && !bland.getText().toString().isEmpty() && !landkreis.getText().toString().isEmpty() && !stadt.getText().toString().isEmpty()) {
                            reg = new Region(landkreis.getText().toString(), land.getText().toString(), bland.getText().toString(), stadt.getText().toString(), 1);
                            Region[] regions = db.getRegion();
                            if(userId>=0){
                                // generate new Vertrag object
                                vertrag = new Vertrag(db.getVertrag().length+1, db.getBenutzerById(userId), spinnerVertrag.getSelectedItem().toString());
                                newVertrag=true;
                                Log.e("newContract", "true");
                            }
                            else{
                                //make spinner and text box visible
                                spinnerUser.setVisibility(View.VISIBLE);
                                userInfo.setVisibility(View.VISIBLE);

                                //extract user ID from selected user-list item
                                String user = spinnerUser.getSelectedItem().toString();
                                String getsUserID = user.replaceAll("[^0-9]+", " ").trim();
                                int usersId = Integer.parseInt(getsUserID);

                                //first time selected, generate new Vertrag object
                                vertrag = new Vertrag(db.getVertrag().length+1, db.getBenutzerById(usersId), spinnerVertrag.getSelectedItem().toString());
                                newVertrag=true;
                                Log.e("newContract", "true");
                            }
                            //check if there is already an existing region in the database
                            int counter = 0;
                            for (int i = 0; i < regions.length; i++) {
                                if (regions[i].getStadt().equals(stadt.getText().toString()) && regions[i].getBundesland().equals(bland.getText().toString()))
                                    counter++;
                            }
                            //if not add new region
                            if (counter == 0)
                                db.addRegion(reg);
                            //close dialog
                            dialog.dismiss();
                        } else
                            //warning to user
                            Toast.makeText(getContext(), getString(R.string.info_missing3), Toast.LENGTH_SHORT).show();
                    }
                });
                //open dialogue on Button click
                dialog.show();
            }
        });
    }

    /*
     * distance of two lng/lat points in kilometers
     * Haversine formula
     *
     * @param lat1 latitude of 1st marker
     * @param lng1 longitude of 1st marker
     * @param lat2 latitude of 2nd marker
     * @param lng2 longitude of 2nd marker
     * @return double distance between two markers
     */
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371.0; // kilometers (or 3958.75 miles)
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        return dist;
    }


    /**
     * actual area calculation for field/dmg area
     *
     * @param marker
     * @return double with field area
     */
    public double berechnePolygonFlaeche(ArrayList<Marker> marker) {
        final int EARTH_RADIUS = 6371; //in km
        HashMap<Marker, List<Double>> coords = new HashMap<>();


        for(Marker m : marker) {
            ArrayList<Double> xys = new ArrayList<>();

            double latitude = m.getPosition().latitude;
            double longitude = m.getPosition().longitude;

            //Gall–Peters projection
            double x = EARTH_RADIUS * Math.toRadians(longitude);
            double y =2*EARTH_RADIUS* Math.sin(Math.toRadians(latitude));


            xys.add(x);
            xys.add(y);

            coords.put(m, xys);
        }

        double flaeche = 0;

        if(marker.size() == 3 ) {
            //side from first to second marker
            double c = distFrom(marker.get(0).getPosition().latitude, marker.get(0).getPosition().longitude, marker.get(1).getPosition().latitude, marker.get(1).getPosition().longitude);
            //side from 2nd to third marker
            double a = distFrom(marker.get(1).getPosition().latitude, marker.get(1).getPosition().longitude, marker.get(2).getPosition().latitude, marker.get(2).getPosition().longitude);
            //side from 3rd to 1st marker
            double b = distFrom(marker.get(2).getPosition().latitude, marker.get(2).getPosition().longitude, marker.get(0).getPosition().latitude, marker.get(0).getPosition().longitude);
            //angle between c ( 1st side) and a (2nd side)
            double cosB = (Math.pow(a,2) + Math.pow(c,2 ) - Math.pow(b, 2)) / (2 * a * c);
            double beta  = Math.acos(cosB);

            flaeche = (c * a * Math.sin(beta)) / 2;
        } else {

        /*Gauss' area formula*/
            for (int a = 0; a < marker.size() - 1; a++) {
                double x1 = coords.get(marker.get(a)).get(0);
                double y1 = coords.get(marker.get(a)).get(1);

                double x2 = coords.get(marker.get(a + 1)).get(0);
                double y2 = coords.get(marker.get(a + 1)).get(1);


                flaeche = flaeche + ((x1 * y2) - (y1 * x2));
            }

            //last term contains first marker x,y again; multiply with last marker
            flaeche += ((coords.get(marker.get(marker.size() - 1)).get(0) * coords.get(marker.get(0)).get(1))
                    - ((coords.get(marker.get(marker.size() - 1)).get(1)) * coords.get(marker.get(0)).get(0)));


            flaeche = Math.abs((flaeche/ 4.0));
        }

        //in km^2; * 100 to achieve hectare
        double area = round(flaeche*100, 4);

        return area;
    }


    /**
     * rounds a double (down) to a specified decimal place
     *
     * @param value the given double
     * @param places number of decimals
     * @return the rounded double
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_DOWN);
        return bd.doubleValue();
    }

    /**
     * retrieves all existing users from Database
     * combines user name + id for each list item
     *
     * @return ArrayList with all users by name and ID
     */
    public ArrayList<String> getUserList() {
        ArrayList<String> users = new ArrayList<>();
        DatenbankDaten db = new DatenbankDaten(getActivity().getApplicationContext());

        db.open();
        for(Benutzer user : db.getBenutzer()) {
            users.add(user.getFullName()+ ", "+ "ID: " +user.getId());
        }

        return users;
    }
}
