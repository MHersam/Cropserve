package de.uni_stuttgart.informatik.sopra.sopraapp;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
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

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.drawPolygons;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.location;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.mMap;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.polygons;
import static de.uni_stuttgart.informatik.sopra.sopraapp.MainActivity.userId;
import static de.uni_stuttgart.informatik.sopra.sopraapp.SchadenErfassen.punktInPolygon;
import static de.uni_stuttgart.informatik.sopra.sopraapp.SchadensfallFragment.schadenID;

/**
 * class that sets the content view
 * for after clicking on an item in the list of registered damages
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */
public class ListItemDetail extends Fragment {

    //attributes
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;
    ArrayList<String> attList;
    double lat;
    double lng;
    public Region reg2 = new Region("S", "S", "S", "S", 1);
    String schadensart ;
    String gutachter = "";
    TextView datum;
    TextView land2;
    TextView bland2;
    TextView landkreis2;
    TextView stadt2;
    public static ArrayList<Marker> marker = new ArrayList<Marker>();
    String[] bilder;
    Polygon polygon = polygon = mMap.addPolygon(new PolygonOptions()
            .add(new LatLng(0,0))
            .fillColor(Color.parseColor("#660000ff"))
            .strokeColor(Color.parseColor("#660000ff"))
            .strokeWidth(5));

    /**
     * constructor
     */
    public ListItemDetail() {
        // Required empty public constructor
    }


    /**
     * method is called when fragment is created
     * @param savedInstanceState
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_item_detail, container, false);
    }

    /**
     * method is called when the fragment is created and it initialize the fragment
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        for(int i=0;i<polygons.size();i++){
            polygons.get(i).setClickable(false);
        }

        if(MainActivity.location.getVisibility() == View.VISIBLE) {
            location.setVisibility(View.INVISIBLE);
        }

        //background view
        ListView dmgAtt = getActivity().findViewById(R.id.dmgAttr_List);
        dmgAtt.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.standard_background));


        //Buttons initialization to set markers(edges from the field), getLocation and save the selected field
        Button setMarker = getActivity().findViewById(R.id.setDmgEdMarker);
        Button gps = getActivity().findViewById(R.id.gpsEdDmg);

        //set button text
        gps.setText(R.string.gps_btn);
        setMarker.setText(R.string.pos_btn);


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


        setMarker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                marker.add(mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude))
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
                        .fillColor(Color.parseColor("#66ff0000"))
                        .strokeColor(Color.parseColor("#66ff0000"))
                        .strokeWidth(5));
                triangle.removeAll(triangle);
            }
        });


        //prepare list data
        getDmgAttribute();

        //fill List, set adapter
        ArrayAdapter<String> dmgAttAdapter = new ArrayAdapter<String>(getContext(), R.layout.list_item_dmg_attribute, attList);
        dmgAtt.setAdapter(dmgAttAdapter);


        //user is getting ask if he want to allow the location permission
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        //button that opens dmg case image
        ImageButton openImage = getActivity().findViewById(R.id.openImg);
        openImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatenbankDaten db = new DatenbankDaten(getActivity());
                db.open();
                String[] bilder = db.getSchadensfallById(schadenID).getBilder();
                //Log.i("imgIntent1", Integer.toString(bilder.length));

                if(bilder[0]!=null){
                    if(bilder.length > 0 && !bilder[0].toString().equals("bilder.jpeg")) {
                        String imagePath = bilder[0];

                        if(imagePath != null) {
                            Log.i("imgIntent2", imagePath);

                            final Dialog dialog = new Dialog(getContext());
                            dialog.setContentView((getLayoutInflater().inflate(R.layout.image_dialog_layout
                                    , null)));


                            /*cancel button */
                            Button cancelBtn = dialog.findViewById(R.id.closeDia);
                            //simply cancel input of information upon click
                            cancelBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });

                            ImageView mImageView = dialog.findViewById(R.id.imgDialog);
                            mImageView.requestLayout();

                            //rotate image
                            Matrix matrix = new Matrix();
                            matrix.postRotate(90);
                            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                            bitmap = Bitmap.createScaledBitmap(bitmap, mImageView.getLayoutParams().width,mImageView.getLayoutParams().height, false);

                            Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap , 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                            //the image view as rotated image
                            mImageView.setImageBitmap(rotatedBitmap);

                            //show dialog
                            dialog.show();

                            db.close();
                        }

                    } else {
                        Toast.makeText(getContext(), getString(R.string.no_picture), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), getString(R.string.no_picture), Toast.LENGTH_SHORT).show();
                }


            }
        });

        //button for editing dmg info
        ImageButton edDmgInfo = getActivity().findViewById(R.id.editDmgInfo);

        /*
         * listener for Edit Dmg Info-Btn
         * opens Edit-dialogue which allows to change and save information
         */
        edDmgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // custom dialog
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialogsffragment);
                dialog.setTitle("Title...");

                //dialog window headline
                TextView dg_header = getActivity().findViewById(R.id.dg_descr2);

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

                /*spinner initialisation*/

                //init spinner #1 Schadensart
                final Spinner spinnerSchArt = dialog.findViewById(R.id.spinnerSchArt);
                // Create an ArrayAdapter using the string array from res/values/strings and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.schadens_arten, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinnerSchArt.setAdapter(adapter);

                //init spinner #2 Gutachter
                final Spinner spinnerSchGutachter = dialog.findViewById(R.id.spinnerSchGutachter);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getGutachterNames());
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSchGutachter.setAdapter(adapter2);
                spinnerSchArt.setSelection(1);

                /*button init */

                Button photoBtn = dialog.findViewById(R.id.sphotoBtn);
                photoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
                        } else {
                            //call for camera action
                            dispatchTakePictureIntent();
                            Log.i("imgIntentS", "Intent dispatched");
                        }

                    }
                });

                Button cancelBtn = dialog.findViewById(R.id.btn_abbrechen);
                //simply cancel input of information upon click
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button okBtn = dialog.findViewById(R.id.button_ok);
                //SAVES the changed settings after clicking OK (no extra save button!)
                //all saving code belongs here ^-^
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        schadensart = spinnerSchArt.getSelectedItem().toString();
                        gutachter = spinnerSchGutachter.getSelectedItem().toString();
                        land2 = dialog.findViewById(R.id.landText2);
                        bland2 =dialog.findViewById(R.id.bundeslandText2);
                        landkreis2 =dialog.findViewById(R.id.landkreisText2);
                        stadt2 =dialog.findViewById(R.id.stadtText2);

                        DatenbankDaten db = new DatenbankDaten(getActivity().getApplicationContext());
                        db.open();

                        //check if the information is filled in
                        if (!land2.getText().toString().isEmpty() && !bland2.getText().toString().isEmpty() && !landkreis2.getText().toString().isEmpty() && !stadt2.getText().toString().isEmpty()) {
                            reg2 = new Region(landkreis2.getText().toString(), land2.getText().toString(), bland2.getText().toString(), stadt2.getText().toString(), 1);
                            Region[] regions = db.getRegion();
                            //check if there is already an existing region in the database
                            int counter = 0;
                            for (int i = 0; i < regions.length; i++) {
                                if (regions[i].getStadt().equals(stadt2.getText().toString()) && regions[i].getBundesland().equals(bland2.getText().toString()))
                                    counter++;
                            }
                            //if not add new region
                            if (counter == 0)
                                db.addRegion(reg2);

                           //saving pictures
                            bilder = new String[1];
                            bilder[0] = mCurrentPhotoPath;

                            //close dialog
                            dialog.dismiss();

                            //check if damage case area is actually an area
                            if (marker.size() >= 3) {
                                //check if the additional information was added
                                /*if (reg2.getLand().equals("S") || reg2.getStadt().equals("S") || reg2.getBundesland().equals("S") || reg2.getLandkreis().equals("S")) {
                                    Toast.makeText(getContext(), R.string.info_missing2, Toast.LENGTH_LONG).show();
                                } else { */

                                    double flaeche;
                                    flaeche =  berechnePolygonFlaeche(marker);

                                    //create coordinates for the field, first and last latitude/longitude has to be the same
                                    LatLng coordinates[] = new LatLng[marker.size() + 1];
                                    for (int i = 0; i < marker.size(); i++) {
                                        coordinates[i] = marker.get(i).getPosition();
                                    }
                                    coordinates[marker.size()] = marker.get(0).getPosition();
                                    //get all fields
                                    Feld[] felder = db.getFeld();
                                    ArrayList<Feld> userFelder = new ArrayList<Feld>();
                                    //search for the fields the user has registered
                                    for (int i = 0; i < felder.length; i++) {
                                        if(userId>=0){
                                            if (userId == felder[i].getVertrag().getPerson().getId())
                                                userFelder.add(felder[i]);
                                        }
                                        else{
                                            userFelder.add(felder[i]);
                                        }
                                    }
                                    Feld feld = null;
                                    //we need to check if the coordinates of the damage case are in one of the registered fields
                                    //in this variable is saved how often coordinates of the damage case are in the field
                                    int coordinateCounter = 0;
                                    //to avoid that one coordinate is in one field and one coordinate is in another one we need to save the counter after each loop of the second loop
                                    List<Integer> merkerList = new ArrayList<Integer>();
                                    for (int i = 0; i < userFelder.size(); i++) {
                                        for (int j = 0; j < coordinates.length - 1; j++) {
                                            if (punktInPolygon(userFelder.get(i).getKoordinaten(), coordinates[j]) == 1) {
                                                coordinateCounter++;
                                            } else if (punktInPolygon(userFelder.get(i).getKoordinaten(), coordinates[j]) == 0) {
                                                coordinateCounter++;
                                            } else break;
                                        }
                                        merkerList.add(coordinateCounter);
                                        if (coordinateCounter == coordinates.length - 1) feld = userFelder.get(i);
                                            coordinateCounter = 0;
                                    }
                                    //Log.i("cea", merkerList.get(felder.length-2).toString());
                                    //now the list has to be sorted to get the largest element on the last position

                                    Collections.sort(merkerList);
                                    //if the last element in the sortedList merkerList is the number of total coordinates of the damage case, save it
                                    if (merkerList.get(userFelder.size() - 1) == coordinates.length-1) {
                                        //get the matching region from the database
                                        for (int i = 0; i < db.getRegion().length; i++) {
                                            if (db.getRegion()[i].getStadt().equals(reg2.getStadt()) && db.getRegion()[i].getBundesland().equals(reg2.getBundesland())) {
                                                db.updateSchadensfall(new Schadensfall(schadensart, feld, coordinates, getString(R.string.dmg_status), db.getRegion()[i], Calendar.getInstance().getTime().toString(), bilder, schadenID, flaeche, db.getGutachterById(1)));
                                            }
                                        }
                                        //reset the static variable to avoid anomalies
                                        //schadenID = -1;
                                        drawPolygons(getActivity().getApplicationContext());
                                        marker.removeAll(marker);

                                        //go back to the previous fragment
                                        FragmentManager fm = getActivity().getSupportFragmentManager();
                                        SchadensfallFragment schadensfallFragment = new SchadensfallFragment();
                                        FragmentTransaction ft = fm.beginTransaction();
                                        ft.replace(R.id.flContent, schadensfallFragment).addToBackStack("tag").commit();
                                    } else {
                                        Toast.makeText(getContext(), getString(R.string.coordinate_missing), Toast.LENGTH_SHORT).show();
                                    }
                            } else
                                Toast.makeText(getContext(), getString(R.string.marker_missing), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getContext(), getString(R.string.info_missing3), Toast.LENGTH_SHORT).show();
                        }
                        db.close();
                    }
                });

                //open dialogue on Button click
                dialog.show();
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
                        .fillColor(Color.parseColor("#66ff0000"))
                        .strokeColor(Color.parseColor("#66ff0000"))
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
                            .fillColor(Color.parseColor("#66ff0000"))
                            .strokeColor(Color.parseColor("#66ff0000"))
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
                        .fillColor(Color.parseColor("#66ff0000"))
                        .strokeColor(Color.parseColor("#66ff0000"))
                        .strokeWidth(5));
                triangle.removeAll(triangle);
            }
        });

    }


    /**
     * pulls data from database to fill ListView with every attributes
     * from the Schadensfall we are viewing
     */
    public void getDmgAttribute() {
        //list that holds list item strings/headers
        attList = new ArrayList<String>();

        DatenbankDaten pullDmgAttributes = new DatenbankDaten(getActivity().getApplicationContext());
        pullDmgAttributes.open();

        //fill list
        attList.add(getString(R.string.l_s_art) + " " + pullDmgAttributes.getSchadensfallById(schadenID).getSchadensart() + "\n");
        attList.add(getString(R.string.l_s_feld)+ " " + "#" + pullDmgAttributes.getSchadensfallById(schadenID).getFeld().getId() + ", "  + pullDmgAttributes.getSchadensfallById(schadenID).getFeld().getArt() + "\n");
        attList.add(getString(R.string.l_s_status) + " "+ pullDmgAttributes.getSchadensfallById(schadenID).getStatus() + "\n");
        attList.add(getString(R.string.l_s_loc) + " "+ pullDmgAttributes.getSchadensfallById(schadenID).getRegion().getBundesland() +", " + pullDmgAttributes.getSchadensfallById(schadenID).getRegion().getStadt() + "\n");
        attList.add(getString(R.string.l_s_rev) + " " + pullDmgAttributes.getSchadensfallById(schadenID).getGutachter().getFullName() + "\n");
        attList.add(getString(R.string.l_s_date) + " " + pullDmgAttributes.getSchadensfallById(schadenID).getDatum() + "\n");
        attList.add(getString(R.string.l_s_area) + " " + pullDmgAttributes.getSchadensfallById(schadenID).getFlaeche() + " " + getString(R.string.l_maß) + "\n");
        attList.add(getString(R.string.l_s_person) + " " + pullDmgAttributes.getSchadensfallById(schadenID).getFeld().getVertrag().getPerson().getFullName() + "\n");

        pullDmgAttributes.close();

    }


    /**
     * fetches all existing Gutachter from data base
     *
     * @return ArrayList with all Gutachter names
     */
    public ArrayList<String> getGutachterNames() {
        ArrayList<String> gaNames = new ArrayList<>();
        DatenbankDaten getGaName = new DatenbankDaten(getActivity().getApplicationContext());
        getGaName.open();

        for(Gutachter g : getGaName.getGutachter()) {
            String name = g.getVorname() + " " + g.getNachname();
            gaNames.add(name);
        }
        getGaName.close();

        return  gaNames;

    }


    /*---------------------AREA CALCULATION-------------------*/

    /*
     * distance of two lng/lat points in kilometers
     * Haversine formula
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
     * @param marker of the damage case polygon
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



    /* -------------------PHOTO METHODS----------------------*/


    /**
     * sends an intent to start camera application
     * starts the camera activity
     * handles the event (photo) on a positive activity result
     */
    private void dispatchTakePictureIntent() {
        //Standard Intent action that can be sent to have the camera application capture an image and return it
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e("error", "Error while creating photo file");
                Toast.makeText(getContext(), getString(R.string.no_file), Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                // return a content URI for the given file
                Uri photoURI = FileProvider.getUriForFile(getContext(),"de.uni_stuttgart.informatik.sopra.sopraapp",
                        photoFile);
                //EXTRA_OUTPUT: used to indicate a content resolver Uri to be used to store the requested image
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                Log.i("imgIntentS", "Intent to Camera");
            }

        }
    }


    /**
     * Called upon returning to the (app) activity
     * after taking a photo
     *
     * Retrieves the encoded the photo
     *
     * @param requestCode
     * @param resultCode
     * @param data Intent which holds the photo as a URI
     */
    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            galleryAddPic();
            Log.i("imgIntentS", "Pic saved");
        }
    }


    /**
     * Creates the image file for the taken photo in
     * specified directory
     *
     * Sets the absolute file path for that photo
     *
     * @return (image jpg) file representing the photo that was taken
     * @throws IOException when file cannot be created
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        /* returns the path to files folder inside Android/data/data/your_package/ on your SD card
         * Environment.DIRECTORY_PICTURES: directory in which to place pictures that are available to the user */
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        //new empty file in the specified directory, with prefix and suffix strings to generate its name
        File image = File.createTempFile(
                imageFileName,  //prefix
                ".jpg",         //suffix
                storageDir      //directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /**
     * writes taken picture to phone gallery
     *
     */
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getContext().sendBroadcast(mediaScanIntent);
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
        MediaStore.Images.Media.insertImage(getContext().getContentResolver(), bitmap, "" , "");
        Log.i("imgIntent", "Pic saved in gallery");

    }


    /**
     * getter for the image file path to use in other classes for display
     * @return String of image file path
     */
    public String getImageFilePath() {
        String imagePath = mCurrentPhotoPath;

        return  imagePath;
    }

    /**
     * called on requested picture permission, and handles the event
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE: {
                //if request is cancelled, the result arrays are empty
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    dispatchTakePictureIntent();
                } else {
                    //permission is denied
                    //disable functionality that depends on this permission
                    Toast.makeText(getContext(), getString(R.string.no_permission), Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


}



